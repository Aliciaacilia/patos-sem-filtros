const express = require('express');
const session = require('express-session');
const fs = require('fs');
const path = require('path');

const app = express();
const PORT = 3000;

app.use(express.json());
app.use(session({
  secret: 'denuncias-secret-local',
  resave: false,
  saveUninitialized: false,
  cookie: { secure: false }
}));

const DATA_DIR = path.join(__dirname, 'data');
const USUARIOS_FILE = path.join(DATA_DIR, 'usuarios.json');
const DENUNCIAS_FILE = path.join(DATA_DIR, 'denuncias.json');
const COMENTARIOS_FILE = path.join(DATA_DIR, 'comentarios.json');
const CURTIDAS_FILE = path.join(DATA_DIR, 'curtidas.json');


function lerArquivo(caminho) {
  if (!fs.existsSync(caminho)) {
    fs.writeFileSync(caminho, '[]');
  }
  const data = fs.readFileSync(caminho, 'utf8');
  return JSON.parse(data || '[]');
}


function escreverArquivo(caminho, dados) {
  fs.writeFileSync(caminho, JSON.stringify(dados, null, 2));
}


function requireAuth(req, res, next) {
  if (!req.session.userId) {
    return res.status(401).json({ erro: 'Não autenticado' });
  }
  next();
}

app.post('/register', (req, res) => {
  const { nome, email, senha } = req.body;

  if (!nome || !email || !senha) {
    return res.status(400).json({ erro: 'Nome, email e senha são obrigatórios' });
  }

  const usuarios = lerArquivo(USUARIOS_FILE);
  if (usuarios.some(u => u.email === email)) {
    return res.status(400).json({ erro: 'Email já cadastrado' });
  }

  const novoUsuario = {
    id: String(Date.now()),
    nome: nome.trim(),
    email: email.trim(),
    senha: senha,
    anonimo: false
  };

  usuarios.push(novoUsuario);
  escreverArquivo(USUARIOS_FILE, usuarios);
  res.status(201).json({ ok: true, mensagem: 'Usuário criado' });
});


app.post('/login', (req, res) => {
  const { email, senha } = req.body;
  const usuarios = lerArquivo(USUARIOS_FILE);
  const user = usuarios.find(u => u.email === email && u.senha === senha);

  if (user) {
    req.session.userId = user.id;
    return res.json({ ok: true, nome: user.nome, anonimo: user.anonimo });
  }
  res.status(401).json({ erro: 'Credenciais inválidas' });
});

app.post('/logout', (req, res) => {
  req.session.destroy();
  res.json({ ok: true });
});

app.get('/me', requireAuth, (req, res) => {
  const usuarios = lerArquivo(USUARIOS_FILE);
  const user = usuarios.find(u => u.id === req.session.userId);
  if (!user) return res.status(404).json({ erro: 'Usuário não encontrado' });

  res.json({ id: user.id, nome: user.nome, email: user.email, anonimo: user.anonimo });
});

app.patch('/me', requireAuth, (req, res) => {
  const { anonimo } = req.body;
  if (typeof anonimo !== 'boolean') {
    return res.status(400).json({ erro: 'Campo "anonimo" deve ser true ou false' });
  }

  const usuarios = lerArquivo(USUARIOS_FILE);
  const index = usuarios.findIndex(u => u.id === req.session.userId);
  if (index === -1) return res.status(404).json({ erro: 'Usuário não encontrado' });

  usuarios[index].anonimo = anonimo;
  escreverArquivo(USUARIOS_FILE, usuarios);
  res.json({ ok: true, anonimo });
});


app.post('/denuncias', requireAuth, (req, res) => {
  const { titulo, descricao, categoria, localizacao } = req.body;

  if (!titulo || titulo.trim().length < 5) {
    return res.status(400).json({ erro: 'Título obrigatório (mín. 5 caracteres)' });
  }
  if (!descricao || descricao.trim().length < 10) {
    return res.status(400).json({ erro: 'Descrição obrigatória (mín. 10 caracteres)' });
  }
  if (!categoria) {
    return res.status(400).json({ erro: 'Categoria é obrigatória' });
  }
  if (!localizacao) {
    return res.status(400).json({ erro: 'Localização é obrigatória' });
  }

  const denuncias = lerArquivo(DENUNCIAS_FILE);
  const novaDenuncia = {
    id: String(Date.now()),
    usuarioId: req.session.userId,
    titulo: titulo.trim(),
    descricao: descricao.trim(),
    categoria: categoria.trim(),
    localizacao: localizacao.trim(),
    status: 'pendente',
 data: new Date().toISOString()
  };

  denuncias.push(novaDenuncia);
  escreverArquivo(DENUNCIAS_FILE, denuncias);
  res.status(201).json(novaDenuncia);
});


app.get('/denuncias', (req, res) => {
  let denuncias = lerArquivo(DENUNCIAS_FILE);
  const usuarios = lerArquivo(USUARIOS_FILE);
  const { categoria, status, localizacao } = req.query;

  if (categoria) denuncias = denuncias.filter(d => d.categoria === categoria);
  if (status) denuncias = denuncias.filter(d => d.status === status);
  if (localizacao) denuncias = denuncias.filter(d => d.localizacao.includes(localizacao));

  const resultado = denuncias.map(d => {
    const autor = usuarios.find(u => u.id === d.usuarioId);
    return {
      ...d,
      autor: autor?.anonimo ? 'Anônimo' : (autor?.nome || 'Desconhecido')
    };
  });

  res.json(resultado);
});


app.get('/me/denuncias', requireAuth, (req, res) => {
  const denuncias = lerArquivo(DENUNCIAS_FILE);
  const minhas = denuncias.filter(d => d.usuarioId === req.session.userId);
  res.json(minhas);
});


app.get('/denuncias/:id', (req, res) => {
  const { id } = req.params;
  const denuncias = lerArquivo(DENUNCIAS_FILE);
  const denuncia = denuncias.find(d => d.id === id);
  if (!denuncia) return res.status(404).json({ erro: 'Denúncia não encontrada' });

  const usuarios = lerArquivo(USUARIOS_FILE);
  const comentarios = lerArquivo(COMENTARIOS_FILE);
  const curtidas = lerArquivo(CURTIDAS_FILE);

  const autor = usuarios.find(u => u.id === denuncia.usuarioId);
  const listaComentarios = comentarios
    .filter(c => c.denunciaId === id)
    .map(c => {
      const user = usuarios.find(u => u.id === c.usuarioId);
      return {
        ...c,
        autor: user?.anonimo ? 'Anônimo' : (user?.nome || 'Desconhecido')
      };
    });

  const totalCurtidas = curtidas.filter(c => c.denunciaId === id).length;
  const euCurti = req.session.userId
    ? curtidas.some(c => c.denunciaId === id && c.usuarioId === req.session.userId)
    : false;

  res.json({
    ...denuncia,
    autor: autor?.anonimo ? 'Anônimo' : (autor?.nome || 'Desconhecido'),
    comentarios: listaComentarios,
    totalCurtidas,
    euCurti
  });
});


app.post('/denuncias/:id/comentarios', requireAuth, (req, res) => {
  const { id } = req.params;
  const { texto } = req.body;

  if (!texto || texto.trim().length < 1) {
    return res.status(400).json({ erro: 'Texto do comentário é obrigatório' });
  }

  const denuncias = lerArquivo(DENUNCIAS_FILE);
  if (!denuncias.some(d => d.id === id)) {
    return res.status(404).json({ erro: 'Denúncia não encontrada' });
  }

  const comentarios = lerArquivo(COMENTARIOS_FILE);
  comentarios.push({
    id: String(Date.now()),
    denunciaId: id,
    usuarioId: req.session.userId,
    texto: texto.trim(),
 data: new Date().toISOString()
  });

  escreverArquivo(COMENTARIOS_FILE, comentarios);
  res.status(201).json({ ok: true });
});


app.post('/denuncias/:id/curtir', requireAuth, (req, res) => {
  const { id } = req.params;
  const userId = req.session.userId;

  const denuncias = lerArquivo(DENUNCIAS_FILE);
  if (!denuncias.some(d => d.id === id)) {
    return res.status(404).json({ erro: 'Denúncia não encontrada' });
  }

  const curtidas = lerArquivo(CURTIDAS_FILE);
  const jaCurtiu = curtidas.some(c => c.denunciaId === id && c.usuarioId === userId);

  if (jaCurtiu) {

    const atualizado = curtidas.filter(c => !(c.denunciaId === id && c.usuarioId === userId));
    escreverArquivo(CURTIDAS_FILE, atualizado);
    res.json({ acao: 'removida' });
  } else {

    curtidas.push({ denunciaId: id, usuarioId: userId });
    escreverArquivo(CURTIDAS_FILE, curtidas);
    res.json({ acao: 'adicionada' });
  }
});


app.listen(PORT, () => {
  console.log(`✅ Servidor rodando em http://localhost:${PORT}`);
  console.log('📝 Dados salvos na pasta ./data/');
});


CREATE TABLE tipos_usuario (
    tipo_id SERIAL PRIMARY KEY,
    nome_tipo VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE usuarios (
    usuario_id SERIAL PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL,
    email_verificado BOOLEAN NOT NULL DEFAULT FALSE,
    tipo_id INT NOT NULL,

    CONSTRAINT fk_tipo_usuario
        FOREIGN KEY (tipo_id)
        REFERENCES tipos_usuario (tipo_id)
);

CREATE TABLE usuarios_moradores (
    usuario_morador_id SERIAL PRIMARY KEY,

    usuario_id INT NOT NULL UNIQUE,
    nome_perfil VARCHAR(100) NOT NULL,

    CONSTRAINT fk_usuario_morador
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios (usuario_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS denuncias (
    denuncia_id SERIAL PRIMARY KEY,

    usuario_morador_id INT NOT NULL,
    descricao TEXT NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(30) NOT NULL,
    visibilidade VARCHAR(30) NOT NULL,
    foto VARCHAR(255),
    video VARCHAR(255),

    CONSTRAINT fk_denuncia_morador
        FOREIGN KEY (usuario_morador_id)
        REFERENCES usuarios_moradores (usuario_morador_id)
        ON DELETE CASCADE
);

CREATE TABLE historico_postagens (
    historico_id SERIAL PRIMARY KEY,

    denuncia_id INT NOT NULL,
    acao VARCHAR(50) NOT NULL,
    descricao TEXT,

    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_historico_denuncia
        FOREIGN KEY (denuncia_id)
        REFERENCES denuncias (denuncia_id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS notificacoes (
    notificacao_id SERIAL PRIMARY KEY,

    usuario_id INT NOT NULL,
    mensagem TEXT NOT NULL,
    visualizada BOOLEAN NOT NULL DEFAULT FALSE,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_notificacao_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuarios (usuario_id)
        ON DELETE CASCADE
);

CREATE TABLE curtidas_denuncia (
    curtida_id SERIAL PRIMARY KEY,

    denuncia_id INT NOT NULL,
    usuario_morador_id INT NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_curtida_denuncia
        FOREIGN KEY (denuncia_id)
        REFERENCES denuncias (denuncia_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_curtida_morador
        FOREIGN KEY (usuario_morador_id)
        REFERENCES usuarios_moradores (usuario_morador_id)
        ON DELETE CASCADE
);

CREATE TABLE categorias (
    categoria_id SERIAL PRIMARY KEY,
    nome VARCHAR(50) NOT NULL UNIQUE
);

ALTER TABLE denuncias
ADD COLUMN categoria_id INT NOT NULL;

ALTER TABLE denuncias
ADD CONSTRAINT fk_denuncia_categoria
    FOREIGN KEY (categoria_id)
    REFERENCES categorias (categoria_id);

CREATE TABLE comentarios_denuncia (
    comentario_id SERIAL PRIMARY KEY,

    denuncia_id INT NOT NULL,
    usuario_morador_id INT NOT NULL,
    comentario TEXT NOT NULL,
    data_hora TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_comentario_denuncia
        FOREIGN KEY (denuncia_id)
        REFERENCES denuncias (denuncia_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_comentario_morador
        FOREIGN KEY (usuario_morador_id)
        REFERENCES usuarios_moradores (usuario_morador_id)
        ON DELETE CASCADE
);

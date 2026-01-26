INSERT INTO tipos_usuario (tipo_id, nome_tipo) 
VALUES (1, 'Morador'), (2, 'Moderador')
ON CONFLICT (tipo_id) DO NOTHING;

INSERT INTO usuarios_moradores (usuario_id) VALUES (3);

INSERT INTO categorias (categoria_id, nome) VALUES (1, 'Saúde');
INSERT INTO categorias (categoria_id, nome) VALUES (2, 'Saneamento');
INSERT INTO categorias (categoria_id, nome) VALUES (3, 'Lixo e Limpeza');
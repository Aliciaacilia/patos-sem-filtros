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
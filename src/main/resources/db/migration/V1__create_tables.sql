CREATE TABLE pauta (
    id uuid PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    descricao TEXT NOT NULL,
    destinatarios TEXT[],
    aberta BOOLEAN DEFAULT false,
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE associado (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL
);

CREATE TABLE voto (
    id UUID PRIMARY KEY,
    pauta_id UUID NOT NULL,
    associado_id UUID NOT NULL,
    voto VARCHAR(3),
    FOREIGN KEY (pauta_id) REFERENCES pauta(id),
    FOREIGN KEY (associado_id) REFERENCES associado(id)
);

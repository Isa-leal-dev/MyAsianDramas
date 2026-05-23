CREATE TABLE IF NOT EXISTS tb_usuarios (
    id_usuario SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    email VARCHAR(100),
    senha VARCHAR(50),
    data_criacao TIMESTAMP DEFAULT NOW()
);

CREATE TABLE IF NOT EXISTS tb_generos(
    id_genero SERIAL PRIMARY KEY,
    id_tmdb_genero INTEGER,
    descricao VARCHAR (50)
);

CREATE TABLE IF NOT EXISTS tb_atores (
  id_ator SERIAL PRIMARY KEY,
  id_tmdb_ator INTEGER,
  nome_ator VARCHAR (80),
  nome_original VARCHAR (80),
  data_nascimento DATE,
  sexo CHAR (1),--Enum
  foto_perfil VARCHAR(500) --para link
);

CREATE TABLE IF NOT EXISTS tb_doramas (
  id_dorama SERIAL PRIMARY KEY,
  titulo_portugues VARCHAR (200),
  titulo_nativo VARCHAR (200),
  titulo_ingles VARCHAR (200),
  sinopse VARCHAR (1000),
  status_dorama VARCHAR(20), --Enum 
  pais CHAR(2), --Enum
  data_estreia DATE,
  data_final DATE,
  emissora_original VARCHAR(50),
  poster VARCHAR(500), --para link
  numero_episodios INTEGER
);

CREATE TABLE IF NOT EXISTS tb_listas (
  id_lista SERIAL PRIMARY KEY,
  id_usuario INTEGER,
  nome_lista VARCHAR(100),
  descricao VARCHAR(400),
  criada_em TIMESTAMP DEFAULT NOW(),
  FOREIGN KEY (id_usuario) REFERENCES tb_usuarios(id_usuario)
);

--AUXILIARES:

CREATE TABLE IF NOT EXISTS tb_elenco (
  id_dorama INTEGER,
  id_ator INTEGER,
  personagem VARCHAR(200),
  PRIMARY KEY (id_dorama, id_ator),
  FOREIGN KEY (id_dorama) REFERENCES tb_doramas(id_dorama),
  FOREIGN KEY (id_ator) REFERENCES tb_atores(id_ator)
);

CREATE TABLE IF NOT EXISTS tb_doramas_generos (
  id_dorama INTEGER,
  id_genero INTEGER,
  PRIMARY KEY (id_dorama, id_genero),
  FOREIGN KEY (id_dorama) REFERENCES tb_doramas(id_dorama),
  FOREIGN KEY (id_genero) REFERENCES tb_generos(id_genero)
);

CREATE TABLE IF NOT EXISTS tb_lista_doramas(
  id_lista INTEGER,
  id_dorama INTEGER,
  status_visualizacao VARCHAR(20), --Enum
  PRIMARY KEY (id_lista, id_dorama),
  FOREIGN KEY (id_dorama) REFERENCES tb_doramas(id_dorama),
  FOREIGN KEY (id_lista) REFERENCES tb_listas(id_lista)
);


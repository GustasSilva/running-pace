-- Banco de dados do Running Pace
-- Esquema reconstruído a partir do código (cadastro.java -> método botaoSalva()).
-- O app conecta em jdbc:mysql://localhost:3306/runningpace como root (sem senha).

CREATE DATABASE IF NOT EXISTS runningpace
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE runningpace;

-- Tabela usada pelo INSERT em cadastro.java:
--   INSERT INTO placar (nome_piloto, nome_equipe, num_carro, id_temp) VALUES (...)
CREATE TABLE IF NOT EXISTS placar (
    id          INT AUTO_INCREMENT PRIMARY KEY,
    nome_piloto VARCHAR(100),
    nome_equipe VARCHAR(100),
    num_carro   VARCHAR(20),
    id_temp     VARCHAR(50)   -- tempo/identificador gravado pelo app (ex.: "0.0")
);

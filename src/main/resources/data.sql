DROP TABLE IF EXISTS tb_user;
DROP TABLE IF EXISTS user;

CREATE TABLE tb_user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  email VARCHAR(250) NOT NULL,
  password VARCHAR(1000) NOT NULL
);

INSERT INTO tb_user (name, email, password) VALUES
  ('João', 'joao@email.com', '$2y$12$mgjaLEz.FjB8ifwr/zqmcehx5yswbYDvYd7sTzKofCQMhu7/43Q0q'),
  ('Estevam', 'estevam@email.com', '$2y$12$mgjaLEz.FjB8ifwr/zqmcehx5yswbYDvYd7sTzKofCQMhu7/43Q0q');
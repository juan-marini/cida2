INSERT INTO tb_fazenda(nome, municipio, area_hectares, data_cadastro) VALUES ('Fazenda Santa Rita', 'Ribeirão Preto', 320.5, '2026-01-15');
INSERT INTO tb_fazenda(nome, municipio, area_hectares, data_cadastro) VALUES ('Sítio Boa Esperança', 'Uberlândia', 85.0, '2026-01-20');
INSERT INTO tb_fazenda(nome, municipio, area_hectares, data_cadastro) VALUES ('Fazenda Horizonte', 'Sorriso', 1240.75, '2026-02-03');
INSERT INTO tb_fazenda(nome, municipio, area_hectares, data_cadastro) VALUES ('Chácara Vale Verde', 'Londrina', 47.3, '2026-02-10');

INSERT INTO tb_leitura_ndvi(nome_area, valor_ndvi, status, data_leitura, fazenda_id) VALUES ('Talhão Norte', 0.82, 'SAUDAVEL', '2026-03-01', 1);
INSERT INTO tb_leitura_ndvi(nome_area, valor_ndvi, status, data_leitura, fazenda_id) VALUES ('Talhão Sul', 0.55, 'ATENCAO', '2026-03-01', 1);
INSERT INTO tb_leitura_ndvi(nome_area, valor_ndvi, status, data_leitura, fazenda_id) VALUES ('Setor Leste', 0.31, 'ALERTA', '2026-03-05', 2);
INSERT INTO tb_leitura_ndvi(nome_area, valor_ndvi, status, data_leitura, fazenda_id) VALUES ('Pivô Central', 0.74, 'SAUDAVEL', '2026-03-07', 3);

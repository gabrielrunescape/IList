-- CURRENT VERSION APPLICATION 0.2 --
CREATE TABLE IF NOT EXISTS `Unidade` (
    ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
    Descricao VARCHAR(16) NOT NULL,
    Abreviacao VARCHAR(4) NULL
);

ALTER TABLE `Item` ADD Quantidade INTEGER NOT NULL DEFAULT 1;
ALTER TABLE `Item` ADD Unidade INTEGER NOT NULL REFERENCES `Unidade` (ID) DEFAULT 1;

INSERT INTO `Unidade` VALUES (NULL, 'Metro', 'm');
INSERT INTO `Unidade` VALUES (NULL, 'Peça', 'pc');
INSERT INTO `Unidade` VALUES (NULL, 'Quilograma', 'kg');
INSERT INTO `Unidade` VALUES (NULL, 'Unidade', 'un');
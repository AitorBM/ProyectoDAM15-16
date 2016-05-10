CREATE OR REPLACE PROCEDURE INSERT_CATEGORIA
(
  P_NOM CATEGORIAS.NOMBRE%TYPE
)
AS
	V_ID CATEGORIAS.ID_CAT%TYPE;
BEGIN
	SELECT MAX(ID_CAT)+1 INTO V_ID FROM CATEGORIAS;
	INSERT INTO CATEGORIAS(ID_CAT, NOMBRE) VALUES(V_ID, P_NOM);
END INSERT_CATEGORIA;
--***************************************************************
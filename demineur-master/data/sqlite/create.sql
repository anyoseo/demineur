----------------------
-- Créer les tables
----------------------

CREATE TABLE GAMES (
	id			INTEGER 	PRIMARY KEY AUTOINCREMENT,
	nbRow	INTEGER		NOT NULL,
	nbColumn	INTEGER		NOT NULL,
	bombs	INTEGER		NOT NULL, 
	CONSTRAINT parametre	UNIQUE(nbRow, nbColumn, bombs)
	);	

	
CREATE TABLE RECORDS (
	id			INTEGER PRIMARY KEY AUTOINCREMENT,
	score		INTEGER,
	dateRecord		TEXT	NOT NULL,
	id_game		INTEGER	NOT NULL,
	name  	TEXT	NOT NULL,
	FOREIGN KEY(id_game) REFERENCES GAMES(id),
	);	
	(result.getInt(1), result.getInt(2),  result.getString(3), result.getInt(4), result.getString(5) );
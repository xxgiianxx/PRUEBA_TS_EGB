CREATE TABLE IF NOT EXISTS participante (
participanteid SERIAL PRIMARY KEY,
nombre VARCHAR(255) NOT NULL,
apellidoPaterno VARCHAR(255) NOT NULL,
apellidoMaterno VARCHAR(255) NOT NULL,
direccion VARCHAR(255) NOT NULL,
telefono VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS evento (
eventoid SERIAL PRIMARY KEY,
nombre VARCHAR(255) NOT NULL,
tipoevento VARCHAR(255) NOT NULL,
participanteid INTEGER NOT NULL,
fechaevento TIMESTAMP NOT NULL,
lugar VARCHAR(255) NOT NULL,
FOREIGN KEY (participanteid) REFERENCES participante(participanteid)
);
# onlineStore


docker run --name store-postgres -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1111 -e POSTGRES_DB=store -d postgres

CREATE TABLE product( id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, price numeric NOT NULL, created TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

CREATE TABLE users( id SERIAL PRIMARY KEY, user_name CHAR(50) NOT NULL, login CHAR(50) NOT NULL, password CHAR(50) NOT NULL, sole CHAR(50) NOT NULL );

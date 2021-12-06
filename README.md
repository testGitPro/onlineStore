# onlineStore


docker run --name store-postgres -p 5433:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=1111 -e POSTGRES_DB=store -d postgres

CREATE TABLE product( id SERIAL PRIMARY KEY, name VARCHAR(100) NOT NULL, price numeric NOT NULL, created TIMESTAMP DEFAULT CURRENT_TIMESTAMP);

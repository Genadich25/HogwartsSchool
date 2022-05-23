CREATE TABLE drivers(
  name TEXT UNIQUE NOT NULL,
  id BIGINT UNIQUE NOT NULL,
  age INTEGER CHECK (age > 18),
  drivers_license BOOLEAN,
  id_car BIGINT REFERENCES cars (id) check (drivers_license)
);

CREATE TABLE cars(
    id BIGINT UNIQUE NOT NULL,
    stamp TEXT UNIQUE,
    model TEXT UNIQUE,
    price INTEGER
);
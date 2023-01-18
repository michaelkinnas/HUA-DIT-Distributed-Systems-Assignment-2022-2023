# Air-Tours, D.S. Assigment 2022-2023
This project is part of a semester assigment for the course of "Distributed Systems" of Harokopio University of Athens, 5th semester, 2022-2023.

## Installation isntructions
The project is composed of two parts. The backened application and the frontend application.

## 1. Running the backend.

The application uses PostgreSQL database using Docker. To run the database use:

`docker run --name DS_Assigment_AirTours --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=air_tours -p 5432:5432 -v pgdata_group35:/var/lib/postgresql/data -d postgres:14`


You can connect to the database using these details:

Port: 5432

Username: postgres

Password: pass123


## 2. Running the application server.
Open the directory "DS-Assigment-2022-20223/backend" in an IDE preferably IntelliJ. Wait for dependencies to be installed and then run the server application from the IDE.


## 3. Running the frontend.

You need NodeJS and NPM installed.

Navigate to the "DS-Assigment-2022-20223/frontend" directory.

Install the dependencies by running:

`npm install`

Once the depedencies are installed run:

`npm start`

###
The application server runs on `http://localhost:8080`

The application frontend runs on `http://localhost:3000`



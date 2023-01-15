## **To create a docker image**
```bash
docker run --name spb_db --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=air_tours -p 5432:5432 -v pgdata14:/var/lib/postgresql/data  -d postgres:14
```
## **To remove db data**
```bash
docker volume rm pgdata14
```
## **To run the db**
```bash
psql -h localhost -p 5432 -U postgres -W
```
## **To open the air_tours db**
```bash
postres=# \c air_tours
```
## **To view the db tables**
```bash
air_tours=# \dt
```

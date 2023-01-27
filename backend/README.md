## **To create a docker image**
```bash
docker run --name DS_Assigment_AirTours --rm -e  POSTGRES_PASSWORD=pass123 -e POSTGRES_DB=air_tours -p 5432:5432 -v group35_data_main:/var/lib/postgresql/data -d postgres:14
```
## **To remove db data**
```bash
docker volume rm group35_data_main
```

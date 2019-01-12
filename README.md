# Prescriptions Manager

## Docker
### Build  
```docker build -t "michuu93/prescriptions-manager" .```
### Run
##### Database
```docker run --name postgres -d -v ~/postgres:/var/lib/postgresql/data -p 5432:5432 -e POSTGRES_PASSWORD=qwerty -e POSTGRES_DB=db postgres:11.1-alpine``` 
##### Application
```docker run --name prescriptions -p 8080:80 --link postgres:postgres -e DB_HOST=postgres -e DB_PORT=5432 -e DB_NAME=db -e DB_USER=postgres -e DB_PASSWORD=qwerty michuu93/prescriptions-manager```
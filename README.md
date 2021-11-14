# SpringBootTimeTable Manager

This repo is copy other my project TimeTableManager based on SpringBoot.
For more details information move to https://github.com/acdemeg/TimeTableManager 

## Test credentials for App
For ADMIN:
* login: admin@google.com
* password: admin_passw

For USER:
* login: joo@google.com
* password: joo_passw

# How to run app:
1. `docker-compose up -d`
2. `cd ./backside/backend/src`
3. `npx sequelize-cli db:migrate ->  npx sequelize-cli db:seed:all or restor from dump`
4. `Go to http://localhost:8000`
<!--  -->
## Requirements:
1. Node v12.x or higher
2. NPM v6.x or higher

## How to install requirements:
1. run in the console `./init.sh`

## Repo contains
1. Backend template: 'backend' folder
2. Frontend template: 'frontend folder
3. Database backups: 'ops' folder

docker-compose file uses postgres as database.
You can change db_user and db_password in docker-compose.yml file.

## How to use Docker:
Run all commands project root folder

### Start containers
`docker-compose up -d`
### Show logs containers
`docker-compose logs -f web`

## How to watch static:
`cd ./frontend && npm run watch`

# DB commands
## Make a dump
`docker-compose exec db sh -c 'exec pg_dump -U postgres time_tables > /backup/dump.sql'`

## Restore from the dump
`docker-compose exec db sh -c 'exec psql -U postgres time_tables < /backup/dump.sql'`

# Useful commands
## Clean all docker containers info
`docker-compose stop && docker-compose down --rmi local --volumes --remove-orphans`




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

1. `Move to src/main/frontend/ and run ./init.sh(for Linux), for Windows need complete all command step by step`
2. `Run "mvn package -DskipTests" command`
3. `Run "docker-compose up"`
4. `Move to ops/db-backups/ and run "restore" script, for Windows make as above mentioned`
5. `Move to` http://localhost:8002
<!--  -->

## Requirements:
1. Node v12.x or higher
2. NPM v6.x or higher
3. Docker, Docker-compose
4. Java, Maven


## How to use Docker:
Run all commands project root folder

docker-compose file uses postgres as database.
You can change db_user and db_password in docker-compose.yml file.

### Start containers
`docker-compose up -d`
### Show logs containers
`docker-compose logs -f web`

# Useful commands
## Clean all docker containers info
`docker-compose stop && docker-compose down --rmi local --volumes --remove-orphans`

## Make dump DB
`move to /ops/db-backups/ and run "make-dump" `






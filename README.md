# TimeTable Manager

This repo contain example project TimeTableManager based on next tech stack:
ReactJs, Redux, Scss, NodeJs, Sequelize, Koa2Js, Postgresql, Docker.

## Test credentials for App
For ADMIN:
* login: admin@google.com
* password: admin_passw

For USER:
* login: joo@google.com
* password: john_passw

## Description App

A list of schedules is available to users, they can book free slots.
Administrators have the ability to change schedules, confirm user reservations.

### User of system
It has:
1. `Name`
2. `Email`

### Schedule
Consist from:
1. `Title`
2. `Colums and rows`
3. `Time of action`
4. `Slot size: hour, day`

### Attribute of slot schedule
When creating a schedule, you need to set the slot configuration and what attributes the reservation will have. When booking, the user will have to set values ​​for them.

Attribute constist from:
1. `Title`
2. `Type: string, number, date`
3. `Flag, require(true or false)`

### Reservation
It has:
1. `Author`
2. `Time duration`
3. `Reservation status: Created, Accepted, Rejected`
4. `Attribute values`

### Notification
It has:
1. `Connect with Reservation`
2. `Type: Reservation Created, Accepted, Rejected`
3. `Status: Reade\Not Reade`

## Opportunities
The USER able to:
1. `Register now`
2. `Edit your profile data (name, email)`
3. `View a list of current schedules and a specific schedule`
4. `Book free slots`
5. `View Self Global Schedule: A list of self slots in all schedules.`
6. `View a list of self events that have already passed.`
7. `See messages in the user interface. For example:`
    * `"The slot has been booked"`
    * `"Booking successfully deleted"`
    * `"Your personal data has been updated"`
    * `etc.`
8. `Receive booking notifications:`
    * `Confirmed`
    * `Rejected`

The ADMINISTRATOR able to:
1. `Create a schedule`
2. `View user list (edit / delete startup profiles)`
3. `Confirm or decline user bookings. Have a user-friendly interface for viewing the        conflict of the reservation list.`
4. `View the global schedule for a specific user`



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




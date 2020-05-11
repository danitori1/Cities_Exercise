# Cities Exercise

## BUILT

This app is made with Angular, Spring Boot, MySQL and Docker

## REQUIREMENTS

- Node.js
- Java JDK 8
- MySQL
- Docker

## START

In the folder, run the command 'docker-compose up' and open the IP of your Docker container in the browser.

## APP

- In the main page (path "/") you will find the app presentation
- In cities page (path "/cities") there is a table with options like page size, page number and sort if you click on the headers title.
- API to check biggest sequence (Exercise a) on city ID in order ascent following N size parameter.
  Go to:
  "http://(YOUR_HOST):1111/api/cities/biggestSequence?N=(SIZE_OF_THE_SEQUENCE)"

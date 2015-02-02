# Websocket Server (JEE 7) and JS client

This project is very much in progress (just started).

## Frontend and Backend

### Backend
A JEE 7 WAR. Listens for WebSocket events and sends own events to
clients in the role of a WebSocket Server. Builds with Maven.

### Frontend
HTML5 app using JQuery and Bootstrap. Connects to backend and
subscribes to websocket messages. Builds with Grunt.js.

### Nginx sample conf
Nginx serves the html app and proxies calls to the backend.

## Installation and Setup notes

### Prerequisites
- ngnix webserver
- Grunt.js, Bower, Maven 3, Node, npm, Java 8

### Installing
See the READMEs for frontend and backend respectively.

### Running
```startAll.sh``` to run everything.

**Notes**

Make sure you move the default port away form 8080! See folder [nginx](nginx) for example conf.





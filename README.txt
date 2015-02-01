# Websocket Server (JEE 7) and JS client

This project is very much in progress (just started).

## Frontend and Backend

### Backend
A JEE 7 WAR. Listens for WebSocket events and sends own events to
clients in the role of a WebSocket Server. Builds with Maven.

### Frontend
HTML5 app using JQuery and Bootstrap. Connects to backend and
subscribes to websocket messages. Builds with Grunt.js.

### Ngnix sample conf
Ngnix serves the html app and proxies calls to the backend.









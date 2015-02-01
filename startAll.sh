#!/bin/bash
NGINX_HOME=/usr/local/etc/nginx
NGINX_SITES=$NGINX_HOME/sites-enabled
PIDFILE=/usr/local/var/run/nginx.pid

PORT=9999
DOCROOT=`pwd`/frontend/dist

# TODO: Parse this from the ./backend project files (jetty.xml)
SERVER=8080

if [ ! -d $NGINX_HOME ]
then
    echo "ERROR! Check location of NGINX_HOME. Current setting does not exist: $NGINX_HOME"
    exit 1
fi

if [ ! -d $NGINX_SITES ]
then
    echo "ERROR! Folder not found $NGINX_SITES"
    exit 2
fi


echo "Building fronted"
cd ./frontend
grunt build
cd ..

if [ -f $PIDFILE ]
then
    echo "Stopping nginx"
    nginx -s stop
fi

echo "Depolying site conf to $NGINX_SITES"
# Escape '/' so that it dosen't interfere with the sed replacement next
DIRESC=$(sed 's/[\/]/\\&/g' <<<"$DOCROOT");
rm -f $NGINX_SITES/site_.conf
cat nginx/site.conf | sed s/PORT/$PORT/g | sed s/DOCROOT/$DIRESC/g | sed s/SERVER/$SERVER/g > $NGINX_SITES/site_.conf

echo "Starting nginx"
nginx

echo "DOCROOT: $DOCROOT"
echo "URL: http://localhost:$PORT"

echo "Starting backend"
cd backend
mvn clean install jetty:run


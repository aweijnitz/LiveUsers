#!/bin/sh
mvn clean install
$WILDFLY_HOME/bin/jboss-cli.sh --connect --command="deploy --force ./target/peepingtom.war"

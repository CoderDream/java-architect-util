#!/usr/bin/env bash
mvn package -Dmaven.test.skip=true
java -jar target/notebook-service-1.1.jar

#!/usr/bin/env bash
mvn package -Dmaven.test.skip=true
java -jar target/gateway-service-1.0.jar

#!/bin/bash

echo Creating Kafka topics...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server kafka:9092 --create --topic asw.edipogram.enigmi --replication-factor 1 --partitions 4
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server kafka:9092 --create --topic asw.edipogram.connessioni --replication-factor 1 --partitions 4

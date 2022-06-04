#!/bin/bash

echo Creating some Kafka topics...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic asw.edipogram.enigmi --replication-factor 1 --partitions 1
docker exec -it $KAFKA_DOCKER kafka-topics.sh --bootstrap-server localhost:9092 --create --topic asw.edipogram.connessioni --replication-factor 1 --partitions 1


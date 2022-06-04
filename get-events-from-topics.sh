#!/bin/bash


KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

echo Ottengo gli eventi dal canale asw.edipogram.enigmi:
docker exec -it $KAFKA_DOCKER kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic asw.edipogram.enigmi --from-beginning
# CTRL+C -> passa agli eventi di asw.edipogram.connessioni

echo Ottengo gli eventi dal canale asw.edipogram.connessioni:
docker exec -it $KAFKA_DOCKER kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic asw.edipogram.connessioni --from-beginning
# CTRL+C termina lo script

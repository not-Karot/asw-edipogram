#!/bin/bash

echo Prova Produttore...

KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')

docker exec -it $KAFKA_DOCKER kafka-console-consumer.sh --bootstrap-server kafka:9092 --topic TopicA --from-beginning


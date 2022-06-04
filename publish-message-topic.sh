#!/bin/bash

echo Inizio Test...

#KAFKA_DOCKER=$(docker ps | grep kafka | grep -v zookeeper | awk '{print $1}')
#docker exec -it $KAFKA_DOCKER  kafka-topics.sh --bootstrap-server localhost:9092 --list
#docker exec -it $KAFKA_DOCKER kafka kafka-console-producer --bootstrap-server kafka:9092 --topic TopicA

docker exec -it kafka /bin/sh
cd /opt/bitnami/kafka/bin
kafka-console-producer.sh --broker-list localhost:9092 --topic TopicA
ls
echo Fine Test...
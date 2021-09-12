# Kafka Server Installation

Install Kafka server and its dependencies, I used the confluent distribution mentioned over [here](https://docs.confluent.io/platform/current/quickstart/ce-docker-quickstart.html#step-1-download-and-start-cp-using-docker). It uses docker and docker-compose is needed as prerequisite.
Note: Based on your RAM configuration, the docker container of either `broker` or `zookeeper` will go down, have to restart again.

# Kafka Topics
Below Topics are needed, create this in you Kafka server
* domains-raw
* domains-active
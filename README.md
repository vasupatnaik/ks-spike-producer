# About

This is a spike project to check `Kafka Streams` + `Spring Cloud Stream`. It has three micros services producer, stream processeor and a consumer. 
Note: Test cases were not considered for this spike.

# Pre-Requisite
Java 11   
Gradle   
[kafka-dependencies](kafka-dependencies.md)

# How to run locally

* Start your apached kafka server and have the topics created as mentioned in [kafka-dependencies](kafka-dependencies.md).  
* Start all three micros services  
    **  [ks-spike-producer](https://github.com/vasupatnaik/ks-spike-producer)  - To produce the stream data by inovking third party API  
    **  [ks-spike-stream-processor](https://github.com/vasupatnaik/ks-spike-stream-processor)  - To process this data into Kafka stream and publish this filtered data into a different topic.  
    **  [ks-spike-consumer](https://github.com/vasupatnaik/ks-spike-consumer)  - To consume the filtered stream data.
* Invoke this [endpoint](http://localhost:8080/v1/domains/search/facebook) to load the data stream to a topic to be processed by the micro service `ks-spike-stream-processor`

Note: if you run into the [issue](https://github.com/redisson/redisson/issues/1625) `Search domain query failed. Original hostname: 'xxxx' failed to resolve 'xxxxx' after Y queries`, try this [solution](https://github.com/netty/netty/issues/8880)

Cheers & Happy Coding
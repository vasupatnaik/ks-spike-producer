package com.spike.producer.resource;

import com.spike.common.dto.Domain;
import com.spike.common.dto.Domains;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class DomainService {
    private KafkaTemplate<String, Domain> kafkaTemplate;
    public DomainService(@Autowired KafkaTemplate<String, Domain> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void lookupDomains(String name){
        System.out.println("processData");
        Mono<Domains> domainsMono = WebClient.create()
                .get()
                .uri(String.format("https://api.domainsdb.info/v1/domains/search?domain=%s",name))
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Domains.class);

        domainsMono.subscribe(domains -> {
            domains.getDomains().parallelStream().forEach(domain -> {

                log.info("Raw Domain : {}",domain.getDomain());

                ListenableFuture<SendResult<String, Domain>> listenableFuture = kafkaTemplate.sendDefault(domain.getDomain(), domain);
                listenableFuture.addCallback(new ListenableFutureCallback<SendResult<String, Domain>>() {
                    @Override
                    public void onFailure(Throwable ex) {
                        log.info("Exception thrown : {}" ,ex.getMessage());
                    }

                    @Override
                    public void onSuccess(SendResult<String, Domain> result) {
                        log.info("Success : Domain is {}, Partion is {}", domain, result.getProducerRecord().partition());
                    }
                });
            });
        });

    }
}

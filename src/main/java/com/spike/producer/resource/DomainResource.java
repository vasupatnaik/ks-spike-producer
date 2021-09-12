package com.spike.producer.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
@Slf4j
public class DomainResource {

    private DomainService service;

    public DomainResource(DomainService service) {
        this.service = service;
    }

    @GetMapping(path = "/domains/search/{name}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void searchDomains(@PathVariable String name){
        log.info("Received Request for : {}", name);

        service.lookupDomains(name);
    }
}

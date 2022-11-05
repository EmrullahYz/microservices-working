package com.example.rest.webservices.restfullwebservices.controller;

import com.example.rest.webservices.restfullwebservices.bean.PersonV1;
import com.example.rest.webservices.restfullwebservices.bean.Name;
import com.example.rest.webservices.restfullwebservices.bean.PersonV2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionOfPerson(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionOfPerson(){
        return new PersonV2(new Name("Bob"," Marley"));
    }

    @GetMapping(path = "/person",params = "version=1")
    public PersonV1 getFirstVersionOfPersonWithParams(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping(path ="/person", params = "version=2")
    public PersonV2 getSecondVersionOfPersonWithParams(){
        return new PersonV2(new Name("Bob"," Marley"));
    }

    @GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getFirstVersionOfPersonWithHeader(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping(path = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionOfPersonWithHeader(){
        return new PersonV2(new Name("Bob"," Marley"));
    }

    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v1+json")
    public PersonV1 getFirstVersionOfPersonWithAccept(){
        return new PersonV1("Bob Marley");
    }

    @GetMapping(path = "/person/accept",produces = "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionOfPersonWithAccept(){
        return new PersonV2(new Name("Bob"," Marley"));
    }


}

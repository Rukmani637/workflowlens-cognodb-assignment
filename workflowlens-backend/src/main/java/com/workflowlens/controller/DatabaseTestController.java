package com.workflowlens.controller;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Session;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseTestController {

    private final Driver driver;
    public  DatabaseTestController(Driver driver){
        this.driver=driver;
    }

    @GetMapping("/api/test-db")
    public String testDatabase(){
        try(Session session=driver.session()){
            String result=session.run("RETURN 'CongoDB connection successful!' As message")
                    .single()
                    .get("message")
                    .asString();
            return result;
        }
    }


}

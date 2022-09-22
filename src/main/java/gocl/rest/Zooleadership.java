package gocl.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gocl.zookeeper.candidate.CurrentLeadership;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class Zooleadership {

    @Autowired
    private CurrentLeadership currentLeadership;

    @RequestMapping("/")
    public String leader() {
        log.info("leader invoked");
        if (this.currentLeadership.isLeader()) {
            return "yes";
        }
        return "no";
    }
    
}

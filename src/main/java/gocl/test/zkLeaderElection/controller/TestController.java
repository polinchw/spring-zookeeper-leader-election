package gocl.test.zkLeaderElection.controller;

import gocl.test.zkLeaderElection.zookeeper.candidate.CurrentLeadership;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Autowired
    private CurrentLeadership leadership;

    @GetMapping("/test")
    @ResponseBody public String test() {
        return "Is Leader ?  ... " + this.leadership.isLeader();
    }
}

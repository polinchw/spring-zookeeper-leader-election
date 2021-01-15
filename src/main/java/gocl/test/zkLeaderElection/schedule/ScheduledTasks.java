package gocl.test.zkLeaderElection.schedule;

import gocl.test.zkLeaderElection.zookeeper.candidate.CurrentLeadership;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ScheduledTasks {

    @Autowired
    private CurrentLeadership currentLeadership;

    @Scheduled(fixedDelay = 3000)
    public void scheduleTask1 () {
        if (this.currentLeadership.isLeader()) {
            log.info(" Starting scheduleTask1 " + System.currentTimeMillis());
        }
    }
}

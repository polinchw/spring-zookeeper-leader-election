package gocl.test.zkLeaderElection.zookeeper.candidate;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.integration.leader.Candidate;
import org.springframework.integration.leader.Context;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class CurrentLeadership implements Candidate {

    @Value( "${gocl.test.leader.role}" )
    private String leadershipRole;

    private Context leaderCtx;

    @Override
    public String getRole() {
        return this.leadershipRole;
    }

    @Override
    public String getId() {
        return UUID.randomUUID().toString();
    }

    @Override
    public void onGranted(Context ctx) throws InterruptedException {
        this.leaderCtx = ctx;
        log.info(" {} leadership granted ", this.getId());
    }

    @Override
    public void onRevoked(Context ctx) {
        this.leaderCtx = null;
        log.info(" {} leadership revoked ", this.getId());
    }

    public boolean isLeader() {
        return this.leaderCtx != null && this.leaderCtx.isLeader();
    }
}


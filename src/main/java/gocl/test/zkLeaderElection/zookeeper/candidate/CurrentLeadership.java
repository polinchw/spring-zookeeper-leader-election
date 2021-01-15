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

    private final String ID;

    public CurrentLeadership() {
        ID = UUID.randomUUID().toString();
    }

    @Override
    public String getRole() {
        return this.leadershipRole;
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onGranted(Context ctx) throws InterruptedException {
        // TODO: If there will be some extra time, it could be nice to keep synchronized the
        //  onGranted, onRevoked and isLeader functions
        this.leaderCtx = ctx;
        log.debug(" {} leadership granted ", this.getId());
    }

    @Override
    public void onRevoked(Context ctx) {
        this.leaderCtx = null;
        log.debug(" {} leadership revoked ", this.getId());
    }

    // TODO: May we check for different roles here?
    public boolean isLeader() {
        return this.leaderCtx != null && this.leaderCtx.isLeader();
    }
}


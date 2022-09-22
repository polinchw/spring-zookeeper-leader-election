package gocl.test.zkLeaderElection;

import gocl.test.zkLeaderElection.zookeeper.candidate.CurrentLeadership;
import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.zookeeper.config.LeaderInitiatorFactoryBean;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ZookeeperLeaderElectionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZookeeperLeaderElectionApplication.class, args);
	}

	@Value( "${gocl.test.leader.path}" )
	private String leadershipPath;

	@Bean
	public LeaderInitiatorFactoryBean currentLeadershipLeaderInitiator(
			CuratorFramework client, CurrentLeadership currentLeadership) {
		return new LeaderInitiatorFactoryBean()
				.setClient(client)
				.setPath(leadershipPath)
				.setCandidate(currentLeadership);
	}

}

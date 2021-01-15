package gocl.test.zkLeaderElection;

import gocl.test.zkLeaderElection.zookeeper.candidate.CurrentLeadership;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ZookeeperLeaderElectionApplicationTests {

	@Autowired
	private CurrentLeadership leadOne;

	@Test
	public void uniqueLeaderTest() throws Exception {
		CurrentLeadership leadTwo = createInstance();
		CurrentLeadership leadThree = createInstance();

		// validate only one leader
		Assertions.assertTrue(leadOne.isLeader() ^ leadTwo.isLeader() ^ leadThree.isLeader(),
			"There is more than one leader");
	}

	private CurrentLeadership createInstance() {
		SpringApplicationBuilder instance = new SpringApplicationBuilder(ZookeeperLeaderElectionApplication.class);
		instance.run();
		return instance.context().getBean(CurrentLeadership.class);
	}

}

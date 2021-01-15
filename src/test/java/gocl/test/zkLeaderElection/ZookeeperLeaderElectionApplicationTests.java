package gocl.test.zkLeaderElection;

import gocl.test.AbstractIntegrationTest;
import gocl.test.zkLeaderElection.zookeeper.candidate.CurrentLeadership;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.io.*;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.assertTrue;


@Slf4j
class ZookeeperLeaderElectionApplicationTests extends AbstractIntegrationTest {

	@Value("${spring.cloud.zookeeper.connect-string}")
	private String zookeeperConnectString;

	@SneakyThrows
	@Test
	void zookeeperIsRunning() {

		String [] addressParts = zookeeperConnectString.split(":");

		try (
				Socket socket = new Socket(addressParts[0], Integer.parseInt(addressParts[1]));
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream(), true)
		) {

			// send request
			out.print("stat");
			out.flush();

			// read response
			StringBuilder sb = new StringBuilder();
			String line;

			while((line = in.readLine()) != null) {
				sb.append(line).append(System.getProperty("line.separator"));
			}

			// check that version of Zookeeper is the same as in the container used
			final String response = sb.toString();
			assertTrue(response.contains("Zookeeper version: 3.6.2"));
			log.info(response);
		}
	}

	@Autowired
	private CurrentLeadership leadOne;

	@Test
	public void uniqueLeaderTest() throws Exception {
		CurrentLeadership leadTwo = createInstance("server.port=8081");
		CurrentLeadership leadThree = createInstance("server.port=8082");

		// validate only one leader
		Assertions.assertTrue(leadOne.isLeader() ^ leadTwo.isLeader() ^ leadThree.isLeader(),
			"There is more than one leader");
	}

	private CurrentLeadership createInstance(String properties) {
		SpringApplicationBuilder instance = new SpringApplicationBuilder(ZookeeperLeaderElectionApplication.class)
				.properties(properties);
		instance.run();
		return instance.context().getBean(CurrentLeadership.class);
	}

}

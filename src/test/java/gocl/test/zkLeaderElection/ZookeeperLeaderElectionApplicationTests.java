package gocl.test.zkLeaderElection;

import gocl.test.AbstractIntegrationTest;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;

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

	@Test
	void contextLoads() {
	}

}

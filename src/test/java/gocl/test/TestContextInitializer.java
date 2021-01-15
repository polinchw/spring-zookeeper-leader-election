package gocl.test;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.GenericContainer;

@Slf4j
@ContextConfiguration(initializers = TestContextInitializer.Initializer.class)
public class TestContextInitializer {


    public static GenericContainer zookeeper = new GenericContainer<>("zookeeper:3.6.2")
            .withExposedPorts(2181)
            .withEnv("ZOO_4LW_COMMANDS_WHITELIST","*");

    public static class Initializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @SneakyThrows
        @Override
        public void initialize(@NotNull ConfigurableApplicationContext ignored) {

            zookeeper.start();

            // the port returned will be random
            System.setProperty("ZOOKEEPER_SERVER", "localhost:" + zookeeper.getMappedPort(2181).toString());
        }
    }
}

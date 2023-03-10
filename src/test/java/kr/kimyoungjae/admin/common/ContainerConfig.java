package kr.kimyoungjae.admin.common;

import org.junit.ClassRule;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

import java.io.File;

public class ContainerConfig {

    @ClassRule
    protected static DockerComposeContainer env = new DockerComposeContainer(new File("src/test/resources/docker-compose-test.yml"))
            .waitingFor("test-mysql", Wait.forListeningPort());

    static {
        env.start();
    }

}

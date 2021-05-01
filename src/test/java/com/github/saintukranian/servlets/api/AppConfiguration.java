package com.github.saintukranian.servlets.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.test.context.TestConfiguration;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.images.builder.ImageFromDockerfile;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.nio.file.Paths;

@Slf4j
@TestConfiguration
public class AppConfiguration {

  static GenericContainer servletsContainer;

  @PostConstruct
  public void startDockerContainer() {
    servletsContainer = new GenericContainer(new ImageFromDockerfile().withDockerfile(Paths.get("./Dockerfile")));
    servletsContainer.addExposedPort(8080);
    servletsContainer.start();
  }

  @PreDestroy
  public void stopDockerContainer() {
    log.info("Stopping docker container...");
    servletsContainer.stop();
    log.info("Docker container has been stopped!");
  }

  public static String getUrl() {
    return "http://" + servletsContainer.getHost() + ":" + servletsContainer.getMappedPort(8080) + "/";
  }
}

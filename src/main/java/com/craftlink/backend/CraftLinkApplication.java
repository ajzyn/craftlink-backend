package com.craftlink.backend;

import com.craftlink.backend.infrastructure.config.AWSProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    AWSProperties.class,
})
public class CraftLinkApplication {

  public static void main(String[] args) {
    SpringApplication.run(CraftLinkApplication.class, args);
  }

}

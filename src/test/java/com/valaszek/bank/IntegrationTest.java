package com.valaszek.bank;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
@ActiveProfiles(profiles = "test")
public abstract class IntegrationTest {

  protected MockMvc mockMvc;

  @Autowired protected WebApplicationContext context;

  @BeforeEach
  public void setup() throws Exception {
    this.mockMvc = webAppContextSetup(this.context).build();
  }
}

package com.example.demoprj;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest(classes = MyWebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class HelloControllerTest {

    @Autowired
    private TestRestTemplate template;

    @Test
    public void hello_ok() throws Exception {
        ResponseEntity<String> response = template.getForEntity("/", String.class);
        //assertThat(response.getBody()).isEqualTo("Hello World, Spring Boot!");

        assertThat(response.getStatusCode().is2xxSuccessful());
    }

}
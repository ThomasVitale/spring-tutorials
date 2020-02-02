package com.thomasvitale.application.web;

import com.thomasvitale.application.Application;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OrganizationRestControllerTest {
    private static final String ENDPOINT = "/api/organizations";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Value("${multitenant.header}")
    private String tenantHeader;

    @Test
    public void getOrganizationsForTenantAcme() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(tenantHeader, "TENANT_ACME");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                buildUrlWithPort(ENDPOINT),
                HttpMethod.GET,
                entity,
                String.class
        );

        String expected = "[{\"name\":\"Acme Department 1\",\"organization\":\"Cartoon department\"},{\"name\":\"Acme Department 2\",\"organization\":\"Another cartoon department\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    @Test
    public void getOrganizationsForTenantArgus() throws JSONException {
        HttpHeaders headers = new HttpHeaders();
        headers.add(tenantHeader, "TENANT_ARGUS");
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                buildUrlWithPort(ENDPOINT),
                HttpMethod.GET,
                entity,
                String.class
        );

        String expected = "[{\"name\":\"Argus Division 1\",\"organization\":\"Secret division\"},{\"name\":\"Argus Division 2\",\"organization\":\"Another secret division\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String buildUrlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}

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
public class NoteRestControllerTest {
    private static final String ENDPOINT = "/api/notes";

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Value("${multitenancy.http-header}")
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

        String expected = "[{\"title\":\"Acme Note 1\",\"content\":\"Some funny note.\"},{\"title\":\"Acme Note 2\",\"content\":\"Another funny note.\"}]";

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

        String expected = "[{\"title\":\"Argus Note 1\",\"content\":\"Some secret note.\"},{\"title\":\"Argus Note 2\",\"content\":\"Another secret note.\"}]";

        JSONAssert.assertEquals(expected, response.getBody(), false);
    }

    private String buildUrlWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }
}

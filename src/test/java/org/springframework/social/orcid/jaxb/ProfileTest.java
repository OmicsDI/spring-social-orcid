package org.springframework.social.orcid.jaxb;

import java.net.URI;

import org.europepmc.springframework.social.orcid.jaxb.beans.Record;
import org.junit.Test;
import org.springframework.social.support.URIBuilder;
import org.springframework.web.client.RestTemplate;

/**
 * @author Yuci Gou
 *
 */
public class ProfileTest {
    @Test
    public void getProfile() {
        RestTemplate restTemplate = new RestTemplate();

        URI uri = URIBuilder.fromUri("https://pub.sandbox.orcid.org/v2.0/0000-0001-8160-1147/record").build();
        Record response = restTemplate.getForObject(uri, Record.class);

        System.out.println(response.getPerson().getName().getFamilyName().getValue());
    }
}

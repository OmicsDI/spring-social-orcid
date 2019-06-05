package uk.ac.ebi.ddi.social.orcid.api.impl;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import uk.ac.ebi.ddi.social.orcid.jaxb.beans.Record;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.http.MediaType.*;

/**
 * @author Yuci Gou
 *
 */
public class MessageOperationsImplTest extends AbstractOrcidApiTest {
    /**
     * https://members.orcid.org/api/tutorial/read-orcid-records
     */
    @Test
    public void getDirectMessage() {
        unauthorizedMockServer.expect(requestTo("https://pub.sandbox.orcid.org/v2.0/0000-0001-8160-1147/record"))
            .andExpect(method(GET))
            .andRespond(withSuccess(xmlResource("orcidProfile"), APPLICATION_XML));
        Record message = unauthorizedOrcid.messageOperations().getOrcidProfile("0000-0001-8160-1147", true);
        assertSingleOrcidProfile(message);
    }
    
    private void assertSingleOrcidProfile(Record profile) {
        System.out.println("Family name: " + profile.getPerson().getName().getFamilyName().getValue());
        assertEquals("Gou", profile.getPerson().getName().getFamilyName().getValue());
    }
}

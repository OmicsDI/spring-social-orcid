package uk.ac.ebi.ddi.social.orcid.api;

import org.springframework.social.ApiBinding;

/**
 * @author Yuci Gou
 *
 */
public interface OrcidApi extends ApiBinding {

	MessageOperations messageOperations();

}

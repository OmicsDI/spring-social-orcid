package org.europepmc.springframework.social.orcid.api;

import org.springframework.social.ApiBinding;

/**
 * @author Yuci Gou
 *
 */
public interface OrcidApi extends ApiBinding {

	MessageOperations messageOperations();

}

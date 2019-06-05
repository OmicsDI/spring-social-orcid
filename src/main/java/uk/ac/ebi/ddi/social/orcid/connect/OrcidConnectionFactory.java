package uk.ac.ebi.ddi.social.orcid.connect;

import uk.ac.ebi.ddi.social.orcid.api.OrcidApi;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;


/**
 * ORCID ConnectionFactory implementation.
 * 
 * @author Yuci Gou
 */
public class OrcidConnectionFactory extends OAuth2ConnectionFactory<OrcidApi> {
    
    public static final String PROVIDER_ID_ORCID = "orcid";
	/**
	 * Creates a OrcidConnectionFactory for the given application ID and secret
	 * 
	 * @param appId The application's App ID as assigned by ORCID 
	 * @param appSecret The application's App Secret as assigned by ORCID
	 */
	public OrcidConnectionFactory(String appId, String appSecret) {
		super(PROVIDER_ID_ORCID, new OrcidServiceProvider(appId, appSecret), new OrcidAdapter());
	}

}

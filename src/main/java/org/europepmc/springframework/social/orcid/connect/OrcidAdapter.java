package org.europepmc.springframework.social.orcid.connect;

import org.europepmc.springframework.social.orcid.api.OrcidApi;
import org.europepmc.springframework.social.orcid.jaxb.beans.Record;
import org.europepmc.springframework.social.orcid.utils.OrcidConfigBroker;
import org.europepmc.springframework.social.orcid.utils.StringUtility;
import org.springframework.social.ApiException;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;
import org.springframework.social.connect.UserProfileBuilder;

/**
 * ORCID ApiAdapter implementation.
 * 
 * @author Yuci Gou
 */
public class OrcidAdapter implements ApiAdapter<OrcidApi> {

	public boolean test(OrcidApi orcid) {
		try {
			orcid.messageOperations().getOrcidProfile("0000-0001-7155-7939", true);
			return true;
		} catch (ApiException e) {
			return false;
		}
	}

	public void setConnectionValues(OrcidApi orcidApi, ConnectionValues values) {
		Record profile = orcidApi.messageOperations().getOrcidProfile();
		values.setProviderUserId(profile.getOrcidIdentifier().getPath());
				
		String givenName = profile.getPerson().getName().getGivenNames().getValue();
		String familyName = profile.getPerson().getName().getFamilyName().getValue();
		String displayName = givenName;
		if (StringUtility.hasContent(familyName)) {
		    if (StringUtility.hasContent(displayName)) {
		        displayName += " ";
		        displayName += familyName;
		    } else {
		        displayName = familyName;
		    }
		}		
		values.setDisplayName(displayName);
		String orcidFrontendUrl = OrcidConfigBroker.getOrcidConfig().getFrontendUrl();
		// values.setProfileUrl("https://sandbox.orcid.org/" + profile.getOrcidIdentifier().getPath());
		String profileUrl = orcidFrontendUrl + profile.getOrcidIdentifier().getPath();
		values.setProfileUrl(profileUrl);
	}

	public UserProfile fetchUserProfile(OrcidApi orcidApi) {
	    Record profile = orcidApi.messageOperations().getOrcidProfile();
		return new UserProfileBuilder().setName(profile.getPerson().getName().getGivenNames().getValue()).
				setFirstName(profile.getPerson().getName().getGivenNames().getValue()).
				setLastName(profile.getPerson().getName().getFamilyName().getValue()).
				setEmail(null).setUsername(profile.getOrcidIdentifier().getPath()).build();
	}

	@Override
	public void updateStatus(OrcidApi api, String message) {
		// TODO Auto-generated method stub
	}
	
}

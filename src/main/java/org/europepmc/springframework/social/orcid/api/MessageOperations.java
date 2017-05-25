package org.europepmc.springframework.social.orcid.api;

import javax.xml.transform.dom.DOMSource;

import org.europepmc.springframework.social.orcid.jaxb.beans.Record;

/**
 * ORCID APIs
 * 
 * @author Yuci Gou
 *
 */
public interface MessageOperations {
	Record getOrcidProfile();
	Record getOrcidProfile(String orcidId, boolean isPublic);
		
	/**
	 * Add works to the ORCID record
	 * 
	 * @param orcidId
	 * @param document
	 * @return
	 */
	boolean addWorks(String orcidId, DOMSource document);
}

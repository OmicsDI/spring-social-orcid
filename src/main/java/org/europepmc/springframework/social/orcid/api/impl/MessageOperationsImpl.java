package org.europepmc.springframework.social.orcid.api.impl;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.Arrays;
import java.util.List;

import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.sax.SAXSource;

import org.europepmc.springframework.social.orcid.api.MessageOperations;
import org.europepmc.springframework.social.orcid.jaxb.beans.Record;
import org.europepmc.springframework.social.orcid.utils.OrcidConfigBroker;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * @author Yuci Gou
 *
 */
public class MessageOperationsImpl extends AbstractOrcidOperations implements MessageOperations {

	private final RestTemplate restTemplate;
	private RestTemplate pubRestTemplate;
	
	private String accessToken;
	
	public MessageOperationsImpl(OrcidApiImpl orcidTemplate, RestTemplate restTemplate, boolean authorized, String accessToken) {
		super(authorized);
		this.restTemplate = restTemplate;
		this.accessToken = accessToken;
		this.pubRestTemplate = new RestTemplate();

        final String proxyHost = System.getProperty("http.proxyHost");
        final String proxyPort = System.getProperty("http.proxyPort");

        if (proxyHost != null && proxyPort != null) {
            SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
            Proxy proxy= new Proxy(Proxy.Type.HTTP, new InetSocketAddress(proxyHost, Integer.parseInt(proxyPort)));
            requestFactory.setProxy(proxy);
            this.restTemplate.setRequestFactory(requestFactory);
            this.pubRestTemplate.setRequestFactory(requestFactory);
        }
	}

	@Override
	public Record getOrcidProfile(String orcidId, boolean isPublic) {
	    Assert.hasText(orcidId, "ORCID ID empty");
	    
	    String url;
	    RestTemplate restTmp;
	    if (isPublic) {
	        url = OrcidConfigBroker.getOrcidConfig().getPubApiUrl();
	        restTmp = pubRestTemplate;
	    } else {
	        url = OrcidConfigBroker.getOrcidConfig().getApiUrl();
	        restTmp = restTemplate;
	    }
		url += orcidId + "/record";

		// Set XML content type explicitly to force response in XML (If not spring gets response in JSON)
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

		ResponseEntity<Record> responseEntity = restTmp.exchange(url, HttpMethod.GET, entity, Record.class);;
		if (responseEntity == null) {
		    return null;
		}
		return responseEntity.getBody();		
	}

	@Override
	public Record getOrcidProfile() {
        requireAuthorization();
        Assert.hasText(accessToken, "Authorized but no access token!");
		String orcidId = OrcidInfo.getInstance().getOrcid(accessToken);
		return getOrcidProfile(orcidId, true);
	}

    @Override
    public boolean addWorks(String orcidId, DOMSource domSource) {
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        boolean gotSourceHttpMessageConverter = false;
        for (HttpMessageConverter<?> messageConverter: messageConverters) {
            if (messageConverter instanceof SourceHttpMessageConverter) {
                gotSourceHttpMessageConverter = true;
            }
        }
        if (!gotSourceHttpMessageConverter) {
            messageConverters.add(new SourceHttpMessageConverter<DOMSource>());
            restTemplate.setMessageConverters(messageConverters);
        }
        
        String url = OrcidConfigBroker.getOrcidConfig().getApiUrl();
        url += orcidId + "/orcid-works";        
        
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_XML));
        headers.set("Content-Type", "application/orcid+xml");

        HttpEntity<DOMSource> entity = new HttpEntity<DOMSource>(domSource, headers);
               
        ResponseEntity<SAXSource> orcidOutput;
        try {
            orcidOutput = restTemplate.exchange(url, HttpMethod.POST, entity, SAXSource.class);
        } catch (HttpClientErrorException e) {
            HttpStatus httpStatus = e.getStatusCode();
            if (HttpStatus.UNAUTHORIZED == httpStatus) {
                throw e;
            } else if(HttpStatus.CONFLICT == httpStatus){
                System.out.println("The queried Orcid Account is locked. Can't retrieve data. Don't worry.");
                throw e;
            } else {
                System.out.println("URL in question: " + url);
                System.out.println("Exception getting ORCID document: " + e.getMessage());
                throw e;
            }
        } catch (Exception e) {
            System.out.println("URL in question: " + url);
            System.out.println("Exception getting ORCID document: " + e.getMessage());
            throw e;
        }
        
        return orcidOutput != null && orcidOutput.getStatusCode() != null && orcidOutput.getStatusCode().value() == HttpStatus.CREATED.value(); 
    }
}

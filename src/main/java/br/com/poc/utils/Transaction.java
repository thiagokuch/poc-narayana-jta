/*package br.com.poc.utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jboss.jbossts.star.util.TxLinkNames;
import org.jboss.jbossts.star.util.TxMediaType;
import org.jboss.jbossts.star.util.TxSupport;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@SuppressWarnings("deprecation")
public class Transaction {
    private static final String URL_COORDINATOR = "http://172.17.0.2:8080/rest-at-coordinator/tx/transaction-manager";

    public static Link getLink(List<Link> links, String relation) {
        for (Link link : links)
            if (link.getRel().equals(relation))
                return link;

        return null;
    }
    
    public static List<Link> beginTxn() throws IOException {
    	RestTemplate restTemplate = new RestTemplate();
    	HttpHeaders headers = new HttpHeaders();
    	headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

    	ResponseEntity<String> response = restTemplate.postForEntity(URL_COORDINATOR, new HttpEntity<String>("", headers), String.class);
    	
    	return LinkBuilder.build(response.getHeaders().get("Link"));
    }
    
    public static int endTxn(List<Link> links) throws IOException {
    	MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
    	headers.add("Content-Type", TxMediaType.TX_STATUS_MEDIA_TYPE);

    	RestTemplate restTemplate = new RestTemplate();
    	
    	ResponseEntity<String> response = restTemplate.postForEntity(getLink(links, TxLinkNames.TERMINATOR).getUrl(), new HttpEntity<String>("", headers), String.class);
    	return response.getStatusCodeValue();
    }
    
   public static String enlist(String enlistUrl, int wid, HttpServletRequest request) {
    	Map<String, String> reqHeaders = new HashMap<String, String>();
    	reqHeaders.put("Link", LinkBuilder.combineLinks(LinkBuilder.fromUri(request.getRequestURL().toString(), wid)));
    	return new TxSupport().httpRequest(new int[] {HttpURLConnection.HTTP_CREATED}, enlistUrl, "POST", TxMediaType.POST_MEDIA_TYPE, null, null, reqHeaders);
    }
}
*/
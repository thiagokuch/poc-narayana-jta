package br.com.poc.controller;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HEAD;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.jboss.jbossts.star.util.TxMediaType;
import org.jboss.jbossts.star.util.TxStatus;
import org.jboss.jbossts.star.util.TxSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.poc.service.AccountService;

@SuppressWarnings("deprecation")
@Path("service")
@Component
public class AccountController {

	private static final String URL_COORDINATOR = "http://172.17.0.3:8080/rest-at-coordinator/tx/transaction-manager";
	private static AtomicInteger workId = new AtomicInteger(1);
	
	@Autowired
	private AccountService service;
	
	@GET
	@Path("create")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    public Response create(@Context UriInfo info, @QueryParam(value="enlistURL") String enlistURL) {
        try {
        	new TxSupport().enlistParticipant(enlistURL, new TxSupport().makeTwoPhaseAwareParticipantLinkHeader(info.getBaseUri() + info.getPath(), String.valueOf(workId.incrementAndGet()), null));
        	this.service.create();
            
            return Response.ok().build();
            
        } catch (Exception e){
        	e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_PRECON_FAILED).build();
        }
    }
	
	@GET
	@Path("error")
	 @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Response error(@Context UriInfo info, @QueryParam(value="enlistURL") String enlistURL) {
        try {
            new TxSupport().enlistParticipant(enlistURL, new TxSupport().makeTwoPhaseAwareParticipantLinkHeader(info.getBaseUri() + info.getPath(), String.valueOf(workId.incrementAndGet()), null));
            
            /* force error */
            List<Long> list = new ArrayList<Long>();
            list.get(0);
            
            return Response.ok().build();
            
        } catch (Exception e){
        	e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_PRECON_FAILED).build();
        }
    }
    
	@GET
	@Path("createAll")
	@Transactional(propagation=Propagation.REQUIRES_NEW)
    public Response createAll(@Context UriInfo info, @QueryParam(value="enlistURL") String enlistURL) {
        try {
        	TxSupport txn = new TxSupport(URL_COORDINATOR);
        	txn.startTx();
        	new TxSupport().enlistParticipant(enlistURL, txn.makeTwoPhaseAwareParticipantLinkHeader(info.getBaseUri() + info.getPath(), String.valueOf(workId.incrementAndGet()), null));
        	this.service.create();
            
        	txn.commitTx();
        	
            return Response.ok(txn.getStatus()).build();
            
        } catch (Exception e){
        	e.printStackTrace();
            return Response.status(HttpURLConnection.HTTP_PRECON_FAILED).build();
        }
    }
	
	
    @GET
	@Path("transaction")
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public int transaction (@Context UriInfo info) throws Exception {
    	TxSupport txn = new TxSupport(URL_COORDINATOR);
    	txn.startTx();

    	txn.httpRequest(new int[] {HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_PRECON_FAILED}, "http://172.17.0.1:8080/service/create" + "?enlistURL=" + txn.getDurableParticipantEnlistmentURI(), "GET", TxMediaType.PLAIN_MEDIA_TYPE, null, null);
    	//txn.httpRequest(new int[] {HttpURLConnection.HTTP_OK, HttpURLConnection.HTTP_PRECON_FAILED}, "http://localhost:8080/service/error" + "?enlistURL=" + txn.getDurableParticipantEnlistmentURI(), "GET", TxMediaType.PLAIN_MEDIA_TYPE, null, null);

    	if(txn.getStatus() == HttpURLConnection.HTTP_PRECON_FAILED) {
    		txn.rollbackTx();
    		return txn.getStatus();
    	}

    	txn.commitTx();
    	
    	return txn.getStatus();
    }
    
    @PUT
    @Path("/create/{wId}/terminator")
    public Response terminate(@PathParam("wId") @DefaultValue("")String wId, String content) throws Exception {
        System.out.println("Service: PUT request to terminate url: wId=" + wId + ", status:=" + content);
        TxStatus status = TxSupport.toTxStatus(content);

       /* if (status.isPrepare()) {
            System.out.println("Service: preparing");
        } else if (status.isCommit()) {
            if (wId.equals("")) {
                System.out.println("Service: Halting VM during commit of work unit wId=" + wId);
                Runtime.getRuntime().halt(1);
            }
            System.out.println("Service: committing");
        } else if (status.isAbort()) {
            System.out.println("Service: aborting");
        } else {
            System.out.println("Service: invalid termination request");
            return Response.status(HttpURLConnection.HTTP_BAD_REQUEST).build();
        }*/
        
        //((JtaTransactionManager) transactionManager).commit(status);;
        
        return Response.ok(TxSupport.toStatusContent(status.name())).build();
    }


    @HEAD
    @Path("/create/{pId}/participant")
    public Response getTerminator(@Context UriInfo info, @PathParam("pId") @DefaultValue("")String wId) {
    	 String serviceURL = info.getBaseUri() + info.getPath();
         String linkHeader = new TxSupport().makeTwoPhaseAwareParticipantLinkHeader(serviceURL, false, wId, null);

         return Response.ok().header("Link", linkHeader).build();
    }
}

package com.reviewboard.api;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.reviewboard.api.model.DiffComment;
import com.reviewboard.api.model.ReviewRequest;
import com.reviewboard.api.model.ReviewResource;
import com.reviewboard.util.DateUtil;
import com.reviewboard.util.JAXBHelper;

public class ReviewBoardApi {
	private static Logger logger = Logger.getLogger(ReviewBoardApi.class);

	public String API = "/api";
	public String REVIEW_REQUEST = "/review-requests";
	public String REVIEW_RESOURCE = "/reviews";
	public String DIFF_COMMENT = "/diff-comments";
	public String REVIEW_BOARD_API = null;
	public String REVIEW_BOARD_API_REVIEW_REQUEST = null;
	
	private final String MAX_RESULT_KEY="max-results";
	public static final Integer MAX_RESULT=10000;
	
	private final String HOST;

	public ReviewBoardApi(String host) {
		this.HOST = host;
		REVIEW_BOARD_API = host + API;
		
		logger.info("Review Board Page"+REVIEW_BOARD_API);
		
	}
	public String getUrlReviewRequests(){
		return REVIEW_BOARD_API_REVIEW_REQUEST = REVIEW_BOARD_API + REVIEW_REQUEST;
	}
	public String getUrlReviewRequest(Long reviewRequestId){
		return getUrlReviewRequests() +"/"+ reviewRequestId;
	}
	public String getUrlReviewResource(Long reviewRequestId){
		return getUrlReviewRequests()+"/"+reviewRequestId+REVIEW_RESOURCE;
	}
	public String getUrlDiffComments(Long reviewRequestId,Long reviewResourceId){
		return getUrlReviewResource(reviewRequestId)+"/"+reviewResourceId+DIFF_COMMENT;
	}
	
	public List<ReviewRequest> listAllReviewRequest(Date dateStart,Date dateEnd,String reviewStatus,String user,Integer maxResult) throws Exception {
		logger.info("#Listing All Reviews");
		String dateStartStr=null;
		if(dateStart!=null){
			dateStartStr = DateUtil.toString(dateStart);
		}
		logger.info("DateStart 	:"+dateStartStr);
		String dateEndStr=null;

		if(dateEnd!=null){
			dateEndStr = DateUtil.toString(dateEnd);
		}
		logger.info("DateEnd 	:"+dateEndStr);
		logger.info("ReviewStatus :"+reviewStatus);
		logger.info("User :"+user);
		
		Map<String,String> map=new HashMap<String,String>();
		map.put("status", reviewStatus);
		map.put("time-added-from", dateStartStr);
		map.put("time-added-to", dateEndStr);
		map.put("from-user", user);
		
		map.put(MAX_RESULT_KEY, (maxResult!=null?maxResult.toString():MAX_RESULT.toString()));

		String xmlContent = executeApi(getUrlReviewRequests(),map);

		List<ReviewRequest> processAllReviewRequest = processAllReviewRequest(xmlContent);
		
		return processAllReviewRequest;
	}
	
	private List<ReviewRequest> processAllReviewRequest(String xmlContent) throws Exception {
		//logger.info(content);
		List<ReviewRequest> reviewRequestList = JAXBHelper.readReviewRequestsFromXmlContent(xmlContent);
		logger.info("reviewRequestList Size:"+(reviewRequestList!=null?reviewRequestList.size():0));
		return reviewRequestList;		
	}
	public List<ReviewRequest> listAllReviewRequestFull(Date dateStart,Date dateEnd,String reviewStatus,String user, Integer maxResult) throws Exception {
		logger.info("#Listing All Reviews Request with Full Information");
		List<ReviewRequest> reviewRequestList = listAllReviewRequest(dateStart, dateEnd, reviewStatus, user,maxResult);
	
		int iReviewRequestList=0;
		for (ReviewRequest reviewRequest : reviewRequestList) {
			Long reviewRequestId = reviewRequest.getId();
			logger.debug("* Listing All Review Resouce from Review Request :"+reviewRequest.getSummary());
			
			ReviewRequest reviewRequestPopulated = getReviewRequest(reviewRequest);
			
			reviewRequestList.set(iReviewRequestList,reviewRequestPopulated);
			iReviewRequestList++;
		}
		
		return reviewRequestList;		
	}
	public ReviewRequest getReviewRequest(ReviewRequest reviewRequest) throws Exception{
		
		if(reviewRequest==null){
			throw new Exception("Invalid Review Request");
		}
		logger.info("Getting ReviewRequest by Object");
		
		String reviewResouceContent=executeApi(getUrlReviewResource(reviewRequest.getId()));
		List<ReviewResource> reviewResouceList = JAXBHelper.readReviewResourceFromXmlContent(reviewResouceContent);
		logger.info("reviewResouceList Size:"+(reviewResouceList!=null?reviewResouceList.size():0));
		int iReviewResouceList=0;
		for (ReviewResource reviewResource : reviewResouceList) {
			Long reviewResourceId=reviewResource.getId();
			logger.info("** Listing All Diff Comment from Review Resouce Id :"+reviewResource.getId());
			try{
				String diffComments=executeApi(getUrlDiffComments(reviewRequest.getId(), reviewResourceId));
				List<DiffComment> diffCommentList = JAXBHelper.readDiffCommentFromXmlContent(diffComments);
				logger.info("diffCommentList Size:"+(diffCommentList!=null?diffCommentList.size():0));
				reviewResource.setDiffComments(diffCommentList);
			}catch(Exception e){
				logger.error(e.getMessage(),e);
			}
			
			reviewResouceList.set(iReviewResouceList, reviewResource);
			iReviewResouceList++;
		}
		reviewRequest.setReviewResouces(reviewResouceList);
		return reviewRequest;
		
	}
	public ReviewRequest getReviewRequest(Long reviewRequestId) throws Exception{
		logger.info("Getting ReviewRequest by Id :"+reviewRequestId);
		
		String reviewRequestContent=executeApi(getUrlReviewRequest(reviewRequestId));
		ReviewRequest reviewRequest = JAXBHelper.readReviewRequestFromXmlContent(reviewRequestContent);
		
		reviewRequest=getReviewRequest(reviewRequest);
		
		return reviewRequest;
	}
	public String executeApi(String api) throws Exception {
		return executeApi(api,null);		
	}
	
	public String executeApi(String api,Map<String,String> map) throws Exception {
	
		StringBuilder builder = new StringBuilder();
		String params="";
		if(map!=null){
			logger.info("Setting the map params");
			Set<String> keySet = map.keySet();
			for (String key : keySet) {
				String value = map.get(key);
				if(value!=null){
					logger.info("Key: "+key+" Value: "+value);
					params=params+(key+"="+value)+"&";
					//conn.setRequestProperty(key, value);					
				}
			}
		}
		
		api=api+"?"+params;
		
		logger.debug("Executing the ReviewBoard API["+api+"]");
		
		URL url = new URL(api);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		conn.setRequestProperty("Accept", "application/xml");

		
		if (conn.getResponseCode() == 500) {
			throw new RuntimeException("Failed : HTTP error code : "+ conn.getResponseCode() + "-" +conn.getResponseMessage() +"- There was a server-side error when processing the request. This is usually a bug in Review Board.");
		}
		else if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ conn.getResponseCode() + "-" +conn.getResponseMessage());
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

		String output;
		logger.debug("Getting Output from Server ....");
		while ((output = br.readLine()) != null) {
			builder.append(output);
		}

		conn.disconnect();
		
		return builder.toString();
	}

	public String getHOST() {
		return HOST;
	}
}

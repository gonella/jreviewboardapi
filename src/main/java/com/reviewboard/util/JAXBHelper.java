package com.reviewboard.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.reviewboard.api.model.DiffComment;
import com.reviewboard.api.model.ReviewRequest;
import com.reviewboard.api.model.ReviewResource;
import com.reviewboard.api.model.reviewrequest.Array;
import com.reviewboard.api.model.reviewrequest.ArrayElement;
import com.reviewboard.api.model.reviewrequest.Item;
import com.reviewboard.api.model.reviewrequest.Links;
import com.reviewboard.api.model.reviewrequest.Submitter;


public class JAXBHelper {

	private static Logger logger = Logger.getLogger(JAXBHelper.class);

	public static void main(String[] args) throws Exception {

		/*List<DiffComment> list1 = readDiffCommentFromXmlFile("tools/JReviewBoardApi/config/RB_DiffComment_Result_VCEM.xml");
		for (DiffComment diffComment : list1) {
			logger.info(diffComment.getContent());
		}
		List<ReviewResource> list2 = readReviewResourceFromXmlFile("tools/JReviewBoardApi/config/RB_ReviewResource_Result_VCEM.xml");
		for (ReviewResource reviewResource : list2) {
			logger.info(reviewResource.getContent());
		}
*/
		List<ReviewRequest> list3 = readReviewRequestsFromXmlFile("tools/JReviewBoardApi/config/RB_ReviewRequests_Result_VCEM.xml");
		for (ReviewRequest reviewItem : list3) {
			logger.info(reviewItem.toString());
		}

	}

	public static List<ReviewRequest> readReviewRequestsFromXmlFile(String fileName)
			throws Exception {

		File file = new File(fileName);

		String content = FileUtils.readFileToString(file);

		List<ReviewRequest> list = readReviewRequestsFromXmlContent(content);

		return list;
	}
	public static ReviewRequest readReviewRequestFromXmlFile(String fileName)
			throws Exception {

		File file = new File(fileName);

		String content = FileUtils.readFileToString(file);

		ReviewRequest review = readReviewRequestFromXmlContent(content);

		return review;
	}
	public static List<ReviewResource> readReviewResourceFromXmlFile(String fileName)
			throws Exception {

		File file = new File(fileName);

		String content = FileUtils.readFileToString(file);

		List<ReviewResource> list = readReviewResourceFromXmlContent(content);

		return list;
	}
	public static List<DiffComment> readDiffCommentFromXmlFile(String fileName)
			throws Exception {

		File file = new File(fileName);

		String content = FileUtils.readFileToString(file);

		List<DiffComment> list = readDiffCommentFromXmlContent(content);

		return list;
	}

	public static List<ReviewRequest> readReviewRequestsFromXmlContent(
			String xmlContent) {
		logger.info("Reading the Review Requests Content");
		com.reviewboard.api.model.reviewrequests.Rsp rsp = null;
		List<ReviewRequest> list = new ArrayList<ReviewRequest>();

		JAXBContext ctx;
		try {
			InputStream is = new ByteArrayInputStream(
					xmlContent.getBytes("UTF-8"));

			ctx = JAXBContext.newInstance(new Class[] { com.reviewboard.api.model.reviewrequests.Rsp.class });
			Unmarshaller um = ctx.createUnmarshaller();
			rsp = (com.reviewboard.api.model.reviewrequests.Rsp) um.unmarshal(is);
			com.reviewboard.api.model.reviewrequests.Array reviewRequests=null;
			if(rsp!=null){
				reviewRequests = rsp.getReviewRequests();
			}
			com.reviewboard.api.model.reviewrequests.ArrayElement array=null;
			if(reviewRequests!=null){
				array = reviewRequests.getArray();
			}
			List<com.reviewboard.api.model.reviewrequests.Item> itemList=null;
			if(array!=null){
				itemList = array.getItem();
			}
			if(itemList!=null){
				for (com.reviewboard.api.model.reviewrequests.Item item : itemList) {
					
					ReviewRequest decodeReviewRequest = ReviewRequestsHelper
							.decode(item);
					logger.info("Item Decoded : "+decodeReviewRequest.getSummary());
					list.add(decodeReviewRequest);
				}
				logger.info("ReviewRequests decoded");
			}
		} catch (Exception e) {
			logger.info("Erro in unmashall", e);
		}
		return list;
	}

	public static List<ReviewResource> readReviewResourceFromXmlContent(
			String xmlContent) {
		logger.info("Reading the Review Resource Content");
		com.reviewboard.api.model.reviewresource.Rsp rsp = null;
		List<ReviewResource> list = new ArrayList<ReviewResource>();

		JAXBContext ctx;
		try {
			InputStream is = new ByteArrayInputStream(
					xmlContent.getBytes("UTF-8"));

			ctx = JAXBContext.newInstance(new Class[] { com.reviewboard.api.model.reviewresource.Rsp.class });
			Unmarshaller um = ctx.createUnmarshaller();
			rsp = (com.reviewboard.api.model.reviewresource.Rsp) um.unmarshal(is);
			com.reviewboard.api.model.reviewresource.Reviews reviews=null;
			if(rsp!=null){
				reviews = rsp.getReviews();
			}
			com.reviewboard.api.model.reviewresource.Array array=null;
			if(reviews!=null){
				array = reviews.getArray();
			}
			List<com.reviewboard.api.model.reviewresource.Item> listItem=null;
			if(array!=null){
				listItem = array.getItem();
			}
			if(listItem!=null){
				for (com.reviewboard.api.model.reviewresource.Item item : listItem) {
					ReviewResource decodeReviewResource = ReviewResourceHelper.decode(item);
					list.add(decodeReviewResource);
				}			
				logger.info("ReviewResource(s) decoded");
			}
		} catch (Exception e) {
			logger.info("Erro in unmashall", e);
		}
		return list;
	}
	
	public static List<DiffComment> readDiffCommentFromXmlContent(
			String xmlContent) {
		logger.info("Reading the DiffComment Content");
		com.reviewboard.api.model.diffcomment.Rsp rsp = null;
		List<DiffComment> list = new ArrayList<DiffComment>();
		logger.info(xmlContent);
		JAXBContext ctx;
		try {
			InputStream is = new ByteArrayInputStream(
					xmlContent.getBytes("UTF-8"));

			ctx = JAXBContext.newInstance(new Class[] { com.reviewboard.api.model.diffcomment.Rsp.class });
			Unmarshaller um = ctx.createUnmarshaller();
			rsp = (com.reviewboard.api.model.diffcomment.Rsp) um.unmarshal(is);
			
			com.reviewboard.api.model.diffcomment.DiffComments diffComments=null;
			if(rsp!=null){
				diffComments = rsp.getDiffComments();
			}
			com.reviewboard.api.model.diffcomment.Array array=null;
			if(diffComments!=null){
				array = diffComments.getArray();
			}
			List<com.reviewboard.api.model.diffcomment.Item> listItem=null;
			if(array!=null){
				listItem = array.getItem();
			}
			if(listItem!=null){
				for (com.reviewboard.api.model.diffcomment.Item item : listItem) {
					DiffComment decoded = DiffCommentHelper.decode(item);
					list.add(decoded);
				}
				
				logger.info("ReviewResource(s) decoded");
			}
		} catch (Exception e) {
			logger.info("Erro in unmashall", e);
		}
		return list;
	}

	public static ReviewRequest readReviewRequestFromXmlContent(
			String xmlContent) {
		logger.info("Reading the Review Request Content");
		com.reviewboard.api.model.reviewrequest.Rsp rsp = null;
		ReviewRequest reviewRequest=null;
		JAXBContext ctx;
		try {
			InputStream is = new ByteArrayInputStream(
					xmlContent.getBytes("UTF-8"));

			ctx = JAXBContext.newInstance(new Class[] { com.reviewboard.api.model.reviewrequest.Rsp.class });
			Unmarshaller um = ctx.createUnmarshaller();
			rsp = (com.reviewboard.api.model.reviewrequest.Rsp) um.unmarshal(is);
			
			if(rsp!=null){
				com.reviewboard.api.model.reviewrequest.ReviewRequest rr = rsp.getReviewRequest();
			
				if(rr!=null){
					reviewRequest=new ReviewRequest();
					reviewRequest.setSummary(rr.getSummary());
					reviewRequest.setTimeAdded(DateUtil.toDate(rr.getTimeAdded()));
					reviewRequest.setBranch(rr.getBranch());
					reviewRequest.setDescription(rr.getDescription());
					reviewRequest.setId(rr.getId().longValue());
					reviewRequest.setStatus(rr.getStatus());
					
					Links links = rr.getLinks();
					if(links!=null){
						Submitter submitter = links.getSubmitter();
						if(submitter!=null){
							reviewRequest.setSubmitter(submitter.getTitle());
						}
					}
					Array targetPeople = rr.getTargetPeople();
					if(targetPeople!=null){
						ArrayElement array = targetPeople.getArray();
						if(array!=null){
							Item item = array.getItem();
							
							if(item!=null){
								//reviewRequest.setReviewer(item.getTitle());
							}
							
						}
					}
				}
			}
			
			logger.info("ReviewRequest decoded");
			
		} catch (Exception e) {
			logger.info("Erro in unmashall", e);
		}
		return reviewRequest;
	}
	
}

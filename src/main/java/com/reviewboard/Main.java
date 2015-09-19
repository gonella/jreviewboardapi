package com.reviewboard;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.reviewboard.api.ReviewBoardApi;
import com.reviewboard.api.model.ReviewRequest;
import com.reviewboard.api.model.ReviewStatus;
import com.reviewboard.util.DateUtil;

public class Main {
    private static Logger logger = Logger.getLogger(Main.class);


    public static void main(String arg[]) throws Exception{

        String host = "http://demo.reviewboard.org";
        ReviewBoardApi reviewBoardApi = new ReviewBoardApi(host);

        /*List<ReviewRequest> listReviewRequest = reviewBoardApi.listAllPedingReviewRequestFull();
		for (ReviewRequest reviewItem : listReviewRequest) {
			logger.info(reviewItem);
		}*/

        /*ReviewRequest reviewRequest = reviewBoardApi.getReviewRequest(new Long(46));
		logger.info(reviewRequest);*/
        Date dateStart = DateUtil.toSimple("2015-05-15");
        Date dateEnd = DateUtil.toSimple("2015-10-31");
        //String user="joker.selloff@gmail.com";
        String user=null;
        List<ReviewRequest> listAllReviewRequest = reviewBoardApi.listAllReviewRequestFull(
                dateStart, dateEnd, ReviewStatus.pending.name()
                ,user
                ,1000);
        for (ReviewRequest reviewRequest : listAllReviewRequest) {
            logger.info(" > "+reviewRequest.getSummary());
            List<String> bugsClosed = reviewRequest.getBugsClosed();
            for (String string : bugsClosed) {
                logger.info("Bug :"+string);
            }

            List<String> groups = reviewRequest.getTargetGroups();
            for (String string : groups) {
                logger.info("Groups :"+string);
            }

            List<String> peoples = reviewRequest.getTargetPeople();
            for (String string : peoples) {
                logger.info("People :"+string);
            }


            /*List<ReviewResource> reviewResouces = reviewRequest.getReviewResouces();
			logger.info("Review Resource Size :"+reviewResouces.size());
			for (ReviewResource reviewResource : reviewResouces) {

				logger.info("Content :"+reviewResource.getContent());
				List<DiffComment> diffComments = reviewResource.getDiffComments();
				logger.info("diffComments Size :"+diffComments.size());
				for (DiffComment diffComment : diffComments) {
					logger.info("diffComment :"+diffComment.getContent());

				}

			}*/



        }

    }


}

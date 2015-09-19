
#Introduction

JReviewBoardApi:
This is a Java API to have access to Review Board(http://www.reviewboard.org) data. This API will allow the developer to read all reviews and comments. This APIS are not prepared to write in the review board.
The initial proposal is to getting all review information and then it makes available to collect metrics on that.
SUPPORT Review board 1.6.6

![Alt text](https://www.reviewboard.org/static/rbsite/images/logo@2x.40d02c2ae94b.png "Reviewboard - Code review")


READ ONLY OPERATIONs

Libraries needed:
JAXB(http://jaxb.java.net/ - Version 2.2.x)
IO COMMON(http://commons.apache.org/io/ Version 2.0.1)
LOG4j(http://logging.apache.org/log4j/ Version 1.2.1.5)

#How to Use the JReviewBoardAPI

##How to Use:
It was published only apis to read from Review Board. Just pass the review board(the project host).
ReviewBoardApi reviewBoardApi = new ReviewBoardApi("http://demo.reviewboard.org");
APIs Available so far:
```
public List<ReviewRequest> listAllReviewRequest(Date dateStart,Date dateEnd,String 
reviewStatus,String user,Integer maxResult) throws Exception
public List<ReviewRequest> listAllReviewRequestFull(Date dateStart,Date dateEnd,String reviewStatus,String user, Integer maxResult) throws Exception
public ReviewRequest getReviewRequest(ReviewRequest reviewRequest) throws Exception
public ReviewRequest getReviewRequest(Long reviewRequestId) throws Exception
public String executeApi(String api) throws Exception
public String executeApi(String api,Map<String,String> map) throws Exception
```
1.In the method(executeApi), you can pass the request parameters(fields) as described by Review Board Web API, see the example in the link. 2. You can accurate the results using these fields. http://www.reviewboard.org/docs/manual/dev/webapi/2.0/resources/review-request/
Development:
In case of development, you just need to checkout the code as a Java Project in Eclipse.

##A full example to retrieve reviews from ReviewBoard in Java

This is a simple example to retrieve reviews from the ReviewBoard demo. Basically we will do some read operation, with the period.
```
package com.reviewboard;
import java.util.Date;
import java.util.List;
import org.apache.log4j.Logger;
import com.reviewboard.api.ReviewBoardApi;
import com.reviewboard.api.model.DiffComment;
import com.reviewboard.api.model.ReviewRequest;
import com.reviewboard.api.model.ReviewResource;
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
                logger.info(reviewRequest);
                */
                
                Date dateStart = DateUtil.toSimple("2012-05-15");
                Date dateEnd = DateUtil.toSimple("2012-05-31");

                String user=null;
                List<ReviewRequest> listAllReviewRequest = reviewBoardApi.listAllReviewRequestFull(
                                dateStart, dateEnd, ReviewStatus.pending.name()
                                ,user
                                ,1000);
                for (ReviewRequest reviewRequest : listAllReviewRequest) {
                        logger.info(" > "+reviewRequest.getSummary());
                        
                        logger.info("Sub :"+reviewRequest.getSubmitter());
                        
                        
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
                        
                        
                        List<ReviewResource> reviewResouces = reviewRequest.getReviewResouces();
                        logger.info("Review Resource Size :"+reviewResouces.size());
                        for (ReviewResource reviewResource : reviewResouces) {
                                
                                logger.info("Content :"+reviewResource.getContent());
                                List<DiffComment> diffComments = reviewResource.getDiffComments();
                                logger.info("diffComments Size :"+diffComments.size());
                                for (DiffComment diffComment : diffComments) {
                                        logger.info("diffComment :"+diffComment.getContent());

                                }
                                
                        }
                        
                        
                        
                }
                
        }
        
        }
```

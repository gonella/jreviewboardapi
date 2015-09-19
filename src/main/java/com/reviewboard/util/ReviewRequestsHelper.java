package com.reviewboard.util;

import java.math.BigInteger;
import java.util.List;

import javax.xml.bind.JAXBElement;

import org.apache.log4j.Logger;

import com.reviewboard.api.model.ReviewRequest;
import com.reviewboard.api.model.reviewrequests.Array;
import com.reviewboard.api.model.reviewrequests.Changenum;
import com.reviewboard.api.model.reviewrequests.Item;
import com.reviewboard.api.model.reviewrequests.Links;

public class ReviewRequestsHelper {
	private static Logger logger = Logger.getLogger(ReviewRequestsHelper.class);

	private static final String SUMMARY="summary";
	private static final String ID="id";
	private static final String TIME_ADDED="time_added";
	private static final String BRANCH="branch";
	private static final String STATUS="status";
	private static final String DESCRIPTION="description";
	
	private static final String BUGS_CLOSED="bugs_closed";
	private static final String TARGET_PEOPLE="target_people";
	private static final String TARGET_GROUPS="target_groups";
	private static final String LAST_UPDATED="last_updated";
	
	private static final String TARGET_GROUPS_NAME="title";
	private static final String TARGET_PEOPLE_NAME="title";

	
	public static ReviewRequest decode(Item item) throws Exception {
		ReviewRequest reviewItem=null;
		
			if (item == null) {
				throw new Exception("Item is invalid");

			}
			if(item.getContent()==null){
				throw new Exception("Item Content is empty");
			}
			reviewItem = new ReviewRequest();
			
			List<Object> content = item.getContent();

			for (Object object : content) {
				
				if (object instanceof JAXBElement) {
					JAXBElement poElement = (JAXBElement) object;
					//logger.info(poElement.getValue());
					//logger.info("Name :" + poElement.getName() + " - "+ poElement.getValue());
										
					if(SUMMARY.equals(poElement.getName().toString().trim())){
						String summary = (String)poElement.getValue();
						reviewItem.setSummary(summary);
					}
					else if(ID.equals(poElement.getName().toString().trim())){
						BigInteger id = (BigInteger)poElement.getValue();
						reviewItem.setId(new Long(id.longValue()));
					}
					else if(TIME_ADDED.equals(poElement.getName().toString().trim())){
						String date = (String)poElement.getValue();
						reviewItem.setTimeAdded(DateUtil.toDate(date));
					}
					else if(STATUS.equals(poElement.getName().toString().trim())){
						String status = (String)poElement.getValue();
						reviewItem.setStatus(status);
					}
					else if(BRANCH.equals(poElement.getName().toString().trim())){
						String branch = (String)poElement.getValue();
						reviewItem.setBranch(branch);
					}
					else if(DESCRIPTION.equals(poElement.getName().toString().trim())){
						String desc = (String)poElement.getValue();
						reviewItem.setDescription(desc);
					}
					else if(BUGS_CLOSED.equals(poElement.getName().toString().trim())){
						//String bugs_closed = (String)poElement.getValue();
						
						Array bugs_closed = (com.reviewboard.api.model.reviewrequests.Array)poElement.getValue();

						List<Item> items = bugs_closed.getArray().getItem();
						for (Item item3 : items) {
							List<Object> content2 = item3.getContent();
							for (Object itemStr : content2) {
								reviewItem.getBugsClosed().add(itemStr.toString());
							}
						}
						
						//reviewItem.setBugsClosed(bugs_closed);
						
					}
					else if(TARGET_PEOPLE.equals(poElement.getName().toString().trim())){
						
						Array targetPeople = (com.reviewboard.api.model.reviewrequests.Array)poElement.getValue();

						List<Item> itemsTargetPeople = targetPeople.getArray().getItem();
						for (Item item3 : itemsTargetPeople) {
							List<Object> content2 = item3.getContent();
							for (Object itemStr : content2) {
								
								if(itemStr instanceof JAXBElement){
									JAXBElement elem = (JAXBElement) itemStr;
									
									if(TARGET_PEOPLE_NAME.equals(elem.getName().toString())){								
										reviewItem.getTargetPeople().add(elem.getValue().toString());
									}

								}
								
							}
						}
					}
					else if(TARGET_GROUPS.equals(poElement.getName().toString().trim())){
						Array targetGroups = (com.reviewboard.api.model.reviewrequests.Array)poElement.getValue();
						
						List<Item> items = targetGroups.getArray().getItem();
						for (Item item3 : items) {
							List<Object> content2 = item3.getContent();
							for (Object itemStr : content2) {
								
								if(itemStr instanceof JAXBElement){
									JAXBElement elem = (JAXBElement) itemStr;
									
									if(TARGET_GROUPS_NAME.equals(elem.getName().toString())){								
										reviewItem.getTargetGroups().add(elem.getValue().toString());
									}

								}
							}
						}
						
						//reviewItem.setTargetGroups(targetGroups);
					}
					else if(LAST_UPDATED.equals(poElement.getName().toString().trim())){
						String lastUpdated = (String)poElement.getValue();
						
						reviewItem.setLastUpdated(DateUtil.toDate(lastUpdated));

					}
					

				} else if (object instanceof Changenum) {
					Changenum chagenNum = (Changenum) object;
					//logger.info(chagenNum);
				} else if (object instanceof Links) {
					Links links = (Links) object;
					//logger.info(links);
					if(links.getSubmitter()!=null){					
						String sub = links.getSubmitter().getTitle();
						reviewItem.setSubmitter(sub);
					}
					
				}
			}
		
		return reviewItem;
	}
}

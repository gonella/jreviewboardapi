package com.reviewboard.util;

import org.apache.log4j.Logger;

import com.reviewboard.api.model.ReviewRequest;
import com.reviewboard.api.model.reviewrequest.Item;

public class ReviewRequestHelper {
	private static Logger logger = Logger.getLogger(ReviewRequestHelper.class);

	private static final String SUMMARY="summary";
	private static final String ID="id";
	private static final String TIME_ADDED="time_added";
	private static final String BRANCH="branch";
	private static final String STATUS="status";
	private static final String DESCRIPTION="description";

	public static ReviewRequest decode(Item item) throws Exception {
		ReviewRequest reviewItem=null;
			
		
		return reviewItem;
	}
}

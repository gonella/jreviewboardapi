package com.reviewboard.util;

import org.apache.log4j.Logger;

import com.reviewboard.api.model.ReviewResource;
import com.reviewboard.api.model.reviewresource.Item;

public class ReviewResourceHelper {
	private static Logger logger = Logger.getLogger(ReviewResourceHelper.class);

	public static ReviewResource decode(Item item)
			throws Exception {
		ReviewResource obj = null;

		if (item == null) {
			throw new Exception("Item is invalid");

		}
		obj=new ReviewResource();
		
		obj.setId(item.getId().longValue());
		obj.setShipit((item.getShipIt().intValue()>0?true:false));
		obj.setContent(item.getBodyTop());
		obj.setDate(DateUtil.toDate(item.getTimestamp()));
		
		return obj;
	}
}

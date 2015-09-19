package com.reviewboard.util;

import org.apache.log4j.Logger;

import com.reviewboard.api.model.DiffComment;

public class DiffCommentHelper {
	private static Logger logger = Logger.getLogger(DiffCommentHelper.class);

	public static DiffComment decode(com.reviewboard.api.model.diffcomment.Item item)
			throws Exception {
		DiffComment obj = null;

		if (item == null) {
			throw new Exception("Item is invalid");

		}
		obj=new DiffComment();
		obj.setContent(item.getText());
		
		obj.setIssueStatus(item.getIssueStatus());
		
		return obj;
	}
}

package com.virtual.queue.validator;

import java.util.List;

import com.virtual.queue.rule.Rule;

public class RideValidator implements Validator {
	
	private List<Rule> ruleList = null;

	@Override
	public boolean validate(long userId,long rideId) {

		// TODO:Add any other validation logic not related to specify business
		// validation logic.
		for (Rule rule : ruleList) {

			try {
				rule.loadData(userId,rideId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			   return false;
			}
			
			if(!rule.apply())return false;

		}
		return true;

	}

	@Override
	public void setRules(final List<Rule> rules) throws Exception {
		if (rules.isEmpty())
			throw new Exception("Empty rule list");
		ruleList = rules;

	}

}

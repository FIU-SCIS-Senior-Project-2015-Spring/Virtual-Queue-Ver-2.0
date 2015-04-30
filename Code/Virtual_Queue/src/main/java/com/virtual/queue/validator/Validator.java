package com.virtual.queue.validator;

import java.util.List;

import com.virtual.queue.rule.Rule;

public interface Validator {
	public void setRules(final List<Rule> rules) throws Exception;
 
	public boolean validate(long userId,long rideId);
}

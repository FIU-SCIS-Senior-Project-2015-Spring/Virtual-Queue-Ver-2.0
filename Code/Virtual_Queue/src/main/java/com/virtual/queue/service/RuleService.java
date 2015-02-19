package com.virtual.queue.service;

import com.virtual.queue.beans.RuleCapacityBean;

public interface RuleService {

	RuleCapacityBean loadDataRule(long userId, long rideId) throws Exception;

	RuleCapacityBean loadDataRuleAll(long rideId) throws Exception;

}

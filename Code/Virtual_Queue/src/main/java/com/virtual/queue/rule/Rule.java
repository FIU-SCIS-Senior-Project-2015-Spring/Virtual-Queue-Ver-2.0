package com.virtual.queue.rule;

import com.virtual.queue.beans.RuleCapacityBean;

public interface Rule extends RuleCmd{
	public void loadData(long userId, long rideId) throws Exception;

	public void loadData();

	public RuleCapacityBean loadDataRule(long userId, long rideId)
			throws Exception;
}

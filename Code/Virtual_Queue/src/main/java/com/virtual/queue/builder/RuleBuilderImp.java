package com.virtual.queue.builder;

import java.util.ArrayList;
import java.util.List;

import com.virtual.queue.rule.AddUserRule;
import com.virtual.queue.rule.DuplicateUserRule;
import com.virtual.queue.rule.Rule;

public class RuleBuilderImp implements RuleBuilder{

	@Override
	public List<Rule> buildRules() {
		
		List<Rule> list=new ArrayList<Rule>();
		//TODO:this can be defined on a xml configuration file.using class type
		list.add(new DuplicateUserRule());
		list.add(new AddUserRule() ); 
		 
		return list;
	}

}

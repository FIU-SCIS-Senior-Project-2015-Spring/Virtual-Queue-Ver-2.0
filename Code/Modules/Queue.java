package com.mkyong.android;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Queue {
	public Queue(){

	}
	
	public Queue(int actid, String nameact, String about2, int lat, int lon){

	}
//	public List<Queue> getActivityId(int activityId, int visitorId, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		LinkedList<Queue> que = new LinkedList<Queue>();
//		rs = stmt.executeQuery("SELECT * FROM queue WHERE activity_id="+activityId+" and vistor_id="+visitorId+"" );
//		rs.next();
//		return ;
//		//return 0;
//	}
	public int getVisitorId(String name, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT activity_id FROM activity WHERE name_act=\""+name+"\"");
		rs.next();
		return rs.getInt("activity_id");
		//return 0;
	}
}

package com.mkyong.android;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Venue {
	private int activity_id;
	private String name_act;
	private String about;
	private int latitude;
	private int longitude;
	private String location_in_venue;
	private int time_per_event;
	private int entry_time;
	private int exit_time;
	private int max_gueest_per_event;
	private int max_concurrent_event;
	private int min_age;
	private int max_age;
	private int min_weight;
	private int max_weight;
	private String other_restriction;
	private String disclaimer;
	private String waiver;
	private int restriction_group_id;
	
	public Venue(){

	}
	
	public Venue(int actid, String nameact, String about2, int lat, int lon){

	}
	
	public int VenueID(String name, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT activity_id FROM activity WHERE name_act=\""+name+"\"");
		rs.next();
		return rs.getInt("activity_id");
		//return 0;
	}
	
	public String getNameAct(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT name_act FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getString("name_act");
	}
	
	public String getAbout(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT about FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getString("about");
	}
	
	public int getLatitude(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT latitude FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("latitude");
	}
	
	public int getLongitude(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT longitude FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("longitude");
	}
	
	public String getLocationinveunue(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT location_in_venue FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getString("location_in_venue");
	}
	
	public int getTimePerEvent(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT time_per_event FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("time_per_event");
	}
	
	public int getEntryTime(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT entry_time FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("entry_time");
	}
	
	public int getExitTime(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT exit_time FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("exit_time");
	}
	
	public int getMaxGuest(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT max_guest_per_event FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("max_guest_per_event");
	}
	
	public int getMaxConcurrentEvent(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT max_concurrent_event FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("max_concurrent_event");
	}
	
	public int getMinAge(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT min_age FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("min_age");
	}
	
	public int getMaxAge(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT max_age FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("man_age");
	}
	
	public int getMinHeight(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT min_height FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("min_height");
	}
	
	public int getMinWeight(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT min_weight FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("min_weight");
	}
	
	public int getMaxWeight(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT max_weight FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getInt("max_weight");
	}
	
	public String getOtherRestriction(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT other_restriction FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getString("other_restriction");
	}
	
	public String getDisclaimer(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT disclaimer FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getString("disclaimer");
	}
	
	public String getWaiver(int id, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT waiver FROM activity WHERE activity_id="+id);
		rs.next();
		return rs.getString("waiver");
	}
}

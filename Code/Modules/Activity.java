package com.mkyong.android;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Activity {
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
	private int min_height;
	private int max_height;
	private int min_weight;
	private int max_weight;
	private String other_restriction;
	private String disclaimer;
	private String waiver;
	private int restriction_group_id;
	
	public Activity(int id, Statement stmt) throws SQLException{
		activity_id =0;
		name_act = "";
		about = "" ;
		latitude = 0;
		longitude = 0;
		location_in_venue="";
		time_per_event=0;
		entry_time=0;
		exit_time=0;
		max_gueest_per_event = 0;
		max_concurrent_event = 0;
		min_age = 0;
		min_height = 0;
		max_height = 0;
		min_weight = 0;
		max_weight = 0;
		other_restriction = "";
		disclaimer = "";
		waiver = "";
		restriction_group_id = 0;
		ResultSet rs = null;
		//Statement stmt;
		rs = stmt.executeQuery("SELECT * FROM activity WHERE activity_id="+id);
		rs.next();
		activity_id = rs.getInt("activity_id");
		name_act = rs.getString("name_act");
		about = rs.getString("about");
		latitude = rs.getInt("latitude");
		longitude = rs.getInt("longitude");
		location_in_venue = rs.getString("location_in_venue");
		time_per_event = rs.getInt("time_per_event");
		entry_time = rs.getInt("entry_time");
		exit_time=rs.getInt("exit_time");
		max_gueest_per_event = rs.getInt("max_guest_per_event");
		max_concurrent_event = rs.getInt("max_concurrent_event");
		min_age = rs.getInt("min_age");
		min_height = rs.getInt("min_height");
		max_height = rs.getInt("max_height");
		min_weight = rs.getInt("min_age");
		max_weight = rs.getInt("min_weight");
		other_restriction = rs.getString("other_restriction");
		disclaimer = rs.getString("disclaimer");
		waiver = rs.getString("waiver");
		restriction_group_id = 0;
	}
	
	public Activity(int actid, String nameact, String about2, int lat, int lon){
//	public Activity(int ID, Statement stmt){
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT activity_id FROM activity WHERE name_act=\""+name+"\"");
		activity_id =0;
		name_act = nameact;
		about = about2;
		latitude = lat;
		longitude = lon;
		location_in_venue="";
		time_per_event=0;
		entry_time=0;
		exit_time=0;
		max_gueest_per_event = 0;
		max_concurrent_event = 0;
		 min_age = 0;
		max_age = 0;
		min_weight = 0;
		max_weight = 0;
		other_restriction = "";
		disclaimer = "";
		waiver = "";
		restriction_group_id = 0;
	}

	
	public int getActiviryID(String name, Statement stmt) throws SQLException{
		ResultSet rs = null;
		rs = stmt.executeQuery("SELECT activity_id FROM activity WHERE name_act=\""+name+"\"");
		rs.next();
		return rs.getInt("activity_id");
		//return 0;
	}

//	public String getNameAct(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT name_act FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getString("name_act");
//		return name_act;
//	}
	public String getNameAct() {
		return name_act;
	}
	
//	public String getAbout(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT about FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getString("about");
//	}
	public String getAbout(){
		return about;
	}
	
//	public int getLatitude(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT latitude FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("latitude");
//	}
	public int getLatitude(){
		return latitude;
	}
//	public int getLongitude(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT longitude FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("longitude");
//	}
	public int getLongitude(){
		return longitude;
	}
//	public String getLocationinveunue(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT location_in_venue FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getString("location_in_venue");
//	}
	public String getLocationinveunue(){
		return location_in_venue;
	}
	
//	public int getTimePerEvent(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT time_per_event FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("time_per_event");
//	}
	public int getTimePerEvent(){
		return time_per_event;
	}
//	public int getEntryTime(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT entry_time FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("entry_time");
//	}
	public int getEntryTime(){
		return entry_time;
	}
//	public int getExitTime(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT exit_time FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("exit_time");
//	}
	public int getExitTime(){
		return exit_time;
	}
	
	
//	public int getMaxGuest(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT max_guest_per_event FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("max_guest_per_event");
//	}
	public int getMaxGuest(){
		return max_gueest_per_event;
	}
//	public int getMaxConcurrentEvent(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT max_concurrent_event FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("max_concurrent_event");
//	}
	public int getMaxConcurrentEvent(){
		return max_concurrent_event;
	}
//	public int getMinAge(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT min_age FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("min_age");
//	}
	public int getMinAge(){
		return min_age;
	}
	public int getMinHeight(){
		return min_height;
	}
	public int getMaxHeight(){
		return max_height;
	}
//	public int getMinWeight(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT min_height FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("min_weight");
//	}
	public int getMinweight(){
		return min_weight;
	}
	
//	public int getMaxWeight(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT max_weight FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getInt("max_weight");
//	}
	public int getMaxWeight(){
		return max_weight;
	}
//	public String getOtherRestriction(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT other_restriction FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getString("other_restriction");
//	}
	public String getOtherRestriction(){
		return other_restriction;
	}
//	public String getDisclaimer(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT disclaimer FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getString("disclaimer");
//	}
	public String getDisclaimer(){
		return disclaimer;
	}
//	public String getWaiver(int id, Statement stmt) throws SQLException{
//		ResultSet rs = null;
//		rs = stmt.executeQuery("SELECT waiver FROM activity WHERE activity_id="+id);
//		rs.next();
//		return rs.getString("waiver");
//	}
	public String getWaiver(){
	return waiver;
	}
}

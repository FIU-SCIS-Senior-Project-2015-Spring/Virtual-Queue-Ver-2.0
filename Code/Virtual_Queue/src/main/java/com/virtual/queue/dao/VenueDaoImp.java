package com.virtual.queue.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.virtual.queue.beans.VenueInfo;
import com.virtual.queue.dao.VenueDao;

@Repository
public class VenueDaoImp extends BaseDao implements VenueDao {
	
	private final static String GET_VENUE_INFO="SELECT  * "+
			" FROM VirtualQueueDB.Venue v WHERE v.venue_id  = ?";
	
	
	@Override
	public List<VenueInfo> getVenueInfo(long venueId) {
		 
		List<VenueInfo> list= new ArrayList<VenueInfo>();
			Connection con=getConnection();
		
		try {

				PreparedStatement statement = con.prepareStatement(
						GET_VENUE_INFO);
				statement.setLong(1, venueId); 
				
				ResultSet result = statement.executeQuery();
				VenueInfo info2=null;
				while (result.next()) {
					
					info2= new VenueInfo();
					info2.setVenueName(result.getString("venue_name"));
					info2.setStartTime(result.getLong("start_time"));
					info2.setStartTime(result.getLong("end_time")); 
					list.add(info2); 
				}
				
				result.close(); 
				statement.close();
				
			} catch (SQLException e) {
				// TODO need to add log4j output
				e.printStackTrace();

			} catch (Exception ex) {

				// TODO need to add log4j output
				ex.printStackTrace();

			}finally{
				try {
					if(con!=null && !con.isClosed()){
						con.close();
						
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			VenueInfo vInfo= new VenueInfo();
			vInfo.setStartTime(8);
			vInfo.setEndTime(18);
			vInfo.setVenueName("Paradise");
			
			list.add(vInfo);
	return list;
	
	}

}

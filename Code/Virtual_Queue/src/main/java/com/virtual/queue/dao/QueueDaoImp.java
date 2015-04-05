package com.virtual.queue.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.virtual.queue.beans.Coordinate;
import com.virtual.queue.beans.QueueInfo;
import com.virtual.queue.beans.RideInfo;
import com.virtual.queue.beans.User;
import com.virtual.queue.beans.Queue;
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.beans.VenueInfo;
import com.virtual.queue.exception.NotificationException;

@Repository
@Transactional
public class QueueDaoImp extends BaseDao implements QueueDao {

//	private static final String GET_QUEUE_INFO = "SELECT u.user_id,u.user_name, u.first_name, u.last_name, u.phone_number, u.email , r.ride_duraction, r.ride_name "
//			+ " FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Ride r,  VirtualQueueDB.UserQueue uq "
//			+ " WHERE r.ride_id = ? AND r.myqueue_id = uq.queue_id AND uq.user_id = u.user_id ";
	private static final String GET_QUEUE_INFO = "SELECT * FROM  vqdatabase.queue q, activity a, vqdatabase.patron p, vqdatabase.visitor v where q.activity_id =? and a.activity_id=q.activity_id and v.visitor_id=q.visitor_id ";
	private static final String GET_RIDE_IN_QUEUE_INFO = "Select * From queue where activity_id=";
	private static final String GET_QUEUE_INFO_ALL = "SELECT u.user_name, u.first_name, u.last_name, u.phone_number, u.email "
			+ " FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Ride r,  VirtualQueueDB.UserQueue uq "
			+ " WHERE  r.myqueue_id = uq.queue_id AND uq.user_id = u.user_id ";

	// private static final String GET_QUEUE_BY_RIDEID = null;

	//private static final String GET_QUEUE = "SELECT q.myqueue_id,q.waiting_time,q.queue_capacity,r.ride_duraction FROM VirtualQueueDB.MyQueue q ,VirtualQueueDB.Ride r where q.myqueue_id= r.myqueue_id   AND  r.ride_id =? ";
	private static final String GET_QUEUE = "SELECT * FROM vqdatabase.queue q ,vqdatabase.activity a where q.activity_id= a.activity_id   AND  a.activity_id =? ";

	private static final String DELETE_ALL_FROM_QUEUE = "DELETE FROM VirtualQueueDB.UserQueue WHERE queue_id=(Select myqueue_id From Ride where ride_id= ? )";
	
	//this query is to delete the first record for a specific ride
	
	private static final String DELETE_FIRST_RECORD = "DELETE FROM vqdatabase.queue Where activity_id = ? LIMIT 1";
	
	private final static long VENUE_ID = 1;

	//private static final String GET_RIDE_INFO_BY_USERID = "SELECT r.ride_name, r.ride_duraction , r.ride_capacity, r.ride_id,   r.longitude, r.latitude   FROM  VirtualQueueDB.UserQueue q, Ride r where q.user_id =? and r.myqueue_id=queue_id order by q.registered_time asc ";
	private static final String GET_RIDE_INFO_BY_USERID = "SELECT * FROM  vqdatabase.queue q, activity a where q.visitor_id =? and a.activity_id=q.activity_id order by q.time_reservation_made asc ";

	private static final String GET_QUEUE_INFO_INTERVAL = "SELECT u.user_id  FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Ride r,  VirtualQueueDB.UserQueue uq WHERE r.ride_id = ? AND r.myqueue_id = uq.queue_id AND uq.user_id = u.user_id order by uq.registered_time";
	private static final String DELETE_USER_FROM_QUEUE = "Delete FROM vqdatabase.queue Where activity_id = ? AND visitor_id = ?";
	//query to find total number of visitors waiting front of this user
	private static final String GET_TOTAL_NUM_FRONT_OF_USER = "Select Count(*) AS totalnum From vqdatabase.queue Where queue_id < ? and activity_id = ?";
	//query to find specific queue Id for specific visitor in a specific ride
	private static final String GET_QUEUE_ID_FOR_VISITOR = "Select * From vqdatabase.queue Where visitor_id = ? AND activity_id = ?";
	
	public QueueDaoImp() {

	}
	
	 // Calls dataBase to remove from front of the queue
	public boolean removeFromFront(long rideId){
		
		PreparedStatement updateemp = null;
		Connection conn = getConnection();
		try {

			updateemp = conn.prepareStatement(DELETE_FIRST_RECORD);

			updateemp.setLong(1, rideId);
			updateemp.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());
			// TODO:needs to handle errors and return to caller with a message.
			return false;

		} finally {

			if (updateemp != null)
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		return true;
	}
	public List<UserQueueInfo> getNotificationInfoTest() {

		// pull data from DB DAO
		List<UserQueueInfo> info = new ArrayList<UserQueueInfo>(3);
		final UserQueueInfo info1 = new UserQueueInfo();
		info1.setName("Name1");
		info1.setMaxValue(300);
		info1.setPhoneNumber("7867602409");
		info1.setEmail("ysosasupport@gmail.com");
		info.add(info1);
		final UserQueueInfo info2 = new UserQueueInfo();
		info2.setName("Name2");
		info2.setMaxValue(400);
		info2.setPhoneNumber("7867602419");
		info2.setEmail("roninjord@yahoo.com");

		info.add(info2);
		final UserQueueInfo info3 = new UserQueueInfo();
		info2.setName("Name3");
		info2.setMaxValue(600);
		info3.setPhoneNumber("7867602409");
		info3.setEmail("samuraijord@hotmail.com");
		info.add(info3);

		return info;
	}

	@Override
	// TODO:refector with a better method's name.
	public List<UserQueueInfo> pullInfo(long rideId) {

		List<UserQueueInfo> infoList = new ArrayList<UserQueueInfo>();
		
		Connection con=getConnection();
		try {

			PreparedStatement statement = con.prepareStatement(
					GET_QUEUE_INFO);

			// TODO:set ride id from job scheduler.
			statement.setLong(1, rideId);
			// statement.setString(2, password);
			// statement.setString(3, code);

			ResultSet result = statement.executeQuery();
			UserQueueInfo info = null;
			while (result.next()) {

				info = new UserQueueInfo();

				info.setEmail(result.getString("user_name"));
				info.setName(result.getString("first_name") + ", "
						+ result.getString("last_name"));
				info.setPhoneNumber(result.getString("phone_number"));
				info.setEmail(result.getString("email"));
				info.setInterval(result.getInt("ride_duraction"));
				info.setRideName(result.getString("ride_name"));

				infoList.add(info);
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

		return infoList;

	}

	@Override
	public List<UserQueueInfo> pullAllInfo() {
		List<UserQueueInfo> infoList = new ArrayList<UserQueueInfo>();
		
		Connection con=getConnection();
		try {
			PreparedStatement statement = con.prepareStatement(
					GET_QUEUE_INFO_ALL);

			ResultSet result = statement.executeQuery();
			UserQueueInfo info = null;
			while (result.next()) {

				info = new UserQueueInfo();

				info.setEmail(result.getString("user_name"));
				info.setName(result.getString("first_name") + ", "
						+ result.getString("last_name"));
				info.setPhoneNumber(result.getString("phone_number"));
				info.setEmail(result.getString("email"));
				infoList.add(info);
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

		return infoList;
	}

	@Override
	public LinkedList<User> getAllUserQueueForRide(long rideId) {

		LinkedList<User> infoList = new LinkedList<User>();
		Connection conn = getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(GET_QUEUE_INFO);

			// TODO:set ride id from job scheduler.
			statement.setLong(1, rideId);
			// statement.setString(2, password);
			// statement.setString(3, code);

			ResultSet result = statement.executeQuery();
			User user = null;

			while (result.next()) {

				user = new User();
				user.setUserid(result.getLong("visitor_id"));
				user.setEmail(result.getString("username"));
				user.setFirstName(result.getString("name_first"));
				user.setLastName(result.getString("name_last"));
				user.setPhoneNumber(result.getString("contact_phone"));
				user.setEmail(result.getString("contact_email"));
				infoList.add(user);
			}

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return infoList;
	}
	
	//This returns list of Queues specific to the rideId (aka Activity_id)
	@Override
	public LinkedList<Queue> getListRideInQueue(long activity_id) {

		LinkedList<Queue> infoList = new LinkedList<Queue>();
		Connection conn = getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(GET_RIDE_IN_QUEUE_INFO+activity_id);

			// TODO:set ride id from job scheduler.
			//statement.setLong(1, activity_id);
			// statement.setString(2, password);
			// statement.setString(3, code);

			ResultSet result = statement.executeQuery();
			Queue queue = null;

			while (result.next()) {

				queue = new Queue();
				queue.setVisitorId(result.getLong("visitor_id"));
				queue.setActivityId(result.getLong("activity_id"));
				queue.setReservationTime(result.getString("reservation_time"));
				//queue.setEstimatedTime(result.getString("last_name"));
				//queue.setPhoneNumber(result.getString("phone_number"));
				//queuer.setEmail(result.getString("email"));
				infoList.add(queue);
			}

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return infoList;
	}

	@Override
	public LinkedList<Long> getAllUserQueueForRide(long rideId, int interval) {

		LinkedList<Long> infoList = new LinkedList<Long>();
		Connection conn = getConnection();
		try {

			PreparedStatement statement = conn
					.prepareStatement(GET_QUEUE_INFO_INTERVAL + " limit "
							+ interval);

			statement.setLong(1, rideId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				infoList.add(result.getLong("user_id"));
			}

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return infoList;
	}
//Method used to find wait time for the specific users
	@Override
	public QueueInfo getQueueInfoByRideId(long rideId) {

		QueueInfo info = new QueueInfo();
		Connection conn = getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(GET_QUEUE);

			statement.setLong(1, rideId);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

//				info.setCapacity(result.getInt("queue_capacity"));
//				info.setQueueId(result.getInt("myqueue_id"));
//				info.setWaitingTime(result.getInt("waiting_time"));
				info.setCapacity(result.getInt("max_guest_per_event"));
				info.setQueueId(result.getInt("activity_id"));
				info.setWaitingTime(result.getInt("estimated_time"));
				info.setQueueRealId(result.getInt("queue_id"));
			}

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return info;

	}

	@Override
	public boolean removeUserFromQueue(long rideId, long userId) {

		PreparedStatement updateemp = null;
		Connection conn = getConnection();
		try {

			updateemp = conn.prepareStatement(DELETE_USER_FROM_QUEUE);

			updateemp.setLong(1, rideId);
			updateemp.setLong(2, userId);
			updateemp.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());
			// TODO:needs to handle errors and return to caller with a message.
			return false;

		} finally {

			if (updateemp != null)
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		return true;
	}

	@Override
	public boolean removeAllUsersFromQueue(long rideId) {
		PreparedStatement updateemp = null;
		Connection conn = getConnection();

		try {

			updateemp = conn.prepareStatement(DELETE_ALL_FROM_QUEUE);
			updateemp.setLong(1, rideId);
			updateemp.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			// throw new ResetPasswordException(e.getMessage());
			// TODO:needs to handle errors and return to caller with a message.
			return false;

		} finally {

			if (updateemp != null)
				try {
					updateemp.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return true;
	}

	@Override
	public LinkedList<RideInfo> getRideListByUser(long userId) throws Exception {

		LinkedList<RideInfo> infoLst = new LinkedList<RideInfo>();
		// TODO:replace by logger.
		System.out.println("pull ride info666");

		VenueDao vDao = new VenueDaoImp();
		List<VenueInfo> venueList = vDao.getVenueInfo(VENUE_ID);

		if (venueList.isEmpty())
			throw new Exception("Empty venue information");

		VenueInfo vInfo = venueList.get(0);

		long startTime = vInfo.getStartTime();
		long endTime = vInfo.getStartTime();
		RideInfo info2 = null;

		PreparedStatement statement = null;

		Connection conn = getConnection();
		try {

			statement = conn.prepareStatement(GET_RIDE_INFO_BY_USERID);
			statement.setLong(1, userId);

			ResultSet result = statement.executeQuery();

			while (result.next()) {

				info2 = new RideInfo();
				info2.setrName(result.getString("name_act"));
				info2.setStartTime(startTime);
				info2.setInterval(result.getInt("time_per_event"));
				//info2.setInterval(5);
				info2.setEndTime(endTime);
				info2.setCapacity(result.getInt("max_guest_per_event"));
				info2.setRideId(result.getLong("activity_id"));
				info2.setCoordinate(new Coordinate(result
						.getBigDecimal("latitude"), result
						.getBigDecimal("longitude")));
				infoLst.add(info2);
				
			}

			result.close();
			statement.close();

		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			if (statement != null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return infoLst;

	}

	@Override
	public List<Long> getUserToRemoveFromQueue(Long rideId) {
		// TODO Auto-generated method stub
		return new ArrayList<Long>(1);
	}
	
	@Override
	public long getUserQueueId(long userId, long rideId) {
		Connection conn = getConnection();
		long queueId = 0;
		try {

			PreparedStatement statement = conn
					.prepareStatement(GET_QUEUE_ID_FOR_VISITOR);

			statement.setLong(1, userId);
			statement.setLong(2, rideId);
			
			ResultSet result = statement.executeQuery();
			result.next();
			queueId = result.getLong("queue_id");
			
			
			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		
		return queueId;
	}
	
	@Override
	public int getNumFrontUser(long rideId, long queueId) {
		
		
		
		Connection conn = getConnection();
		int totalnum = 0;
		try {

			PreparedStatement statement = conn
					.prepareStatement(GET_TOTAL_NUM_FRONT_OF_USER);

			statement.setLong(1, queueId);
			statement.setLong(2, rideId);
			
			ResultSet result = statement.executeQuery();
			result.next();
			totalnum = result.getInt("totalnum");

			result.close();
			statement.close();
		} catch (SQLException e) {
			// TODO need to add log4j output
			e.printStackTrace();

		} catch (Exception ex) {

			// TODO need to add log4j output
			ex.printStackTrace();

		} finally {

			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		
		
		return totalnum;
	}
	
	
}

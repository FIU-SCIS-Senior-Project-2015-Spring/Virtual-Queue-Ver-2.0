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
import com.virtual.queue.beans.UserQueueInfo;
import com.virtual.queue.beans.VenueInfo;
import com.virtual.queue.exception.NotificationException;

@Repository
@Transactional
public class QueueDaoImp extends BaseDao implements QueueDao {

	private static final String GET_QUEUE_INFO = "SELECT u.user_id,u.user_name, u.first_name, u.last_name, u.phone_number, u.email , r.ride_duraction, r.ride_name "
			+ " FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Ride r,  VirtualQueueDB.UserQueue uq "
			+ " WHERE r.ride_id = ? AND r.myqueue_id = uq.queue_id AND uq.user_id = u.user_id ";
	private static final String GET_QUEUE_INFO_ALL = "SELECT u.user_name, u.first_name, u.last_name, u.phone_number, u.email "
			+ " FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Ride r,  VirtualQueueDB.UserQueue uq "
			+ " WHERE  r.myqueue_id = uq.queue_id AND uq.user_id = u.user_id ";

	// private static final String GET_QUEUE_BY_RIDEID = null;

	private static final String GET_QUEUE = "SELECT q.myqueue_id,q.waiting_time,q.queue_capacity,r.ride_duraction FROM VirtualQueueDB.MyQueue q ,VirtualQueueDB.Ride r where q.myqueue_id= r.myqueue_id   AND  r.ride_id =? ";

	private static final String DELETE_ALL_FROM_QUEUE = "DELETE FROM VirtualQueueDB.UserQueue WHERE queue_id=(Select myqueue_id From Ride where ride_id= ? )";

	private final static long VENUE_ID = 1;

	private static final String GET_RIDE_INFO_BY_USERID = "SELECT r.ride_name, r.ride_duraction , r.ride_capacity, r.ride_id,   r.longitude, r.latitude   FROM  VirtualQueueDB.UserQueue q, Ride r where q.user_id =? and r.myqueue_id=queue_id order by q.registered_time asc ";
	private static final String GET_QUEUE_INFO_INTERVAL = "SELECT u.user_id  FROM VirtualQueueDB.VenueRegisteredUser u, VirtualQueueDB.Ride r,  VirtualQueueDB.UserQueue uq WHERE r.ride_id = ? AND r.myqueue_id = uq.queue_id AND uq.user_id = u.user_id order by uq.registered_time";
	private static final String DELETE_USER_FROM_QUEUE = "DELETE FROM VirtualQueueDB.UserQueue WHERE queue_id=(Select myqueue_id From Ride where ride_id= ?) AND user_id = ? ";

	public QueueDaoImp() {

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
				user.setUserid(result.getLong("user_id"));
				user.setEmail(result.getString("user_name"));
				user.setFirstName(result.getString("first_name"));
				user.setLastName(result.getString("last_name"));
				user.setPhoneNumber(result.getString("phone_number"));
				user.setEmail(result.getString("email"));
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

	@Override
	public QueueInfo getQueueInfoByRideId(long rideId) {

		QueueInfo info = new QueueInfo();
		Connection conn = getConnection();
		try {
			PreparedStatement statement = conn.prepareStatement(GET_QUEUE);

			statement.setLong(1, rideId);

			ResultSet result = statement.executeQuery();

			if (result.next()) {

				info.setCapacity(result.getInt("queue_capacity"));
				info.setQueueId(result.getInt("myqueue_id"));
				info.setWaitingTime(result.getInt("waiting_time"));

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
				info2.setrName(result.getString("ride_name"));
				info2.setStartTime(startTime);
				info2.setInterval(result.getInt("ride_duraction"));
				info2.setEndTime(endTime);
				info2.setCapacity(result.getInt("ride_capacity"));
				info2.setRideId(result.getLong("ride_id"));
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
}

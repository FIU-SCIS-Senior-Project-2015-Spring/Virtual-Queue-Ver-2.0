package com.virtual.queue.dao;

import java.util.List;

import com.virtual.queue.beans.VenueInfo;

 

public interface VenueDao {
	
public List<VenueInfo> getVenueInfo(long venueId);
}

/*
 * @(#)HotelService.java $version 2014. 1. 24.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package io.resthelpersample;

import io.resthelpersample.model.Hotel;
import io.resthelpersample.model.Room;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

/**
 * @author NHN
 */
@Service
public class HotelService implements InitializingBean {
	private Map<Integer, Hotel> hotels;

	@Override
	public void afterPropertiesSet() throws Exception {
		hotels = new HashMap<Integer, Hotel>();
		
		hotels.put(100, new Hotel(100, "Ibis Mapo"));
		hotels.put(200, new Hotel(200, "Ibis Bundang"));
		hotels.put(300, new Hotel(300, "Galleria"));
		hotels.put(400, new Hotel(400, "Abassador"));
	}

	/**
	 * @param search
	 * @return
	 */
	public List<Hotel> findHotels(String search) {
		List<Hotel> response = new ArrayList<Hotel>();
		
		if (search != null && search.length() > 0) {
			for (Hotel hotel : hotels.values()) {
				if (hotel.getName().contains(search)) {
					response.add(hotel);
				}
			}
		} else {
			response.addAll(hotels.values());
		}
		
		return response;
	}


	/**
	 * @param hotelNo
	 * @return
	 */
	public Hotel getHotel(int hotelNo) {
		return hotels.get(hotelNo);
	}


	/**
	 * @param hotelNo
	 * @return
	 */
	public List<Room> getHotelRooms(int hotelNo) {
		return hotels.get(hotelNo).getRooms();
	}

	/**
	 * @param hotelNo
	 * @param newRoom
	 */
	public void addHotelRoom(int hotelNo, Room newRoom) {
		hotels.get(hotelNo).getRooms().add(newRoom);
	}

	/**
	 * @param hotelNo
	 * @param roomNo
	 * @return
	 */
	public Room reserveHotelRoom(int hotelNo, int roomNo) {
		List<Room> rooms = hotels.get(hotelNo).getRooms();
		
		for (Room room : rooms) {
			if (room.getNumber() == roomNo) {
				room.setReserved(true);
				return room;
			}
		}
		
		return null;
	}
}

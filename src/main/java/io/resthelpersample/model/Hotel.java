/*
 * @(#)Hotel.java $version 2014. 1. 24.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package io.resthelpersample.model;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author NHN
 */
public class Hotel {
	private int number;
	private String name;
	private List<Room> rooms = new ArrayList<Room>();

	/**
	 * @param name
	 */
	public Hotel(int number, String name) {
		this.number = number;
		this.name = name;
		
		rooms.add(new Room(101));
		rooms.add(new Room(102));
		rooms.add(new Room(103));
		rooms.add(new Room(201));
		rooms.add(new Room(201));
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@JsonIgnore
	public List<Room> getRooms() {
		return rooms;
	}
}

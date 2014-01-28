/*
 * @(#)Room.java $version 2014. 1. 24.
 *
 * Copyright 2007 NHN Corp. All rights Reserved. 
 * NHN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package io.resthelpersample.model;

/**
 * @author NHN
 */
public class Room {
	private int number;
	private boolean reserved;
	
	/**
	 * @param number
	 */
	public Room(int number) {
		this.number = number;
	}
	
	public int getNumber() {
		return number;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}
	
	public boolean isReserved() {
		return reserved;
	}
	
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
}
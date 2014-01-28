/*
 * Copyright 2014 The RestHelper Project
 *
 * The RestHelper Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package io.resthelpersample;

import io.resthelper.annotations.ApiDescription;
import io.resthelper.annotations.ApiName;
import io.resthelpersample.model.Hotel;
import io.resthelpersample.model.Room;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HotelController {
	private static final Logger logger = LoggerFactory.getLogger(HotelController.class);
	
	@Autowired
	private HotelService hotelService;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@ApiName("get hotel list")
	@ApiDescription("find hotels with name containg 'search'")
	@RequestMapping(value="/hotels", method = RequestMethod.GET)
	@ResponseBody
	public List<Hotel> getHotels(@RequestParam("search") String search){
		List<Hotel> hotels = hotelService.findHotels(search);
		
		return hotels;
	}
	
	@ApiName("get hotel")
	@ApiDescription("get hotel of hotelNo")
	@RequestMapping(value="/hotels/{hotelNo}", method = RequestMethod.GET)
	@ResponseBody
	public Hotel getHotel(@PathVariable("hotelNo") int hotelNo) {
		return hotelService.getHotel(hotelNo);
	}
	
	@ApiName("get hotel's room list")
	@ApiDescription("room list with reservation status")
	@RequestMapping(value="/hotels/{hotelNo}/rooms")
	@ResponseBody
	public List<Room> getHotelRooms(@PathVariable("hotelNo") int hotelNo) {
		return hotelService.getHotelRooms(hotelNo);
	}
	
	@ApiName("add a new room to the hotel")
	@ApiDescription("sample json : {\"number\":401, \"reserved\":false}")
	@RequestMapping(value="/hotels/{hotelNo}/rooms", method = RequestMethod.POST)
	@ResponseBody
	public List<Room> postHotelRoom(@PathVariable("hotelNo") int hotelNo, @RequestBody Room newRoom) {
		hotelService.addHotelRoom(hotelNo, newRoom);
		
		return hotelService.getHotelRooms(hotelNo);
	}
	
	@ApiName("set hotel room reserved")
	@ApiDescription("...")
	@RequestMapping(value="/hotels/{hotelNo}/rooms/{roomNo}/reserved", method = RequestMethod.PUT)
	@ResponseBody
	public Room reserveHotelRoom(@PathVariable("hotelNo") int hotelNo, @PathVariable("roomNo") int roomNo) {
		Room room = hotelService.reserveHotelRoom(hotelNo, roomNo);
		
		return room;
	}
}
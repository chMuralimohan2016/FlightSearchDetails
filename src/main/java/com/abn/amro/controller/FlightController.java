package com.abn.amro.controller;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abn.amro.dto.FlightDetailsDTO;
import com.abn.amro.service.FlightService;

import lombok.extern.slf4j.Slf4j;


/**
 * In FlightController Controller
 *
 */
@Slf4j
@RestController
@Validated
public class FlightController {

	FlightService flightService;

	@Autowired
	public FlightController(FlightService flightService) {
		this.flightService = flightService;
	}

	/**
	 * Call getListOfFlights method and return List of flight details
	 * 
	 * @param origin
	 * @param destination
	 * @param price
	 * @param duration
	 * @return FlightDetailsDTO
	 */
	@GetMapping("/getFlightDetails")
	public ResponseEntity<List<FlightDetailsDTO>> getListOfFlights(@RequestParam(required = true) String origin,
			@RequestParam(required = true) String destination, @RequestParam(required = false) String price,
			@RequestParam(required = false) String duration) {

		log.info("In FlightController call");

		List<FlightDetailsDTO> flightDetailsDTO = flightService.getOrginAndDepDetails(origin, destination, price,
				duration);

		return new ResponseEntity<>(flightDetailsDTO, HttpStatus.ACCEPTED);

	}

}

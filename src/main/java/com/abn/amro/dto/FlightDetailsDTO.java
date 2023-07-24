package com.abn.amro.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * FlightDetailsDTO - FlightDetailsDTO
 *
 */
@Data
@Builder
public class FlightDetailsDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String flightNumber;
	private String origin;
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private double price;

}
package com.abn.amro.entity;



import java.time.LocalDateTime;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;



/**
 * FlightDetails - FlightDetails
 *
 */
@Entity
@Table(name="FlightDetails")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDetails {

	@Id
	@GeneratedValue()
	private Long id;
	@Column(nullable = false)
	private String flightNumber;
	@Column(nullable = false)
	private String origin;
	@Column(nullable = false)
	private String destination;
	private LocalDateTime departureTime;
	private LocalDateTime arrivalTime;
	private double price;

}

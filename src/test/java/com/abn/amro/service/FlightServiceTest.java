package com.abn.amro.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.abn.amro.dto.FlightDetailsDTO;
import com.abn.amro.entity.FlightDetails;
import com.abn.amro.repositroy.FlightDetailRepository;

@ExtendWith(MockitoExtension.class)
public class FlightServiceTest {

	@InjectMocks
	FlightService flightService;

	@Mock
	FlightDetailRepository flightDetailRepository;

	@Before(value = "")
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void getOrginAndDepDetailsTest() {

		LocalDateTime departureDate = LocalDateTime.of(2017, 7, 6, 23, 30, 0);
		LocalDateTime arrivalDate = LocalDateTime.of(2017, 7, 7, 1, 30, 0);
		final FlightDetails flightDetailData = FlightDetails.builder().flightNumber("A101").origin("BOM")
				.destination("MAA").arrivalTime(arrivalDate).departureTime(departureDate).price(80).build();
		List<FlightDetails> flightToDetails = new ArrayList<FlightDetails>();
		flightToDetails.add(flightDetailData);

		List<FlightDetailsDTO> flightDetailsDTO = flightService.getFlightDtoDetails(flightToDetails);

		assertEquals(1, flightDetailsDTO.size());

	}
	
	@Test
	public void getPriceDetailsTest() {
		
		LocalDateTime departureDate = LocalDateTime.of(2017, 7, 6, 23, 30, 0);
		LocalDateTime arrivalDate = LocalDateTime.of(2017, 7, 7, 1, 30, 0);
		final FlightDetails flightDetailData = FlightDetails.builder().flightNumber("A101").origin("BOM")
				.destination("MAA").arrivalTime(arrivalDate).departureTime(departureDate).price(80).build();
		final FlightDetails flightDetailData11 = FlightDetails.builder().flightNumber("B101").origin("BOM")
				.destination("MAA").arrivalTime(arrivalDate).departureTime(departureDate).price(60).build();
		List<FlightDetails> flightToDetails = new ArrayList<FlightDetails>();
		flightToDetails.add(flightDetailData);
		flightToDetails.add(flightDetailData11);
		Iterable<FlightDetails> flightDetailData1= this.flightDetailRepository.findByOriginAndDestination("MAA", "BOM");
		flightDetailData1.forEach(flightToDetails :: add);
		List<FlightDetailsDTO> flightDetailsDTO = flightService.getFlightDtoDetails(flightToDetails);
		List<FlightDetailsDTO> flightSortedDetails = flightDetailsDTO.stream()
				.sorted(Comparator.comparingDouble(FlightDetailsDTO::getPrice)).collect(Collectors.toList());
		
		assertEquals(2, flightSortedDetails.size());
		
		
	}

}

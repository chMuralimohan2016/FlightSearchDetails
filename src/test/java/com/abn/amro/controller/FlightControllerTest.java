package com.abn.amro.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.abn.amro.dto.FlightDetailsDTO;
import com.abn.amro.service.FlightService;

@ExtendWith(MockitoExtension.class)
public class FlightControllerTest {

	@InjectMocks
	FlightController flightController;

	@Mock
	private FlightService flightService;

	@Test
	public void getListOfFlights() {
		LocalDateTime departureDate = LocalDateTime.of(2017, 7, 6, 23, 30, 0);
		LocalDateTime arrivalDate = LocalDateTime.of(2017, 7, 7, 1, 30, 0);
		final FlightDetailsDTO flightDetailDtoData = FlightDetailsDTO.builder().flightNumber("A101").origin("BOM")
				.destination("MAA").arrivalTime(arrivalDate).departureTime(departureDate).price(80).build();

		List<FlightDetailsDTO> flightDetails = new ArrayList<FlightDetailsDTO>();
		flightDetails.add(flightDetailDtoData);

		//when(flightService.getOrginAndDepDetails("BOM", "MAA")).thenReturn(flightDetails);

		ResponseEntity<List<FlightDetailsDTO>> result = flightController.getListOfFlights("BOM", "MAA", "", "");

		assertThat(result.getStatusCodeValue()).isEqualTo(202);
	}

}

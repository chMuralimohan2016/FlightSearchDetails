package com.abn.amro.service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abn.amro.dto.FlightDetailsDTO;
import com.abn.amro.entity.FlightDetails;
import com.abn.amro.exception.NoRecordFoundException;
import com.abn.amro.repositroy.FlightDetailRepository;
import com.abn.amro.util.Constants;

import lombok.extern.slf4j.Slf4j;



/**
 * FlightService service implementation 
 *
 */
@Slf4j
@Service
public class FlightService {

	@Autowired
	FlightDetailRepository flightDetailRepository;

	/**
	 * Call getOrginAndDepDetails method and return List of flight details
	 * 
	 * @param origin
	 * @param destination
	 * @return List<FlightDetailsDTO>
	 */
	public List<FlightDetailsDTO> getOrginAndDepDetails(String origin, String departure, String price,
			String duration) {

		List<FlightDetailsDTO> flightDetailsDTO = new ArrayList<>();
		Iterable<FlightDetails> flightDetails = flightDetailRepository.findByOriginAndDestination(origin.toUpperCase(),
				departure.toUpperCase());

		log.info("getOrginAndDepDetails::");
		if (origin != null && departure != null && price != null && duration != null) {
			List<FlightDetailsDTO> flightDetailsPriceDTO = getPriceDetails(flightDetails, price);
			flightDetailsDTO = flightDetailsPriceDTO.stream().sorted(Comparator.comparingDouble(d -> durationTimes(d)))
					.collect(Collectors.toList());
		} else if (origin != null && departure != null && duration != null) {
			flightDetailsDTO = getDurationDetails(flightDetails, duration);
		} else if (origin != null && departure != null && price != null) {
			flightDetailsDTO = getPriceDetails(flightDetails, price);
		} else if (origin != null && departure != null) {
			flightDetailsDTO = getFlightDtoDetails(flightDetails);
		}

		log.info("flightDetailsDTO SIZE::" + flightDetailsDTO.size());

		return flightDetailsDTO;
	}

	/**
	 * Call getPriceDetails method and return List of flight details with price
	 * price details
	 * 
	 * @param origin
	 * @param destination
	 * @param Price
	 * @return List<FlightDetailsDTO>
	 */
	public List<FlightDetailsDTO> getPriceDetails(Iterable<FlightDetails> flightDetails, String price) {
		// TODO Auto-generated method stub

		log.info("getOrginAndDepDetails Price::" + price);

		List<FlightDetailsDTO> flightDetailsDTO = getFlightDtoDetails(flightDetails);
		List<FlightDetailsDTO> flightSortedDetails = null;
		if (price.toLowerCase().equals(Constants.ASC)) {
			flightSortedDetails = flightDetailsDTO.stream()
					.sorted(Comparator.comparingDouble(FlightDetailsDTO::getPrice)).collect(Collectors.toList());
		} else if (price.toLowerCase().equals(Constants.DSC)) {
			flightSortedDetails = flightDetailsDTO.stream()
					.sorted(Comparator.comparingDouble(FlightDetailsDTO::getPrice).reversed())
					.collect(Collectors.toList());
		}

		return flightSortedDetails;
	}

	/**
	 * Call getDurationDetails method and return List of flight details with duration
	 * duration details
	 * 
	 * @param origin
	 * @param destination
	 * @param Duration
	 * @return List<FlightDetailsDTO>
	 */
	public List<FlightDetailsDTO> getDurationDetails(Iterable<FlightDetails> flightDetails, String duration) {
		// TODO Auto-generated method stub
		log.info("getOrginAndDepDetails Duration::" + duration);

		List<FlightDetailsDTO> flightDetailsDTO = getFlightDtoDetails(flightDetails);

		List<FlightDetailsDTO> flightSortedDetails = flightDetailsDTO.stream()
				.sorted(Comparator.comparingDouble(d -> durationTimes(d))).collect(Collectors.toList());
		return flightSortedDetails;
	}

	/**
	 * @param flightDetailsDTO
	 * @return double
	 */
	private double durationTimes(FlightDetailsDTO flightDetailsDTO) {
		Duration duration = Duration.between(flightDetailsDTO.getDepartureTime(), flightDetailsDTO.getArrivalTime());
		double result = duration.toHours() + duration.toMinutesPart() / 60f;
		return result;
	}

	/**
	 * @param flightDetails
	 * @return List<FlightDetailsDTO>
	 */
	public List<FlightDetailsDTO> getFlightDtoDetails(Iterable<FlightDetails> flightDetails) {

		List<FlightDetailsDTO> flightDetailsDTO = new ArrayList<>();

		flightDetails.forEach(flightDetail -> {
			flightDetailsDTO.add(FlightDetailsDTO.builder().flightNumber(flightDetail.getFlightNumber())
					.origin(flightDetail.getOrigin()).destination(flightDetail.getDestination())
					.departureTime(flightDetail.getDepartureTime()).arrivalTime(flightDetail.getArrivalTime())
					.price(flightDetail.getPrice()).build());
		});
		log.info("FlightDetails Size::" + flightDetailsDTO.size());
		if (flightDetailsDTO.size() < 1) {
			throw new NoRecordFoundException();
		}
		return flightDetailsDTO;
	}

}

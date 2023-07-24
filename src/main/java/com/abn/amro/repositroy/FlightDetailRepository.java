package com.abn.amro.repositroy;


import org.springframework.data.repository.CrudRepository;

import com.abn.amro.entity.FlightDetails;

public interface FlightDetailRepository extends CrudRepository<FlightDetails, Long> {
	
	Iterable<FlightDetails> findByOriginAndDestination(String origin, String destination);
}
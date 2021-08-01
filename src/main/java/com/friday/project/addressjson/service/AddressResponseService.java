package com.friday.project.addressjson.service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.friday.project.addressjson.constant.AddressPatterns;
import com.friday.project.addressjson.dto.AddressResponseDTO;

@Component
public class AddressResponseService {

	Logger logger = LoggerFactory.getLogger(AddressResponseService.class);

	/**
	 * Returns Street and house number based on Predefined address patterns
	 * @throws Exception 
	 */
	public ResponseEntity<Object> getStreetAndHouseNumber(String address) throws Exception{
		HashMap<Integer, Pattern> addressPatterns = new AddressPatterns().loadAddressPatterns();
		for (Map.Entry<Integer, Pattern> pattern : addressPatterns.entrySet()) {
			Matcher matcher = pattern.getValue().matcher(address);
			if (matcher.matches()) {
				logger.info("Address matched pattern ID - {} ", pattern.getKey());
				return ResponseEntity.ok(verifyPatternAndReturnResponse(matcher, pattern.getKey()));				
			}
		}			
		throw new Exception("Address Pattern not found");
	}

	/**
	 * Verifies address pattern based on key and returns response
	 */
	private AddressResponseDTO verifyPatternAndReturnResponse(Matcher matcher, Integer key) {
		switch (key) {
		case 1:
			return setResponse(matcher.group(2).concat(" ").concat(matcher.group(3)), matcher.group(1));
		case 2:
		case 3:
		case 4:
			return setResponse(matcher.group(1), matcher.group(2));
		case 5:
			return setResponse(matcher.group(2), matcher.group(1));
		}
		return null;
	}

	private AddressResponseDTO setResponse(String streetName, String houseNumber) {
		AddressResponseDTO response = new AddressResponseDTO();
		response.setStreet(streetName.replace(",", "").stripTrailing());
		response.setHouseNumber(houseNumber.replace(",", ""));
		return response;
	}

}

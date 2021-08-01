package com.friday.project.addressjson.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friday.project.addressjson.dto.AddressDTO;
import com.friday.project.addressjson.service.AddressResponseService;

@RestController
public class AddressController {

	Logger logger = LoggerFactory.getLogger(AddressController.class);

	@Autowired
	private AddressResponseService addressService;

	/**
	 * Returns final address response
	 */
	@PostMapping("/address")
	public ResponseEntity<Object> getAddressDetails(@RequestBody AddressDTO address) throws Exception {
		logger.info("Call to get House Number and Street for address: {}", address.getAddress());
		return addressService.getStreetAndHouseNumber(address.getAddress());
	}

}

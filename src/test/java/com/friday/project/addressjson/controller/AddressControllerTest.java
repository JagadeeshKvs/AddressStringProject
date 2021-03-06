package com.friday.project.addressjson.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.friday.project.addressjson.service.AddressResponseService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { AddressController.class, AddressResponseService.class })
@WebMvcTest
public class AddressControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAddressDetils() throws Exception {
		Map<String, String> requestAndResponseValues = createRequestResponse();
		for (Map.Entry<String, String> requestResponse : requestAndResponseValues.entrySet()) {
			testRequestAndResponse(requestResponse.getKey(), requestResponse.getValue());
		}
	}

	private void testRequestAndResponse(String request, String expectedValue) throws Exception {
		String content = request;
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/address").accept(MediaType.APPLICATION_JSON)
				.content(content).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse responseValue = result.getResponse();
		String expected = expectedValue;

		String resultString = responseValue.getContentAsString(StandardCharsets.UTF_8);
		assertNotNull(resultString);
		JSONAssert.assertEquals(expected, resultString, false);
	}

	/**
	 * Create a Map of request and response strings
	 */
	private Map<String, String> createRequestResponse() {
		Map<String, String> requestAndResponse = new HashMap<String, String>();
		requestAndResponse.put("{\"address\": \"200 Broadway\"}",
				"{\"street\": \"Broadway\", \"houseNumber\": \"200\"}");

		requestAndResponse.put("{\"address\": \"Broadway Av 200b\"}",
				"{\"street\": \"Broadway Av\", \"houseNumber\": \"200b\"}");
		
		requestAndResponse.put("{\"address\": \"200 Broadway 300\"}",
				"{\"street\": \"Broadway 300\", \"houseNumber\": \"200\"}");
		
		requestAndResponse.put("{\"address\": \"Calle 39 No 45\"}",
				"{\"street\": \"Calle 39\", \"houseNumber\": \"No 45\"}");
		
		requestAndResponse.put("{\"address\": \"Calle Aduana, 29\"}",
				"{\"street\": \"Calle Aduana\", \"houseNumber\": \"29\"}");
		
		requestAndResponse.put("{\"address\": \"4, rue de la revolution\"}",
				"{\"street\": \"rue de la revolution\", \"houseNumber\": \"4\"}");
		
		requestAndResponse.put("{\"address\": \"Chillies 39 45 c\"}",
				"{\"street\": \"Chillies 39\", \"houseNumber\": \"45 c\"}");

		requestAndResponse.put("{\"address\": \"Auf der Vogelweise 34\"}",
				"{\"street\": \"Auf der Vogelweise\", \"houseNumber\": \"34\"}");
		
		requestAndResponse.put("{\"address\": \"Am B??chle 23\"}",
				"{\"street\": \"Am B??chle\", \"houseNumber\": \"23\"}");
		
		requestAndResponse.put("{\"address\": \"Auf der Vogelweise 34 b\"}",
				"{\"street\": \"Auf der Vogelweise\", \"houseNumber\": \"34 b\"}");

		return requestAndResponse;
	}

}

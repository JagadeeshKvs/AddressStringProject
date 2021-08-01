package com.friday.project.addressjson.controller;

import static org.junit.jupiter.api.Assertions.*;

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

		String resultString = responseValue.getContentAsString();
		assertNotNull(resultString);
		JSONAssert.assertEquals(expected, resultString, false);
	}

	private Map<String, String> createRequestResponse() {
		Map<String, String> requestAndResponse = new HashMap<String, String>();
		requestAndResponse.put("{\"address\": \"200 Broadway\"}",
				"{\"street\": \"Broadway\", \"houseNumber\": \"200\"}");

		requestAndResponse.put("{\"address\": \"Broadway Av 200b\"}",
				"{\"street\": \"Broadway Av\", \"houseNumber\": \"200b\"}");

		requestAndResponse.put("{\"address\": \"Chillies 39 no 45\"}",
				"{\"street\": \"Chillies 39\", \"houseNumber\": \"no 45\"}");

		requestAndResponse.put("{\"address\": \"Aug ter Vokslae 34\"}",
				"{\"street\": \"Aug ter Vokslae\", \"houseNumber\": \"34\"}");

		return requestAndResponse;
	}

}

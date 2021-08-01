package com.friday.project.addressjson.constant;

import java.util.HashMap;
import java.util.regex.Pattern;

public final class AddressPatterns {

	private HashMap<Integer, Pattern> patternMap = new HashMap<Integer, Pattern>();

	enum Patterns {
		PATTERN_ONE("([0-9].*) ([a-zA-Z]+) ([0-9].*)"),
		PATTERN_TWO("(^[A-Za-z]+.*[\\s]?[0-9.]+.*?)[,]?[\\s]?([\bNo\b|\bno\b][A-Za-z].*?[0-9].*)"),
		PATTERN_THREE("(^[A-Za-z].*[\\s]?[0-9]*?[\\d+$]) ([#]?\\d+$|\\d+[A-Za-z]+$)"),
		PATTERN_FOUR("([a-zA-Z].* [^0-9]?)[,]?[\\s]?(\\d+$|[0-9].*$)"),
		PATTERN_FIVE("(\\d+[a-zA-Z]?)[,]?[\\s]?([^0-9][a-zA-Z].*[\\s]?)");

		private final String value;

		private Patterns(String value) {
			this.value = value;
		}

	}

	public HashMap<Integer, Pattern> loadAddressPatterns() {
		Integer id = 1;
		for (Patterns pattern : Patterns.values()) {
			patternMap.put(id, Pattern.compile(pattern.value));
			id += 1;
		}
		return patternMap;
	}
}

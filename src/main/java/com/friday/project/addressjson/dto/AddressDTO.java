package com.friday.project.addressjson.dto;

public class AddressDTO {

	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "AddressDTO [address=" + address + "]";
	}

}

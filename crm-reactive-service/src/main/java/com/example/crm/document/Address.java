package com.example.crm.document;

import org.springframework.data.mongodb.core.mapping.Field;

public record Address(
		@Field("ctry") String country,String city,String line,
		@Field("zip") String zipCode,AddressType addressType) {

}

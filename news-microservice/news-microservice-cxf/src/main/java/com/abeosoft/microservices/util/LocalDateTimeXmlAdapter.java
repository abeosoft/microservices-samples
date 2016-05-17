package com.abeosoft.microservices.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class LocalDateTimeXmlAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String dateStr) throws Exception {
	return LocalDateTime.parse(dateStr);
    }

    @Override
    public String marshal(LocalDateTime dateObject) throws Exception {
	return DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(dateObject);
    }

}

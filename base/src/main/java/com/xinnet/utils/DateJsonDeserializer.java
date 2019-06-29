package com.xinnet.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.springframework.stereotype.Component;

@Component
public class DateJsonDeserializer extends JsonDeserializer<Date> {
	public static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
		try {
			return format.parse(jsonParser.getText());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	//	public Date deserialize(JsonParser jsonParser, DeserializationContext ctxt, Date intoValue) {
	//		ObjectMapper mapper = new ObjectMapper();
	//		try {
	//			return format.parse(jsonParser.getText());
	//		} catch (Exception e) {
	//			throw new RuntimeException(e);
	//		}
	//	}
}
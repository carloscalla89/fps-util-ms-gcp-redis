package pe.farmaciasperuanas.digital.gcp.redis.application.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class ObjectUtil {
	
	public static String objectToJson(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
        } catch (JsonProcessingException ex) {
        	log.error("objectToJson: ", ex.getMessage());
        }
		return null;
	}
	
	public static <T> T jsonToObject(String json, Class<T> valueType) {
		try {
			return new ObjectMapper().readValue(json, valueType);
        } catch (JsonProcessingException ex) {
        	log.error("jsonToObject: ", ex.getMessage());
        }
		return null;
	}

	public static boolean isJSONValid(String jsonInString ) {
		try {
			final ObjectMapper mapper = new ObjectMapper();
			mapper.readTree(jsonInString);
			return true;
		} catch (IOException e) {
			return false;
		}
	}
}

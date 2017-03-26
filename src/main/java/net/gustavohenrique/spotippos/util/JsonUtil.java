package net.gustavohenrique.spotippos.util;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import net.gustavohenrique.spotippos.exceptions.RequestException;
import net.gustavohenrique.spotippos.models.Property;

public class JsonUtil {

	private static Gson g = new Gson();
	
	public static <T> T fromJson(String str, Class<T> clazz) throws RequestException {
		try {
			return g.fromJson(str, clazz);
		}
		catch (JsonSyntaxException e) {
			throw new RequestException("The request have an invalid JSON. " + e.getMessage());
		}
	}

	public static String toJson(Object obj) {
		return g.toJson(obj);
	}
	
	public static <T> JsonProperty fromJsonProperty(String str) throws RequestException {
		try {
			Type type = new TypeToken<JsonProperty>(){}.getType();
			return g.fromJson(str, type);
		}
		catch (JsonSyntaxException e) {
			throw new RequestException("The request have an invalid JSON. " + e.getMessage());
		}
	}
	
	public class JsonProperty {
		public int foundProperties;
		public List<Property> properties;
	}

}

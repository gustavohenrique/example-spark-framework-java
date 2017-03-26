package net.gustavohenrique.spotippos.util;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import net.gustavohenrique.spotippos.exceptions.RequestException;

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

}

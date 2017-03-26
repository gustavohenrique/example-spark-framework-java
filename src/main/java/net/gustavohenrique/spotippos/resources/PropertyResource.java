package net.gustavohenrique.spotippos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gustavohenrique.spotippos.controllers.PropertyController;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.util.JsonUtil;
import spark.Spark;

@Component
public class PropertyResource {
	
	@Autowired
	public PropertyController controller;
	
	private final String JSON = "application/json";
	private final int CREATED = 201;
	private final int BAD_REQUEST = 400;
	
	public PropertyResource() {
		Spark.path("/properties", () -> {
			Spark.post("/", (req, res) -> {
				int statusCode = CREATED;
				String result = "";
				try {
					Property created = controller.create(req.body());
					result = JsonUtil.toJson(created);
				}
				catch (Exception b) {
					statusCode = BAD_REQUEST;
					result = b.getMessage();
				}
				res.status(statusCode);
				res.type(JSON);
				return result;
			});
		});
	}

}

package net.gustavohenrique.spotippos.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gustavohenrique.spotippos.controllers.PropertyController;
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
					result = controller.create(req.body());
				}
				catch (Exception b) {
					statusCode = BAD_REQUEST;
					result = b.getMessage();
				}
				res.status(statusCode);
				res.type(JSON);
				return result;
			});
			
			Spark.get("/:id", (req, res) -> {
				int statusCode = 200;
				String result = "";
				int id = 0;
				try {
					id = Integer.valueOf(req.params(":id"));
				}
				catch (Exception e) {
					statusCode = BAD_REQUEST;
					result = "Invalid ID.";
				}
				try {
					result = controller.findById(id);
				}
				catch (Exception e) {
					statusCode = 404;
					result = e.getMessage();
				}
				res.status(statusCode);
				res.type(JSON);
				return result;
			});
			
			Spark.get("", (req, res) -> {
				int statusCode = 200;
				String result = "";
				try {
					int ax = Integer.valueOf(req.raw().getParameter("ax"));
					int ay = Integer.valueOf(req.raw().getParameter("ay"));
					int bx = Integer.valueOf(req.raw().getParameter("bx"));
					int by = Integer.valueOf(req.raw().getParameter("by"));
					result = controller.findByArea(ax, ay, bx, by);
				}
				catch (NumberFormatException e) {
					statusCode = BAD_REQUEST;
					result = "Invalid coordinates.";
				}
				res.status(statusCode);
				res.type(JSON);
				return result;
			});
		});
	}

}

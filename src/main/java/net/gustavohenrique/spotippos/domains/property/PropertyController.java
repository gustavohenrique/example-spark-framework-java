package net.gustavohenrique.spotippos.domains.property;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.gustavohenrique.spotippos.exceptions.RequestException;
import net.gustavohenrique.spotippos.exceptions.ValidationException;
import net.gustavohenrique.spotippos.util.JsonUtil;

@Controller
public class PropertyController {

	@Autowired
	public PropertyService propertyService;
	
	@Autowired
	public PropertyValidator validator;
	
	public String create(String data) throws RequestException, ValidationException {
		if (data == null || "".equals(data)) {
			throw new RequestException("No data sent via POST.");
		}
		Property property = JsonUtil.fromJson(data, Property.class);
		validator.validate(property);
		List<Province> provinces = propertyService.findProvincesBy(property);
		if (provinces.isEmpty()) {
			throw new RequestException("No provinces found with coordinates " + property.x + "," + property.y);
		}
		property.setProvinces(provinces);
		Property created = propertyService.create(property);
		return JsonUtil.toJson(created);
	}

	public String findById(int id) throws Exception {
		Property property = propertyService.findById(id);
		return JsonUtil.toJson(property);
	}

	public String findByArea(int ax, int ay, int bx, int by) {
		List<Property> properties = propertyService.findByArea(ax, ay, bx, by);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("foundProperties", properties.size());
		map.put("properties", properties);
		return JsonUtil.toJson(map);
	}

}

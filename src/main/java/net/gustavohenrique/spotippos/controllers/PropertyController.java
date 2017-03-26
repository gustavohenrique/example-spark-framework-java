package net.gustavohenrique.spotippos.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import net.gustavohenrique.spotippos.exceptions.RequestException;
import net.gustavohenrique.spotippos.exceptions.ValidationException;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;
import net.gustavohenrique.spotippos.services.PropertyService;
import net.gustavohenrique.spotippos.services.ProvinceService;
import net.gustavohenrique.spotippos.util.JsonUtil;
import net.gustavohenrique.spotippos.validators.PropertyValidator;

@Controller
public class PropertyController {

	@Autowired
	public PropertyService propertyService;
	
	@Autowired
	private ProvinceService provinceService;
	
	@Autowired
	public PropertyValidator validator;
	
	public String create(String data) throws RequestException, ValidationException {
		if (data == null || "".equals(data)) {
			throw new RequestException("No data sent via POST.");
		}
		Property property = JsonUtil.fromJson(data, Property.class);
		validator.validate(property);
		List<Province> provinces = provinceService.findBy(property);
		if (provinces.isEmpty()) {
			throw new RequestException("No provinces found with coordinates " + property.x + "," + property.y);
		}
		property.provinces = provinces;
		Property created = propertyService.create(property);
		return JsonUtil.toJson(created);
	}

	public String findById(int id) throws Exception {
		Property property = propertyService.findById(id);
		return JsonUtil.toJson(property);
	}

}

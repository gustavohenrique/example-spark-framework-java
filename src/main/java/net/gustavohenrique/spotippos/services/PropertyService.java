package net.gustavohenrique.spotippos.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gustavohenrique.spotippos.exceptions.RequestException;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;
import net.gustavohenrique.spotippos.repository.PropertyRepository;

@Service
public class PropertyService {
	
	@Autowired
	private PropertyRepository propertyRepository;
	
	public Property create(Property property) throws RequestException {
		if (property.id > 0) {
			throw new RequestException("Please unset the id field for new property.");
		}
		return propertyRepository.create(property);
	}

	public Property findById(int id) throws Exception {
		Property property = propertyRepository.findById(id);
		if (property == null) {
			throw new Exception("Property not found using ID=" + id);
		}
		return property;
	}

	public List<Property> findByArea(int ax, int ay, int bx, int by) {
		List<Property> result = new ArrayList<Property>();
		for (Property property : propertyRepository.getProperties()) {
			Map<String, Integer> upperLeft = new HashMap<String, Integer>() {{ put("x", ax); put("y", ay); }};
			Map<String, Integer> bottomRight = new HashMap<String, Integer>() {{ put("x", bx); put("y", by); }};
			
			if (isPropertyInsideRectangularArea(property, upperLeft, bottomRight)) {
				result.add(property);
			}
		}
		return result;
	}

	public List<Province> findProvincesBy(Property property) {
		List<Province> result = new ArrayList<Province>();
		for (Province province : propertyRepository.getProvinces()) {
			Map<String, Integer> upperLeft = province.boundaries.get("upperLeft");
			Map<String, Integer> bottomRight = province.boundaries.get("bottomRight");
			
			if (isPropertyInsideRectangularArea(property, upperLeft, bottomRight)) {
				result.add(province);
			}
		}
		return result;
	}
	
	public void setPropertyRepository(PropertyRepository repository) {
		this.propertyRepository = repository;
	}
	
	private boolean isPropertyInsideRectangularArea(Property property, Map<String, Integer> upperLeft, Map<String, Integer> bottomRight) {
		Integer ax = Math.min(upperLeft.get("x"), bottomRight.get("x"));
		Integer ay = Math.min(upperLeft.get("y"), bottomRight.get("y"));
		Integer bx = Math.max(upperLeft.get("x"), bottomRight.get("x"));
		Integer by = Math.max(upperLeft.get("y"), bottomRight.get("y"));
		
		return ax <= property.x && bx >= property.x && ay <= property.y && by >= property.y;
	}

}

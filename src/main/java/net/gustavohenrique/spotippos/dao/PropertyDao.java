package net.gustavohenrique.spotippos.dao;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;

@Repository
public class PropertyDao {

	private List<Property> properties = getProperties();

	public Property create(Property property) {
		int total = properties.size();
		property.id = properties.get(total - 1).id + 1;
		properties.add(property);
		return property;
	}
	
	private List<Property> getProperties() {
		Province ruja = new Province();
		ruja.name = "Ruja";
		ruja.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 400); put("y", 1000); }});
		ruja.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 1100); put("y", 500); }});
		
		Property property = new Property();
		property.id = 665;
		property.title = "Imóvel código 665, com 1 quarto e 1 banheiro";
		property.price = new BigDecimal("540000");
		property.description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit.";
		property.x = 667;
		property.y = 556;
		property.beds = 1;
		property.baths = 1;
		property.squareMeters = 42;		
		property.provinces = Arrays.asList(ruja);
		return Arrays.asList(property);
	}

}

package net.gustavohenrique.spotippos.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;

@Repository
public class PropertyRepository {

	private List<Property> properties = getProperties();
	private List<Province> provinces = getProvinces();

	public Property create(Property property) {
		int total = properties.size();
		property.id = properties.get(total - 1).id + 1;
		properties.add(property);
		return property;
	}
	
	public Property findById(int id) {
		for (Property property : properties) {
			if (property.id == id) {
				return property;
			}
		}
		return null;
	}
	
	public List<Province> getProvinces() {
		if (provinces == null) {
			provinces = new ArrayList<Province>();
			provinces.add(fake("Gode", 0, 1000, 600, 500));
			provinces.add(fake("Ruja", 400, 1000, 1100, 500));
			provinces.add(fake("Jaby", 1100, 1000, 1400, 500));
			provinces.add(fake("Scavy", 0, 500, 600, 0));
			provinces.add(fake("Groola", 600, 500, 800, 0));
			provinces.add(fake("Nova", 800, 500, 1400, 0));
		}
		return provinces;
	}
	
	public List<Property> getProperties() {
		if (properties == null) {
			properties = new ArrayList<Property>();
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
			property.setProvinces(Arrays.asList(ruja));
			
			properties.add(property);
		}
		
		return properties;
	}

	private Province fake(final String name, final int ax, final int ay, final int bx, final int by) {
		Province province = new Province();
		province.name = name;
		province.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", ax); put("y", ay); }});
		province.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", bx); put("y", by); }});
		return province;
	}
	
}

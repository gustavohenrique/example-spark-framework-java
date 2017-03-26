package net.gustavohenrique.spotippos.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import net.gustavohenrique.spotippos.models.Property;

@Repository
public class PropertyDao {

	private List<Property> properties = new ArrayList<Property>();

	public Property create(Property property) {
		int total = properties.size();
		property.id = total + 1;
		properties.add(property);
		return property;
	}

}

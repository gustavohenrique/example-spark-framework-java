package net.gustavohenrique.spotippos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.gustavohenrique.spotippos.dao.PropertyDao;
import net.gustavohenrique.spotippos.models.Property;

@Service
public class PropertyService {
	
	@Autowired
	private PropertyDao propertyDao;
	
	public Property create(Property property) {
		return propertyDao.create(property);
	}

}

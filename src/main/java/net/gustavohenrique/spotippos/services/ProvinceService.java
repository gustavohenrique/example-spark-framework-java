package net.gustavohenrique.spotippos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import net.gustavohenrique.spotippos.dao.ProvinceDao;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;

@Component
public class ProvinceService {

	@Autowired
	private ProvinceDao propertyDao;
	
	public List<Province> findBy(Property property) {
		return propertyDao.findBy(property);
	}

}

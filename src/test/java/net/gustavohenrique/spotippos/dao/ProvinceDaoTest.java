package net.gustavohenrique.spotippos.dao;

import java.util.List;

import org.junit.Test;

import junit.framework.TestCase;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;

public class ProvinceDaoTest extends TestCase {

	final ProvinceDao dao = new ProvinceDao();
	
	@Test
	public void testFindProvincesByPropertyCoordinates() {
		Property property = new Property();
		property.x = 200;
		property.y = 600;
		
		List<Province> provinces = dao.findBy(property);
		assertEquals(1, provinces.size());
		assertEquals("Gode", provinces.get(0).name);
	}
	
	@Test
	public void testShouldReturnsAnEmptyListWhenNotFound() {
		Property property = new Property();
		property.x = 9999;
		property.y = 9999;
		
		List<Province> provinces = dao.findBy(property);
		assertEquals(0, provinces.size());
	}
}

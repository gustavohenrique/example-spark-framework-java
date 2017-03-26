package net.gustavohenrique.spotippos.domains.property;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import net.gustavohenrique.spotippos.SpringTestCase;
import net.gustavohenrique.spotippos.domains.property.Property;
import net.gustavohenrique.spotippos.domains.property.PropertyRepository;
import net.gustavohenrique.spotippos.domains.property.PropertyService;
import net.gustavohenrique.spotippos.domains.property.Province;
import net.gustavohenrique.spotippos.exceptions.RequestException;

public class PropertyServiceTest extends SpringTestCase {

	@Autowired
	private PropertyService service;
	
	@Test
	public void testCreateShouldFailWhenThePropertyHaveId() {
		Property property = new Property();
		property.id = 10;
		try {
			service.create(property);
			fail();
		}
		catch (RequestException e) {}
	}
	
	@Test
	public void testFindProvincesByPropertyCoordinates() {
		Property property = new Property();
		property.x = 200;
		property.y = 600;
		
		List<Province> provinces = service.findProvincesBy(property);
		assertEquals(1, provinces.size());
		assertEquals("Gode", provinces.get(0).name);
	}
	
	@Test
	public void testShouldReturnsAnEmptyListWhenNotFoundProvinces() {
		Property property = new Property();
		property.x = 9999;
		property.y = 9999;
		
		List<Province> provinces = service.findProvincesBy(property);
		assertEquals(0, provinces.size());
	}
	
	@Test
	public void testFindPropertiesByArea() {
		Property property1 = new Property();
		property1.title = "Property1";
		property1.x = 100;
		property1.y = 100;
		
		Property property2 = new Property();
		property2.title = "Property2";
		property2.x = 200;
		property2.y = 500;
		
		service.setPropertyRepository(mockRepository(property1, property2));
		
		List<Property> properties = service.findByArea(50, 50, 300, 220);
		assertEquals(1, properties.size());
		assertEquals("Property1", properties.get(0).title);
		
		properties = service.findByArea(200, 500, 300, 550);
		assertEquals(1, properties.size());
		assertEquals("Property2", properties.get(0).title);
	}
	
	@Test
	public void testShouldReturnEmptyListWhenNotFoundPropertiesInsideArea() {
		List<Property> properties = service.findByArea(10, 10, 10, 10);
		assertEquals(0,  properties.size());
	}

	private PropertyRepository mockRepository(Property property1, Property property2) {
		List<Property> mockProperties = Arrays.asList(property1, property2); 
		PropertyRepository mockRepository = Mockito.mock(PropertyRepository.class);
		Mockito.when(mockRepository.getProperties()).thenReturn(mockProperties);
		return mockRepository;
	}
}

package net.gustavohenrique.spotippos.controllers;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import net.gustavohenrique.spotippos.SpringTestCase;
import net.gustavohenrique.spotippos.exceptions.RequestException;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.util.JsonUtil;
import net.gustavohenrique.spotippos.util.JsonUtil.JsonProperty;

public class PropertyControllerTest extends SpringTestCase {

	@Autowired
	private PropertyController controller;
	
	@Test
	public void testShouldFailWhenNoDataWasSent() {
		try {
			controller.create("");
		}
		catch (Exception e) {}
	}
	
	@Test
	public void testCreateProperty() {
		try {
			final String data = "{" +
				"\"title\": \"Imóvel código 1, com 5 quartos e 4 banheiros\"," +
				"\"description\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit.\", " +
				"\"price\": 1250000, " +
				"\"x\": 222, " +
				"\"y\": 444, " +
				"\"beds\": 4, " +
				"\"baths\": 3, " +
				"\"squareMeters\": 210" +
			"}";
			String created = controller.create(data);
			Property property = JsonUtil.fromJson(created, Property.class);
			assertEquals(666, property.id);
			assertEquals("Imóvel código 1, com 5 quartos e 4 banheiros", property.title);
			assertEquals(1, property.getProvinces().length);
			assertEquals("Scavy", property.getProvinces()[0]);
		}
		catch (Exception e) {}
	}
	
	@Test
	public void testFindById() {
		try {
			String data = controller.findById(665);
			Property property = JsonUtil.fromJson(data, Property.class);
			assertEquals(665, property.id);
			assertEquals("Imóvel código 665, com 1 quarto e 1 banheiro", property.title);
			assertEquals(1, property.getProvinces().length);
			assertEquals("Ruja", property.getProvinces()[0]);
		}
		catch (Exception e) {}
	}
	
	@Test
	public void testFindByIdShouldThrowsExceptionWhenNotFound() {
		try {
			controller.findById(999999);
			fail();
		}
		catch (Exception e) {}
	}
	
	@Test
	public void testFindPropertiesByArea() throws RequestException {
		String data = controller.findByArea(0, 0, 1400, 1000);
		JsonProperty result = JsonUtil.fromJsonProperty(data);
		assertEquals(2, result.foundProperties);
		assertEquals("Ruja", result.properties.get(0).getProvinces()[0]);
		assertEquals("Scavy", result.properties.get(1).getProvinces()[0]);
	}
}

package net.gustavohenrique.spotippos.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.util.JsonUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/META-INF/spring/applicationContext-test.xml")
public class PropertyControllerTest extends TestCase {

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
			assertEquals(1, property.provinces.size());
			assertEquals("Scavy", property.provinces.get(0).name);
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
			assertEquals(1, property.provinces.size());
			assertEquals("Ruja", property.provinces.get(0).name);
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
}

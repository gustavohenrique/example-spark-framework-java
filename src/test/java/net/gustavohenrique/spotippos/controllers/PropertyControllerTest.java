package net.gustavohenrique.spotippos.controllers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import junit.framework.TestCase;
import net.gustavohenrique.spotippos.models.Property;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="/META-INF/spring/applicationContext-test.xml")
public class PropertyControllerTest extends TestCase {

	@Autowired
	private PropertyController propertyController;
	
	@Test
	public void testShouldFailWhenNoDataWasSent() {
		try {
			propertyController.create("");
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
			Property created = propertyController.create(data);
			assertEquals(1, created.id);
			assertEquals("Imóvel código 1, com 5 quartos e 4 banheiros", created.title);
			assertEquals(1, created.provinces.size());
			assertEquals("Scavy", created.provinces.get(0).name);
		}
		catch (Exception e) {}
	}
}

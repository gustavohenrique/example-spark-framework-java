package net.gustavohenrique.spotippos.services;

import org.junit.Test;

import junit.framework.TestCase;
import net.gustavohenrique.spotippos.exceptions.RequestException;
import net.gustavohenrique.spotippos.models.Property;

public class PropertyServiceTest extends TestCase {

	@Test(expected=RequestException.class)
	public void testCreateShouldFailWhenThePropertyHaveId() {
		PropertyService service = new PropertyService();
		Property property = new Property();
		property.id = 10;
		try {
			service.create(property);
		}
		catch (RequestException e) {}
	}
}

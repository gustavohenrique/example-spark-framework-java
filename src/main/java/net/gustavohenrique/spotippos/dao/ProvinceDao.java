package net.gustavohenrique.spotippos.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import net.gustavohenrique.spotippos.models.Property;
import net.gustavohenrique.spotippos.models.Province;

@Repository
public class ProvinceDao {
	
	public List<Province> findBy(Property property) {
		List<Province> result = new ArrayList<Province>();
		for (Province province : getProvinces()) {
			Map<String, Integer> upperLeft = province.boundaries.get("upperLeft");
			Map<String, Integer> bottomRight = province.boundaries.get("bottomRight");
			
			Integer ax = Math.min(upperLeft.get("x"), bottomRight.get("x"));
			Integer ay = Math.min(upperLeft.get("y"), bottomRight.get("y"));
			Integer bx = Math.max(upperLeft.get("x"), bottomRight.get("x"));
			Integer by = Math.max(upperLeft.get("y"), bottomRight.get("y"));
			
			if (isPropertyInsideRectangularArea(property, ax, ay, bx, by)) {
				result.add(province);
			}
		}
		return result;
	}

	private boolean isPropertyInsideRectangularArea(Property property, Integer ax, Integer ay, Integer bx, Integer by) {
		return ax <= property.x && bx >= property.x && ay <= property.y && by >= property.y;
	}
	
	private List<Province> getProvinces() {
		List<Province> provinces = new ArrayList<Province>();
		provinces.add(fake("Gode", 0, 1000, 600, 500));
		provinces.add(fake("Ruja", 400, 1000, 1100, 500));
		provinces.add(fake("Jaby", 1100, 1000, 1400, 500));
		provinces.add(fake("Scavy", 0, 500, 600, 0));
		provinces.add(fake("Groola", 600, 500, 800, 0));
		provinces.add(fake("Nova", 800, 500, 1400, 0));
		return provinces;
	}

	private Province fake(final String name, final int ax, final int ay, final int bx, final int by) {
		Province province = new Province();
		province.name = name;
		province.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", ax); put("y", ay); }});
		province.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", bx); put("y", by); }});
		return province;
	}

}

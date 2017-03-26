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
			
			Integer ax = upperLeft.get("x");
			Integer ay = upperLeft.get("y");
			Integer bx = bottomRight.get("x");
			Integer by = bottomRight.get("y");
			
			if (upperLeft.get("x") > bottomRight.get("x")) {
				ax = bottomRight.get("x");
				bx = upperLeft.get("x");
			}
			
			if (upperLeft.get("y") > bottomRight.get("y")) {
				ay = bottomRight.get("y");
				by = upperLeft.get("y");
			}
			
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
		
		Province gode = new Province();
		gode.name = "Gode";
		gode.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 0); put("y", 1000); }});
		gode.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 600); put("y", 500); }});
		provinces.add(gode);
		
		Province ruja = new Province();
		ruja.name = "Ruja";
		ruja.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 400); put("y", 1000); }});
		ruja.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 1100); put("y", 500); }});
		provinces.add(ruja);
		
		Province jaby = new Province();
		jaby.name = "Jaby";
		jaby.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 1100); put("y", 1000); }});
		jaby.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 1400); put("y", 500); }});
		provinces.add(jaby);
		
		Province scavy = new Province();
		scavy.name = "Scavy";
		scavy.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 0); put("y", 500); }});
		scavy.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 600); put("y", 0); }});
		provinces.add(scavy);
		
		Province groola = new Province();
		groola.name = "Groola";
		groola.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 600); put("y", 500); }});
		groola.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 800); put("y", 0); }});
		provinces.add(groola);
		
		Province nova = new Province();
		nova.name = "Scavy";
		nova.boundaries.put("upperLeft", new HashMap<String, Integer>() {{ put("x", 800); put("y", 500); }});
		nova.boundaries.put("bottomRight", new HashMap<String, Integer>() {{ put("x", 1400); put("y", 0); }});
		provinces.add(nova);
		
		return provinces;
	}

}

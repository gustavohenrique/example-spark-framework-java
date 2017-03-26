package net.gustavohenrique.spotippos.domains.property;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.gustavohenrique.spotippos.interfaces.Number;
import net.gustavohenrique.spotippos.interfaces.Required;

public class Property {

	public int id;
	
	@Required
	public String title;
	
	@Required
	public BigDecimal price;
	
	@Required
	public String description;
	
	@Number(min=0, max=1400)
	public int x;
	
	@Number(min=0, max=1000)
	public int y;
	
	@Number(min=1, max=5)
	public int beds;
	
	@Number(min=1, max=4)
	public int baths;
	
	@Number(min=20, max=240)
	public int squareMeters;
	
	private String[] provinces;
	
	public String[] getProvinces() {
		return provinces;
	}
	
	public void setProvinces(String[] provinces) {
		this.provinces = provinces;
	}
	
	public void setProvinces(List<Province> provinces) {
		List<String> list = new ArrayList<String>();
		for (Province province : provinces) {
			list.add(province.name);
		}
		String[] array = new String[list.size()];
		list.toArray(array);
		this.provinces = array;
	}

}

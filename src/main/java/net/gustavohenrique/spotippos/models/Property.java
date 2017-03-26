package net.gustavohenrique.spotippos.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.gustavohenrique.spotippos.validators.annotations.Number;
import net.gustavohenrique.spotippos.validators.annotations.Required;

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
	
	public List<Province> provinces = new ArrayList<Province>();

}

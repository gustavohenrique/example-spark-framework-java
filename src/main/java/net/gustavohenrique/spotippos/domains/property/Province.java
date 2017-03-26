package net.gustavohenrique.spotippos.domains.property;

import java.util.HashMap;
import java.util.Map;

public class Province {

	public String name;
	public Map<String, Map<String, Integer>> boundaries = new HashMap<String, Map<String, Integer>>();
	
	public String toString() {
		return this.name;
	}
}

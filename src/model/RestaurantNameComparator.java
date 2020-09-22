package model;

import java.util.Comparator;

public class RestaurantNameComparator implements Comparator<Restaurant> {

	@Override
	public int compare(Restaurant o1, Restaurant o2) {
		int comp;
		String r1 = o1.getName();
		String r2= o2.getName();
		
		comp = r1.compareTo(r2);
		
		return comp;
	}

}

package com.tutorial.tsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;


/**
 * 
 * 
 * @author Pallavi Ramicetty  04/02/2016
 */
public class TSP {


	public TSP() {
		
	}
 
	public static void main(String[] args) {
		List<City> cities= new ArrayList<City>();
		cities.add(new City("Tirupati", "tpt"));
		cities.add(new City("Hyderabad","hyd"));
		cities.add(new City("Bangalore","blr"));
		cities.add(new City("Chennai", "chn"));
		cities.add(new City("Pune", "pune"));
		HashMap<String, Integer> distance= new HashMap<String, Integer>();
		distance.put("tpt-hyd",500);
		distance.put("tpt-blr", 150);
		distance.put("tpt-chn", 10);
		distance.put("tpt-pune", 700);
		distance.put("hyd-blr", 60);
		distance.put("hyd-chn", 5);
		distance.put("hyd-pune", 250);
		distance.put("blr-chn", 250);
		distance.put("blr-pune", 100);
		distance.put("chn-pune", 800);
		
		System.out.println(getTSPRoute(cities, cities.get(0), distance));
		

	}

	private static List<City> getTSPRoute(List<City> cities, City current, HashMap<String, Integer> distance) {
		City nearestCity = null;
		if(!cities.isEmpty() && cities.size() == 1){
			List<City> p = new ArrayList<City>();
			p.add(current);
			return p;
		}else{
			nearestCity=getNearestCity(current, getUnvisitedCities.apply(current,cities), distance);
		}
		
		List<City> path = getTSPRoute(cities, nearestCity, distance);
		path.add(0, current);
		return path;
	}

	private static City getNearestCity(City current, List<City> unvisitedCities, HashMap<String, Integer> distance) {
		//Integer nearestDistance = Integer.MAX_VALUE;
		City nearestCity = unvisitedCities.get(0);
		Optional<City> nearestCity1=unvisitedCities.stream().reduce((a,b)-> getDistance(current, a, distance)>getDistance(current, b, distance) ? b : a);
		if(nearestCity1.isPresent())
			nearestCity=nearestCity1.get();
		/*for (City city : unvisitedCities) {			
			Integer d = getDistance(current, city, distance);
			if(d < nearestDistance){
				nearestCity = city;
				nearestDistance = d;
			}
		}*/
		return nearestCity;
	}
		
	
	private static Integer getDistance(City current, City city, HashMap<String, Integer> distance) {
		Integer d = distance.get(current.getCode() + "-" + city.getCode());
		if(d == null){
			d = distance.get(city.getCode() + "-" + current.getCode());
		}
		return d;
	}
	

	/*private static List<City> getUnvisitedCities(City current, List<City> cities) {
		cities.remove(current);
		return cities;
	}*/
	private static BiFunction<City, List<City> , List<City> > getUnvisitedCities=(a,b) -> {
		b.remove(a);
		return b;
		
	};
	
}

 
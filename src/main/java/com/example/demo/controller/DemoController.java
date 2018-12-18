package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
class DemoController {
	@Autowired 
	RestTemplate restTemplate;
	@GetMapping("/top3/{from}/{dest}/{apikey}")
	public String getTop3(@PathVariable String from, @PathVariable String dest, @PathVariable String apikey) {
		String result;
		from = "BOS";
		dest = "";
		apikey = "oq7d5PswFncAvtf1rNLpW5OT8c5F1KxK";
		String uri = "http://api.sandbox.amadeus.com/v1.2/flights/inspiration-search?origin=" + from + "&departure_date=2015-09-06--2015-09-26&duration=7--9&max_price=500&apikey=" + apikey;
		String subResult = null;
		subResult = restTemplate.getForObject(uri, String.class);
		List<String> resultList = getTop3Result(subResult);
		return resultList.toString();
	}
    private List<String> getTop3Result(String subResult) {
    	//1 split subResult to treeMap
    	String[] example = {"price:20, example0 desc","price:22,example1","price:18, example2","price:17, example 3"};
    	//2 return top 3
    	Map<Double, String> map = new TreeMap<Double, String>();
    	
    	map.put(new Double(20), example[0]);
    	
    	int resultLength = 3;
    	int i = 0;
    	List<String> list = new ArrayList<String>();
    	for(Map.Entry<Double,String> m: map.entrySet()) {
    		i++;
    		if(i >= resultLength) {
    			break;
    		}
    		list.add(m.getValue());
    	}
    	
    	
    	return list;
    	
    	
    	
    }
}

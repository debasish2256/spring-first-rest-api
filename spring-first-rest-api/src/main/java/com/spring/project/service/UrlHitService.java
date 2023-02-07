package com.spring.project.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class UrlHitService {

	private static int hitCount;
	private static Map<String, Integer> hitCountMap = new HashMap<>();

	public int getHitCount(String username) {
		hitCount++;
		hitCountMap.put(username,hitCountMap.getOrDefault(username, 0)+1);
		return hitCountMap.get(username);
	}

	public Map<String, Integer> getHitCountSummary() {
		return hitCountMap;	
	}

	public static int getHitCount() {
		return hitCount;
		
	}
}

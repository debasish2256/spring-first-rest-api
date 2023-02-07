package com.spring.project;

import java.util.HashMap;
import java.util.Map;

import com.spring.project.service.UrlHitService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/visitor-count-app")
public class UrlHitController {

	@Autowired
	private UrlHitService urlHitService;

	@GetMapping("total-count")
	public Map<String, Integer> getHitCount() {
		Map<String, Integer> hitCountMap = new HashMap<>();
		hitCountMap.put("visitors", UrlHitService.getHitCount());
		return hitCountMap;
	}
	
	@GetMapping("count-summary")
	public Map<String, Map<String, Integer>> getSummaryHitCount() {
		Map<String,Map<String, Integer>> hitCountMap = new HashMap<>();
		hitCountMap.put("visitors-summary", urlHitService.getHitCountSummary());
		return hitCountMap;
	}
	
	@GetMapping(value = {"username/{username}/count","username/{username}"})
	public Map<String, Object> getHitCount(@PathVariable String username) {
	    Map<String, Object> hitCountMap = new HashMap<>();
	    hitCountMap.put("username", username);
	    hitCountMap.put("hitcount", urlHitService.getHitCount(username));
	    return hitCountMap;
	}
}

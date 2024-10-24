package com.mst.controller;

import java.util.HashMap;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "loader-service", url = "${loader.service.url}")
public interface LoaderClient {

	@GetMapping("/most-label")
	public String developerMostOccurrence(@RequestParam String label, @RequestParam String since);

	@GetMapping("/{developer_id}/label-aggregate")
	public HashMap<String, Integer> aggregationOfLabel(@PathVariable String developer_id, @RequestParam String since);

	@GetMapping("/{developer_id}/task-amount")
	public int totalTasks(@PathVariable String developer_id, @RequestParam String since);
}

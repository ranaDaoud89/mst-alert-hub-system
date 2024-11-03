package com.mst.api;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.mst.model.Metric;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface MetricController {

	@Operation(summary = " create new Metric Entity ", tags = {"Metrics" , "POST"})
	@ApiResponses({
		@ApiResponse(responseCode = "200" , content = {@Content (schema = @Schema(implementation = Metric.class), mediaType = "application/json")}),
		@ApiResponse(responseCode = "500" , content = { @Content(schema = @Schema()) }) })
	ResponseEntity<Metric> createMetric(@RequestBody Metric metric);
	
	ResponseEntity<Metric> getMetricById(@PathVariable Integer id);
	

	@Operation(summary = "Get metrics by list of ids", tags = { "Metrics", "get",})
	  @ApiResponses({
	      @ApiResponse(responseCode = "200", content = {
	          @Content(schema = @Schema(implementation = Metric.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "204", description = "There are no metrics", content = {
	          @Content(schema = @Schema()) }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	ResponseEntity<List<Metric>> getMetricsByIds(@RequestBody List<Integer> id);
	
	ResponseEntity<Metric> updateMetric(@PathVariable Integer id, @RequestBody Metric metric);
	
	ResponseEntity<HttpStatus> deleteMetric(@PathVariable Integer id);
	
	
	@Operation(summary = "Retrieve all Metrics", tags = { "Metrics", "get",})
	  @ApiResponses({
	      @ApiResponse(responseCode = "200", content = {
	          @Content(schema = @Schema(implementation = Metric.class), mediaType = "application/json") }),
	      @ApiResponse(responseCode = "204", description = "There are no metrics", content = {
	          @Content(schema = @Schema()) }),
	      @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
	ResponseEntity<List<Metric>> getAllMetrics();

	ResponseEntity<Map<Integer, Boolean>> checkIfMetricsIdsExist(List<Integer> metricsIds);

}

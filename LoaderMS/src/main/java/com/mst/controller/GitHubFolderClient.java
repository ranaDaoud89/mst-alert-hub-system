package com.mst.controller;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.mst.beans.GitHubFile;

@FeignClient(name = "githubClient", url = "https://api.github.com/repos")
public interface GitHubFolderClient {
	// Fetch the contents of a folder in a GitHub repo
	@GetMapping("/{owner}/{repo}/contents/{folderPath}")
	ResponseEntity<List<GitHubFile>> getFilesFromFolder(@PathVariable String owner, @PathVariable String repo,
			@PathVariable String folderPath);
}

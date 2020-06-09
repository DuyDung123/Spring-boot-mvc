package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.dto.NewDTO;
import com.laptrinhjavaweb.repository.NewRepository;

@RestController(value = "newAPIOfAdmin")
public class NewAPI {
	
	@Autowired
	NewRepository newRepository;
	
	@PostMapping(value = "/api/new")
	public NewDTO createNew(@RequestBody NewDTO newDTO) {
		return newDTO;
	}
	
	@PutMapping(value = "/api/new")
	public NewDTO updateNew(@RequestBody NewDTO newDTO) {
		return newDTO;
	}
	
	@DeleteMapping(value = "/api/new")
	public void deleteNew(@RequestBody long[] ids) {
	}
}

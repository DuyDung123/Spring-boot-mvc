package com.laptrinhjavaweb.api.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.service.INewService;

@RestController(value = "newAPIOfWeb")
public class NewAPI {

	@Autowired
	INewService newService;
	
	@RequestMapping(value = "/new/api", method = RequestMethod.GET)
	@ResponseBody
	public NewModel createNew(@RequestBody NewModel newModel) {
		newModel.setListResult(newService.findAll());
		return newModel;
	}
}

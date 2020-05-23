package com.laptrinhjavaweb.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.service.HomeService;

@Service
public class HomeServiceimpl implements HomeService{

	@Override
	public List<String> loadMenu() {
		List<String> menus = new ArrayList<String>();
		menus.add("Blog java");
		menus.add("hưỡng dẫn học java web");
		menus.add("liên hệ");
		menus.add("thanh toán");
		return menus;
	}
	
}

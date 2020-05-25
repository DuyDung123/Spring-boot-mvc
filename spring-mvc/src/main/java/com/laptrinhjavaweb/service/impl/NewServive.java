package com.laptrinhjavaweb.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.service.INewService;

public class NewServive implements INewService{
	
	@Autowired
	private INewDAO newDao;
	
	@Autowired
	private ICategoryDAO categoryDao;

	@Override
	public List<NewModel> findAll( ) {
		return newDao.finldAll(pageble);
	}
}

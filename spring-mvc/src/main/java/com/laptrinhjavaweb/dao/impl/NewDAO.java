package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;

@Repository
public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> finldAll( ) {
		StringBuilder sql = new StringBuilder("select * from news");
		return query(sql.toString(), new NewMapper());
	}		
}

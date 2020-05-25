package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface ICategoryDAO extends GenericDAO<CategoryModel> {
	
	List<CategoryModel> findAll();
	CategoryModel findOne(long id);
	CategoryModel findOneByCode(String code);
	List<CategoryModel>findAllAndSort(Pageble pageble);
	int getTotalItem();
	Long save(CategoryModel categoryModel);
	void update(CategoryModel updateCategory);
	void delete(long id);
}

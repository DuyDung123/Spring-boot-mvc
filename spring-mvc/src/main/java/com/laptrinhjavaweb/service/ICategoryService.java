package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pageble;

public interface ICategoryService {
	CategoryModel findOne(long id);
	List<CategoryModel> findAll();
	List<CategoryModel> findAllAndSort(Pageble pageble);
	int getTotalItem();
	CategoryModel findOneByCode(String code);
	CategoryModel save(CategoryModel categoryModel);
	CategoryModel update(CategoryModel updateCategory);
	void delete(Long[] ids );
}

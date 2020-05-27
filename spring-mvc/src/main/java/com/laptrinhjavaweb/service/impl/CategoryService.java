package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.laptrinhjavaweb.dao.ICategoryDAO;
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.paging.Pageble;
import com.laptrinhjavaweb.service.ICategoryService;

@Service
public class CategoryService implements ICategoryService{
	
	@Autowired
	private ICategoryDAO CategoryDao;
	
	@Override
	public List<CategoryModel> findAll() {
		return CategoryDao.findAll();
	}

	@Override
	public List<CategoryModel> findAllAndSort(Pageble pageble) {
		return CategoryDao.findAllAndSort(pageble);
	}

	@Override
	public int getTotalItem() {
		return CategoryDao.getTotalItem();
	}

	@Override
	public CategoryModel findOne(long id) {
		return CategoryDao.findOne(id);
	}

	@Override
	public CategoryModel findOneByCode(String code) {
		return CategoryDao.findOneByCode(code);
	}

	@Override
	public CategoryModel save(CategoryModel categoryModel) {
		categoryModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		Long newId = CategoryDao.save(categoryModel);
		return CategoryDao.findOne(newId);
	}

	@Override
	public CategoryModel update(CategoryModel updateCategory) {
		CategoryModel olNew = CategoryDao.findOne(updateCategory.getId());
		updateCategory.setCreatedDate(olNew.getCreatedDate());
		updateCategory.setCreatedBy(olNew.getCreatedBy());
		updateCategory.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		CategoryDao.update(updateCategory);
		return CategoryDao.findOne(updateCategory.getId());
	}

	@Override
	public void delete(Long[] ids) {
		for(Long id: ids) {
			//xoa bai viet truoc roi moi xoa duoc the loai(category_id)
			CategoryDao.delete(id);
		}
		
	}
	
}

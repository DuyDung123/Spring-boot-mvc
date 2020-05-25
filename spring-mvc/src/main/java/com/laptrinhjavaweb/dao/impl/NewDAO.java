package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import com.laptrinhjavaweb.dao.INewDAO;
import com.laptrinhjavaweb.mapper.NewMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.Pageble;

public class NewDAO extends AbstractDAO<NewModel> implements INewDAO {

	@Override
	public List<NewModel> findByCategoryId(Long categoryId) {
		
		String sql="select * from news where categoryid = ?";
		return query(sql, new NewMapper(), categoryId);
	}

	@Override
	public Long save(NewModel newModel) {
		StringBuilder sql = new StringBuilder("insert into news(title, thumbnail, shortdescription, content,");
		sql.append(" createddate, createdby, categoryid) values(?, ?, ?, ?, ?, ?, ?)");
		return insert(sql.toString(), newModel.getTitle(),newModel.getThumbnail(), newModel.getShortDescription(),
				newModel.getContent(), newModel.getCreatedDate(), newModel.getCreatedBy(), newModel.getCategoryId());
	}

	@Override
	public NewModel findOne(long id) {
		String sql="select * from news where id = ?";
		List<NewModel> news = query(sql, new NewMapper(), id);
		return news.isEmpty() ? null : news.get(0);
	}

	@Override
	public void update(NewModel updateNew) {
		StringBuilder sql = new StringBuilder("update news set title = ?, thumbnail = ?,") ;	
		sql.append(" shortdescription = ?, content = ?, categoryid = ?,");
		sql.append(" createddate = ?, modifieddate = ?, createdby = ?, modifiedby = ? where id = ?");
		update(sql.toString(), updateNew.getTitle(), updateNew.getThumbnail(), updateNew.getShortDescription(),
				updateNew.getContent(), updateNew.getCategoryId(), updateNew.getCreatedDate(), updateNew.getModifiedDate(),
				updateNew.getCreatedBy(), updateNew.getModifiedBy(), updateNew.getId());
	}

	@Override
	public void delete(long id) {
		String sql = "delete from news where id= ?";
		update(sql, id);
	}

	@Override
	public List<NewModel> finldAll(Pageble pageble) {
		StringBuilder sql = new StringBuilder("select * from news");
		if(pageble.getSorter() != null) {
			sql.append(" order by "+pageble.getSorter().getSortName()+" "+pageble.getSorter().getSortBy());
		}
		if(pageble.getOffset() !=null && pageble.getLimit() != null) {
			sql.append(" LIMIT "+pageble.getOffset()+","+pageble.getLimit());
		}
		return query(sql.toString(), new NewMapper());
	}

	@Override
	public int getTotalItem() {
		String sql ="select count(*) from news";
		return count(sql);
	}

		
}

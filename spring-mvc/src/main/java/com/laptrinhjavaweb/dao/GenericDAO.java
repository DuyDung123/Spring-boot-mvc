package com.laptrinhjavaweb.dao;

import java.util.List;

import com.laptrinhjavaweb.mapper.RowMapper;

public interface GenericDAO<T> {
	<T>List<T> query(String sql, RowMapper<T> rowMapper, Object...parameters);
	void update (String sql , Object...parameters);
	Long insert (String sql , Object...parameters);
	int count (String sql , Object...parameters);
}

//Object...parameters chuyền bao nhiêu tham số vào cũng được nó gọi là munti param
//query() hàm này để chạy seclect query
//update() ham nay chay sua va xoa
//insert()ham nay chay them(insert) co tra ve id bai viet

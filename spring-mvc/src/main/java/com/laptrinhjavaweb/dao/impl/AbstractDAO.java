package com.laptrinhjavaweb.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.laptrinhjavaweb.dao.GenericDAO;
import com.laptrinhjavaweb.mapper.RowMapper;

public class AbstractDAO<T> implements GenericDAO<T>{
	
//	ResourceBundle resourceBundle = ResourceBundle.getBundle("db");
	
	public Connection getConnection() {
		try {
			
//			Class.forName(resourceBundle.getString("driverName"));
//			String url = resourceBundle.getString("url");
//			String user = resourceBundle.getString("user");
//			String passWord = resourceBundle.getString("password");
//			return DriverManager.getConnection(url, user, passWord);
			
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/newservletjdbc";
			String user = "root";
			String passWord = "123456";
			return DriverManager.getConnection(url, user, passWord);
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}

	@Override
	public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
		
		List<T> resutls = new ArrayList<>();
		
		Connection connection = null;
		PreparedStatement statement =null;
		ResultSet resultSet = null;
		try {
			connection = getConnection();
			statement = connection.prepareStatement(sql); 
			setParameter(statement, parameters); 
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				resutls.add(rowMapper.mapRow(resultSet));
			}
			return resutls;
		} catch (SQLException e) {
			return null;
		}finally { 
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return null;
			}
		}
	}

	private void setParameter(PreparedStatement statement, Object... parameters) {
		try {
			for (int i = 0; i < parameters.length; i++) {
				Object parameter = parameters[i];
				int index = i + 1;
				if (parameter instanceof Long) {
					statement.setLong(index, (Long) parameter);
				} else if(parameter instanceof String) {
					statement.setString(index, (String) parameter);
				} else if(parameter instanceof Integer) {
					statement.setInt(index, (Integer) parameter);
				} else if(parameter instanceof Timestamp) {
					statement.setTimestamp(index, (Timestamp) parameter);
				}
			}
		} catch (SQLException e) {
			e.fillInStackTrace();
		}
	}
	
	@Override
	public void update(String sql, Object... parameters) {
		Connection connection = null;
		PreparedStatement statement = null;
		try {
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql);
			setParameter(statement, parameters);
			statement.executeUpdate();
			connection.commit();
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e2) {
				e2.fillInStackTrace();
			}
		}
	}

	@Override
	public Long insert(String sql, Object... parameters) {
		Connection connection = null;
		ResultSet resultSet = null;
		PreparedStatement statement = null;
		try {
			Long id = null;
			connection = getConnection();
			connection.setAutoCommit(false);
			statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			setParameter(statement, parameters);
			statement.executeUpdate();
			resultSet = statement.getGeneratedKeys(); // no sáº½ láº¥y ra Ä‘Æ°á»£c id vÃ¬ id mÃ¬nh Ä‘á»ƒ nÃ³ tá»± tÄƒng mÃ  dÃ¹ng cÃ¡i nÃ y pháº£i cÃ³ Statement.RETURN_GENERATED_KEYS
			if(resultSet.next()) {
				id = resultSet.getLong(1);
			}
			connection.commit();
			return id;
		} catch (SQLException e) {
			if(connection != null) {
				try {
					connection.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		}finally {
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e2) {
				e2.fillInStackTrace();
			}
		}
		return null;
	}

	@Override
	public int count(String sql, Object... parameters) {
		
		Connection connection = null;
		PreparedStatement statement =null;
		ResultSet resultSet = null;
		try {
			int count = 0;
			connection = getConnection();
			statement = connection.prepareStatement(sql); //chá»— nÃ y má»›i chuyá»�n cÃ¢u query vÃ o thÃ´i
			setParameter(statement, parameters); // chá»— nÃ y chuyá»�n tham sá»‘ vÃ o cho cÃ¢u query
			resultSet = statement.executeQuery(); //chá»— nÃ y nÃ³ má»›i cháº¡y query nÃ¨ sau Ä‘Ã³ nÃ³ cháº¡y tráº£ vá»� cho ta má»™t báº£ng dÃ¹ng loop Ä‘á»ƒ láº¥y dl bang Ä‘Ã³
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			return count;
		} catch (SQLException e) {
			return 0;
		}finally { //dÃ¹ try cÃ³ Ä‘ang chay hay tháº¿ nÃ o Ä‘i ná»¯a thÃ¬ cháº¡y song rá»“i cÃ¡i finally nÃ y má»›i Ä‘Æ°á»£c nháº£y vÃ o cuá»‘i cÃ¹ng
			try {
				if (connection != null) {
					connection.close();
				}
				if (statement != null) {
					statement.close();
				}
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				return 0;
			}
		}
	}
}

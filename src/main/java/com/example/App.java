package com.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
  
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.domain.Customer;
 

 
@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner
{
	@Autowired
	NamedParameterJdbcTemplate jdbcTemplate; // di 컨테이너에 등록된 객체를 얻어 옵니다. 
	
	@Override
	public void run(String... strings) throws Exception {

//		String sql="SELECT :a + :b";
		String sql ="SELECT id, first_name, last_name FROM customers WHERE id =:id";
		
		
		SqlParameterSource param = new MapSqlParameterSource()
			.addValue("id", 2);
		
		//java 1.8 이전 익명 클래스로 
//		Customer result= jdbcTemplate.queryForObject(sql, param, 
//						new RowMapper<Customer>(){ 
//							@Override
//							public Customer mapRow(ResultSet rs, int rowNum)
//									throws SQLException {
// 								
//								return new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name"));
//							} 
//						}
//				);
		//java 1.8 람다로 .. 
		Customer result= jdbcTemplate.queryForObject(sql, param,
				(rs, rowNum) -> new Customer(rs.getInt("id"), rs.getString("first_name"), rs.getString("last_name")));
		
		System.out.println("result = " + result);
		
		
	}
	
	
	public static void main( String[] args )
	{
		SpringApplication.run(App.class, args);
	}
}
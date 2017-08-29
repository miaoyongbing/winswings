package com.dcone.dtss.dao;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.chat;

public class ChatroomDAO {

	/**
	 * 获取利奥塔记录
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<chat> getrecords( JdbcTemplate jdbcTemplate) {
		RowMapper<chat> chat_mapper=new BeanPropertyRowMapper<chat>(chat.class);
		List<chat> chats= jdbcTemplate.query("select username,dc_user.itcode,records as record,chattime as time,portrait from chatting_records,dc_user where dc_user.itcode=chatting_records.itcode order by id desc limit ?",chat_mapper,new Object[] {8});
		return chats;

	}
	
	public static int insert(String itcode,String records,JdbcTemplate jdbcTemplate) throws UnsupportedEncodingException {
			Date date = new Date();
			String url1 = " yyyy-MM-dd";
			String url2 = " HH:mm:ss";
			SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
			SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
			String chattime = fmtDate1.format(date) + fmtDate2.format(date);
			int i = jdbcTemplate.update("insert into chatting_records(itcode, records, chattime) values(?,?,?)",new Object[] { itcode,records,chattime});
			return i;
	}
			
}

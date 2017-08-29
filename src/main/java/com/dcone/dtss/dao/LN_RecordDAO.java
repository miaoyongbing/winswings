package com.dcone.dtss.dao;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;
/**
 * 
 * @author wrs
 *处理红包记录操作
 */
public class LN_RecordDAO {
	/**
	 * 创建发红包记录
	 * @param round 红包雨轮次
	 * @param wallet 用户钱包
	 * @param number 红包数额
	 * @param jdbcTemplate
	 * @param time  交易时间
	 * @return 1成功,0失败
	 */
	public static int newRecord(int round ,dc_wallet wallet,int number,String time,JdbcTemplate jdbcTemplate) {
		try {
			int i =jdbcTemplate.update("insert into luckynumberrecord(round,wid,lucky_number,tradetime) values(?,?,?,?);",new Object[] {round,wallet.getWid(),number,time});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("添加红包支付记录失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 添加提现记录
	 * @param round 红包雨轮次
	 * @param number 提现数额
	 * @param time 时间
	 * @param jdbcTemplate
	 * @return true成功，false失败
	 */
	public static boolean newRecord(int round,int number,String time,JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.update(
					"insert into luckynumberrecord(round,wid,lucky_number,tradetime) values(?,?,?,?);",
					new Object[] {round,0,number,time});
			if(i>0)
				return true;
		}catch(Exception e) {
			System.out.println("添加提现记录失败！");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 获取全部红包记录
	 * @param jdbcTemplate
	 * @return 全部红包记录
	 */
	public static List<ln_record> getAllRecords(JdbcTemplate jdbcTemplate) {
		RowMapper<ln_record> lnrmapper= new BeanPropertyRowMapper<ln_record>(ln_record.class);
		try {
			List<ln_record> wanted=jdbcTemplate.query("select * from luckynumberrecord;", lnrmapper);
			return wanted;
		}catch(Exception e) {
			System.out.println("获取红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定轮次的所有红包雨记录
	 * @param round 红包雨轮次
	 * @param jdbcTemplate
	 * @return 红包雨记录
	 */
	public static List<ln_record> getRecordsByround(int round,JdbcTemplate jdbcTemplate){
		RowMapper<ln_record> lnrmapper= new BeanPropertyRowMapper<ln_record>(ln_record.class);
		try {
			List<ln_record> wanted=jdbcTemplate.query("select * from luckynumberrecord where round=?;",lnrmapper,new Object[] {round});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
}

package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;
/**
 * 
 * @author wrs
 *处理红包账户记录操作
 */
public class LuckRecordDAO {
	/**
	 * 获取指定红包记录
	 * @param lid 红包账户id
	 * @param wid 用户钱包id
	 * @param jdbcTemplate
	 * @return 指定红包记录
	 */
	public static luckrecord getRecordByLidWid(int lid,int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<luckrecord> lrmapper=new BeanPropertyRowMapper<luckrecord>(luckrecord.class);
		try {
			int i=jdbcTemplate.queryForInt("select count(*) from luckrecord where lid=? and wid =?",new Object[] {lid,wid});
			if(i>0)
			{
				luckrecord wanted=jdbcTemplate.queryForObject("select * from luckrecord where lid=? and wid=?;",lrmapper,new Object[] {lid,wid});
				if(wanted!=null)
					return wanted;
			}
		}catch(Exception e) {
			dc_wallet temp=WalletDAO.getWalletByWid(wid, jdbcTemplate);
			dc_user w=UserDAO.getUserByUid(temp.getWid(), jdbcTemplate);
			System.out.println("获取用户"+w.getUsername()+"的抢红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取用户全部红包记录
	 * @param wid 用户钱包id
	 * @param jdbcTemplate
	 * @return 用户全部红包记录
	 */
	public static List<luckrecord> getRecordsByWid(int wid,JdbcTemplate jdbcTemplate){
		RowMapper<luckrecord> lrmapper=new BeanPropertyRowMapper<luckrecord>(luckrecord.class);
		try {
			List<luckrecord> wanted=jdbcTemplate.query("select * from luckrecord where wid=?;",lrmapper,new Object[] {wid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取抢红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取红包账户全部发放记录
	 * @param lid 红包账户id
	 * @param jdbcTemplate
	 * @return 发放记录
	 */
	public static List<luckrecord> getRecordsByLid(int lid,JdbcTemplate jdbcTemplate){
		RowMapper<luckrecord> lrmapper=new BeanPropertyRowMapper<luckrecord>(luckrecord.class);
		try {
			List<luckrecord> wanted=jdbcTemplate.query("select * from luckrecord where lid=?;",lrmapper,new Object[] {lid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取抢红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取红包账户全部发放记录
	 * @param tip 红包账户备注
	 * @param jdbcTemplate
	 * @return 发放记录
	 */
	public static List<luckrecord> getRecordsByTip(String tip,JdbcTemplate jdbcTemplate){
		RowMapper<luckrecord> lrmapper=new BeanPropertyRowMapper<luckrecord>(luckrecord.class);
		try {
			List<luckrecord> wanted=jdbcTemplate.query("select * from luckrecord where tip=?;",lrmapper,new Object[] {tip});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取抢红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取全部红包记录
	 * @param jdbcTemplate
	 * @return 全部红包记录
	 */
	public static List<luckrecord> getAllRecords(JdbcTemplate jdbcTemplate){
		RowMapper<luckrecord> lrmapper=new BeanPropertyRowMapper<luckrecord>(luckrecord.class);
		try {
			List<luckrecord> wanted=jdbcTemplate.query("select * from luckrecord;",lrmapper);
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取抢红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加红包发放记录
	 * @param wid 用户钱包id
	 * @param lid 红包账户id
	 * @param volume 红包数额
	 * @param time 时间
	 * @param tip 备注
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int addRecord(int wid,int lid,int volume,String time,String tip,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("insert into luckrecord(wid,lid,volume,tradetime,tip) values(?,?,?,?,?);",new Object[] {wid,lid,volume,time,tip});
			if(i>0)
				return i;
		}catch(Exception e) {
			System.out.println("添加发红包记录失败！");
			e.printStackTrace();
		}
		return 0;
	}
}

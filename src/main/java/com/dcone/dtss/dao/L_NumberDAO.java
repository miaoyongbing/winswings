package com.dcone.dtss.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.l_number;
/**
 * 
 * @author wrs
 *处理红包账户操作
 */
public class L_NumberDAO {
	/**
	 * 获取红包账户余额
	 * @param i 红包雨轮次
	 * @param jdbcTemplate
	 * @return 红包账户余额或者0
	 */
	public static int getTotalbyRound(int i,JdbcTemplate jdbcTemplate) {
		try {
			int w=jdbcTemplate.queryForInt("select total from luckynumber where round=?;",new Object[] {i});
			return w;
		}catch(Exception e) {
			System.out.println("获取红包账户余额失败哒！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 红包总额计减
	 * @param round 轮次
	 * @param number 红包数额
	 * @return 1 成功, 0失败
	 */
	public static int luckyRain(int round, int number,JdbcTemplate jdbcTemplate) {
		try {
			if(getTotalbyRound(round,jdbcTemplate)>=number) {
				int i=jdbcTemplate.update("update luckynumber set total=total-? where round=?;",new Object[] {number,round});
				System.out.println(number);
				if(i>0)
					return 1;
			}
		}catch(Exception e) {
			System.out.println("减少红包账户总余额失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 红包雨账户提现
	 * @param round 红包雨轮次
	 * @param number 提现金额
	 * @param jdbcTemplate
	 * @return true成功，false失败
	 */
	public static boolean getMoney(int round,int number,JdbcTemplate jdbcTemplate) {
		try {
			int i=luckyRain(round,number,jdbcTemplate);
			if(i>0) {
				Date date = new Date();
				String url1 = " yyyy-MM-dd";
				String url2 = " HH:mm:ss";
				SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
				SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
				String time = fmtDate1.format(date) + fmtDate2.format(date);
				if(LN_RecordDAO.newRecord(round, number, time, jdbcTemplate))
					return true;
			}
		}catch(Exception e) {
			System.out.println("提现失败");
			e.printStackTrace();
		}
		return false;
	}
	/**
	 * 查询所有红包雨账户
	 * @param jdbcTemplate
	 * @return 所有红包雨账户
	 */
	public static List<l_number> getAllLN(JdbcTemplate jdbcTemplate){
		RowMapper<l_number> mapper=new BeanPropertyRowMapper<l_number>(l_number.class);
		try {
			List<l_number> wanted=jdbcTemplate.query("select * from luckynumber;", mapper);
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定轮次红包雨账户
	 * @param round 轮次
	 * @param jdbcTemplate
	 * @return 红包雨账户
	 */
	public static l_number getLNByRound(int round,JdbcTemplate jdbcTemplate){
		RowMapper<l_number> mapper=new BeanPropertyRowMapper<l_number>(l_number.class);
		try {
			l_number wanted=jdbcTemplate.queryForObject("select * from luckynumber where round=?;", mapper,new Object[] {round});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("查询失败");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 红包雨账户充值
	 * @param round 红包雨账户轮次
	 * @param money 金额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int addmoney(int round,int money,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("update luckynumber set total=total+? where round=?",new Object[] {money,round});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("充值失败");
			e.printStackTrace();
		}
		return 0;
	}
}

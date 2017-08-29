package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;
/**
 * 
 * @author wrs
 *用于处理打赏操作
 */
public class P_TradeDAO {
	/**
	 * 获取全部打赏记录
	 * @param jdbcTemplate
	 * @return 全部打赏记录
	 */
	public static List<p_trade> getAllTrades(JdbcTemplate jdbcTemplate){
		RowMapper<p_trade> ptmapper=new BeanPropertyRowMapper<p_trade>(p_trade.class);
		try {
			List<p_trade> wanted=jdbcTemplate.query("select * from p_trade;", ptmapper);
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取全部节目打赏记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定交易记录
	 * @param pid 节目账户id
	 * @param wid 用户钱包id
	 * @param time 交易时间
	 * @param jdbcTemplate
	 * @return 指定交易记录
	 */
	public static p_trade getTrade(int pid,int wid,String time,JdbcTemplate jdbcTemplate) {
		RowMapper<p_trade> ptmapper=new BeanPropertyRowMapper<p_trade>(p_trade.class);
		try {
			p_trade wanted=jdbcTemplate.queryForObject("select * from p_trade where pid=? and wid=? and tradetime=?;", ptmapper,new Object[] {pid,wid,time});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取指定节目打赏记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定交易记录
	 * @param tid 交易记录流水号
	 * @param jdbcTemplate
	 * @return 指定交易记录
	 */
	public static p_trade getTradeByTid(int tid,JdbcTemplate jdbcTemplate) {
		RowMapper<p_trade> ptmapper=new BeanPropertyRowMapper<p_trade>(p_trade.class);
		try {
			p_trade wanted=jdbcTemplate.queryForObject("select * from p_trade where tid=?;", ptmapper,new Object[] {tid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据节目打赏记录id获取节目打赏记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定节目的打赏记录
	 * @param pid 节目id
	 * @param jdbcTemplate
	 * @return 指定节目打赏记录
	 */
	public static List<p_trade> getTradesByPid(int pid,JdbcTemplate jdbcTemplate) {
		RowMapper<p_trade> ptmapper=new BeanPropertyRowMapper<p_trade>(p_trade.class);
		try {
			List<p_trade> wanted=jdbcTemplate.query("select * from p_trade where pid=?;", ptmapper,new Object[] {pid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据节目账户id获取节目打赏记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取指定用户全部打赏记录
	 * @param wid 用户钱包id
	 * @param jdbcTemplate
	 * @return 指定用户全部打赏记录
	 */
	public static List<p_trade> getTradesByWid(int wid,JdbcTemplate jdbcTemplate) {
		RowMapper<p_trade> ptmapper=new BeanPropertyRowMapper<p_trade>(p_trade.class);
		try {
			List<p_trade> wanted=jdbcTemplate.query("select * from p_trade where wid=?;", ptmapper,new Object[] {wid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据用户钱包id获取节目打赏记录失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 创建打赏记录
	 * @param pid 节目账户id
	 * @param wid 用户钱包id
	 * @param volume 打赏金额
	 * @param time 交易时间
	 * @param memo 备注
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int createTrade(int pid,int wid,int volume,String time,String memo,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("insert into p_trade(pid,wid,volume,tradetime,tip) values(?,?,?,?,?);",new Object[] {pid,wid,volume,time,memo});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("创建新的节目打赏记录失败!");
			e.printStackTrace();
		}
		return 0;
	}
}

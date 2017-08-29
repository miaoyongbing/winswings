package com.dcone.dtss.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;
/**
 * 
 * @author wrs
 *处理节目账户操作
 */
public class P_PocketDAO {
	/**
	 * 通过id获取节目账户
	 * @param pid 节目id
	 * @param jdbcTemplate
	 * @return 节目账户
	 */
	public static p_pocket getPocketById(int pid,JdbcTemplate jdbcTemplate) {
		RowMapper<p_pocket> ppmapper=new BeanPropertyRowMapper<p_pocket>(p_pocket.class);
		try {
			p_pocket wanted=jdbcTemplate.queryForObject("select * from p_pocket where pid=?", ppmapper,new Object[] {pid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据id获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过节目名获取节目账户
	 * @param name 节目名
	 * @param jdbcTemplate
	 * @return 节目账户
	 */
	public static p_pocket getPocketByName(String name,JdbcTemplate jdbcTemplate) {
		RowMapper<p_pocket> ppmapper=new BeanPropertyRowMapper<p_pocket>(p_pocket.class);
		try {
			p_pocket wanted=jdbcTemplate.queryForObject("select * from p_pocket where p_name=?", ppmapper,new Object[] {name});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据节目名获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 检查表演者表演节目数
	 * @param name 表演者名
	 * @param jdbcTemplate
	 * @return 节目数
	 */
	public static int checkActor(String name,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.queryForInt("select count(*) from p_pocket where actor=?",new Object[] {name});
			return i;
		}catch(Exception e) {
			System.out.println("获取表演者参演的节目数失败!");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 通过演员名获取节目账户
	 * @param name 演员名
	 * @param jdbcTemplate
	 * @return 节目账户
	 */
	public static p_pocket getPocketByActor(String name,JdbcTemplate jdbcTemplate) {
		RowMapper<p_pocket> ppmapper=new BeanPropertyRowMapper<p_pocket>(p_pocket.class);
		try {
			p_pocket wanted=jdbcTemplate.queryForObject("select * from p_pocket where actor=?", ppmapper,new Object[] {name});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据演员名获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过演员名获取多个节目账户
	 * @param name 演员名
	 * @param jdbcTemplate
	 * @return 多个节目账户
	 */
	public static List<p_pocket> getPocketsByActor(String name,JdbcTemplate jdbcTemplate){
		RowMapper<p_pocket> ppmapper=new BeanPropertyRowMapper<p_pocket>(p_pocket.class);
		try {
			List<p_pocket> wanted=jdbcTemplate.query("select * from p_pocket where actor=?", ppmapper,new Object[] {name});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据演员名获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取全部节目账户
	 * @param jdbcTemplate
	 * @return 全部节目账户
	 */
	public static List<p_pocket> getAllPockets(JdbcTemplate jdbcTemplate){
		RowMapper<p_pocket> ppmapper=new BeanPropertyRowMapper<p_pocket>(p_pocket.class);
		try {
			List<p_pocket> wanted=jdbcTemplate.query("select * from p_pocket;", ppmapper);
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取全部节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 通过节目id设置节目账户余额
	 * @param pid 节目id
	 * @param amount 新余额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int setPocketByPid(int pid,int amount,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("update p_pocket set amount=? where pid=?",new Object[] {amount,pid});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("根据id设置节目账户余额失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 通过节目名设置节目账户余额
	 * @param p_name 节目名
	 * @param amount 新余额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int setPocketByName(String p_name,int amount,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("update p_pocket set amount=? where p_name=?",new Object[] {amount,p_name});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("根据节目名设置节目账户余额失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 添加新的节目账户
	 * @param p_name 节目名
	 * @param volume 余额
	 * @param actor 演员名
	 * @param tip 备注
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int addPocket(String p_name,int volume,String actor,String tip,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("insert into p_pocket(p_name,actor,amount,tip) values(?,?,?,?);",new Object[] {p_name,actor,volume,tip});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("插入节目账户失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取用户打赏
	 * @param name 节目名
	 * @param volume 打赏数额
	 * @param wid 用户钱包id
	 * @param time 时间
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int getMoney(String name,int volume,int wid,String time,JdbcTemplate jdbcTemplate) {
		dc_wallet w=WalletDAO.getWalletByWid(wid, jdbcTemplate);
		dc_user q=UserDAO.getUserByUid(w.getUid(), jdbcTemplate);
		p_pocket temp=getPocketByName(name,jdbcTemplate);
		if(TradeDAO.preTrade(wid, volume, jdbcTemplate)) {
			int i=WalletDAO.walletMinusByWid(wid, volume, jdbcTemplate);
			boolean j=TradeDAO.createTrade(time, wid, volume, "打赏："+name+"；节目id："+temp.getPid(), jdbcTemplate);
			int s=P_PocketDAO.setPocketByName(name, volume+temp.getAmount(), jdbcTemplate);
			int t=P_TradeDAO.createTrade(temp.getPid(), wid, volume, time, "来自"+q.getUsername()+"的打赏", jdbcTemplate);
			if(i*s*t>0&&j)
				return 1;
		}
		return 0;
	}
	/**
	 * 从节目账户中提现
	 * @param name 节目名
	 * @param volume 提现数额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int takeMoney(String name,int volume,JdbcTemplate jdbcTemplate) {
		p_pocket temp=getPocketByName(name,jdbcTemplate);
		int i;
		if(getTotal(temp.getPid(),jdbcTemplate)>volume) {
			i=setPocketByName(name,temp.getAmount()-volume,jdbcTemplate);
			if (i > 0) {
				Date date = new Date();
				String url1 = " yyyy-MM-dd";
				String url2 = " HH:mm:ss";
				SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
				SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
				String time = fmtDate1.format(date) + fmtDate2.format(date);
				i=P_TradeDAO.createTrade(temp.getPid(), 0, volume, time, "提现", jdbcTemplate);
				if(i>0)
					return 1;
			}
		}
		
		return 0;
	}
	/**
	 * 获取账户余额
	 * @param pid 账户id
	 * @param jdbcTemplate
	 * @return 账户余额
	 */
	public static int getTotal(int pid,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.queryForInt("select amount from p_pocket where pid=?",new Object[] {pid});
			if(i>0)
				return i;
		}catch(Exception e) {
			System.out.println("获取节目账户余额失败!");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取节目排名
	 * @param pid 节目id
	 * @param jdbcTemplate
	 * @return 节目排名
	 * 
	 */
	public static int getRank(int pid,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.queryForInt("select rank from programrank where pid=?;",new Object[] {pid});
			if(i>0)
				return i;
		}catch(Exception e) {
			System.out.println("获取排名失败");
			e.printStackTrace();
		}
		return 0;
	}
}

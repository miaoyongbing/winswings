package com.dcone.dtss.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;

/**
 * 
 * @author wrs 处理红包账户相关操作
 */
public class LuckDAO {
	/**
	 * 抢红包操作
	 * 
	 * @param lid
	 *            红包账户id
	 * @param wallet
	 *            用户钱包
	 * @param jdbcTemplate
	 * @return 1成功，0失败，-2没有钱了
	 */
	public static int getLuckMoney(int lid, dc_wallet wallet, JdbcTemplate jdbcTemplate) {
		/*RowMapper<Integer> integ = new BeanPropertyRowMapper<Integer>(Integer.class);
		List<Integer> lids=jdbcTemplate.query(" select lid from luck;",integ);
		for (Integer i:lids) {
			lid=i;
			System.out.println("此用户没有抢过红包！"+i);
		int j=0;
		}*/
		if (LuckRecordDAO.getRecordByLidWid(lid, wallet.getWid(), jdbcTemplate) == null) {
			System.out.println("此用户没有抢过红包！");
		
			Random r = new Random();
			int temp = r.nextInt(5000);
			if (temp > getTotal(lid, jdbcTemplate))
				temp = getTotal(lid, jdbcTemplate);
			if (temp == 0)
				return 2;
			else {
				int z = sendLuck(lid, wallet, temp, jdbcTemplate);
				System.out.println("z=" + z);
				if (z == 1)
					return 1;
			}
		}
	
		
		return 0;
	}

	/**
	 * 获取红包账户
	 * 
	 * @param lid
	 *            红包账户id
	 * @param jdbcTemplate
	 * @return 红包账户
	 */
	public static luck getLuckByLid(int lid, JdbcTemplate jdbcTemplate) {
		RowMapper<luck> luckmapper = new BeanPropertyRowMapper<luck>(luck.class);
		try {
			luck wanted = jdbcTemplate.queryForObject("select * from luck where lid=?", luckmapper,
					new Object[] { lid });
			return wanted;
		} catch (Exception e) {
			System.out.println("获取红包账户失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取红包账户
	 * 
	 * @param tip
	 *            红包账户备注
	 * @param jdbcTemplate
	 * @return 红包账户
	 */
	public static luck getLuckByTip(String tip, JdbcTemplate jdbcTemplate) {
		RowMapper<luck> luckmapper = new BeanPropertyRowMapper<luck>(luck.class);
		try {
			luck wanted = jdbcTemplate.queryForObject("select * from luck where tip=?", luckmapper,
					new Object[] { tip });
			return wanted;
		} catch (Exception e) {
			System.out.println("获取红包账户失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 向用户发送红包
	 * 
	 * @param lid
	 *            红包账户id
	 * @param wallet
	 *            用户钱包
	 * @param volume
	 *            红包额度
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int sendLuck(int lid, dc_wallet wallet, int volume, JdbcTemplate jdbcTemplate) {
		try {
			Date date = new Date();
			String url1 = " yyyy-MM-dd";
			String url2 = " HH:mm:ss";
			SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
			SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
			String time = fmtDate1.format(date) + fmtDate2.format(date);
			int i = jdbcTemplate.update("update luck set amount=amount-? where lid=?", new Object[] { volume, lid });
			int j = LuckRecordDAO.addRecord(wallet.getWid(), lid, volume, time, "抢红包,lid=" + lid, jdbcTemplate);
			int t = WalletDAO.walletAddByWid(wallet.getWid(), volume, jdbcTemplate);
			int s = TradeDAO.createTrade(wallet.getWid(), time, volume,
					"抢" + getLuckByLid(lid, jdbcTemplate).getTip(), jdbcTemplate);
			System.out.println("i=" + i + ",j=" + j + ",t=" + t + ",s=" + s);
			if (i * j * t * s > 0) {
				System.out.println("抢红包成功！");
				
				return 1;
			}
		} catch (Exception e) {
			System.out.println("发送红包失败!");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 设置红包账户余额
	 * 
	 * @param lid
	 *            红包账户id
	 * @param amount
	 *            余额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int setLuckByLid(int lid, int amount, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.update("update luck set amount=? where lid=?", new Object[] { amount, lid });
			if (i > 0)
				return 1;
		} catch (Exception e) {
			System.out.println("设置红包账户余额失败！");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 设置红包账户余额
	 * 
	 * @param tip
	 *            红包账户备注
	 * @param amount
	 *            余额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int setLuckByTip(String tip, int amount, JdbcTemplate jdbcTemplate) {
		luck l = getLuckByTip(tip, jdbcTemplate);
		return setLuckByLid(l.getLid(), amount, jdbcTemplate);
	}

	/**
	 * 添加红包账户
	 * 
	 * @param tip
	 *            备注
	 * @param amount
	 *            余额
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int addLuck(String tip, int amount, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.update("insert into luck(amount,tip) values(?,?);", new Object[] { amount, tip });
			if (i > 0)
				return 1;
		} catch (Exception e) {
			System.out.println("红包账户充值失败！");
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取全部红包账户
	 * 
	 * @param jdbcTemplate
	 * @return 全部红包账户
	 */
	public static List<luck> getAllLuck(JdbcTemplate jdbcTemplate) {
		RowMapper<luck> luckmapper = new BeanPropertyRowMapper<luck>(luck.class);
		try {
			List<luck> wanted = jdbcTemplate.query("select * from luck", luckmapper);
			if (wanted != null)
				return wanted;
		} catch (Exception e) {
			System.out.println("获取全部红包账户失败！");
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取红包账户余额 
	 * 
	 * @param lid红包账户id
	 * @param jdbcTemplate
	 * @return 红包账户余额或者0
	 */
	public static int getTotal(int lid, JdbcTemplate jdbcTemplate) {
		int i = 0;
		try {
			i = jdbcTemplate.queryForInt("select amount from luck where lid=?", new Object[] { lid });
			if (i > 0)
				return i;
		} catch (Exception e) {
			System.out.println("获取红包账户余额失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 红包账户提现交易
	 * @param lid 红包账户id
	 * @param amount 提现额度
	 * @param jdbcTemplate
	 * @return true提现成功，false提现失败
	 */
	public static boolean minusTotal(int lid,int amount,JdbcTemplate jdbcTemplate) {
		try {
			int total=getTotal(lid,jdbcTemplate);
			if(amount>total)
				System.out.println("提现钱数大于总余额");
			else {
				int i=setLuckByLid(lid, total-amount, jdbcTemplate);
				if (i > 0) {
					Date date = new Date();
					String url1 = " yyyy-MM-dd";
					String url2 = " HH:mm:ss";
					SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
					SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
					String time = fmtDate1.format(date) + fmtDate2.format(date);
					LuckRecordDAO.addRecord(0, lid, amount, time, "提现", jdbcTemplate);
					return true;
				}
			}
		}catch(Exception e) {
			System.out.println("红包账户提现失败！");
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 获取红包记录中额度大于某个值的红包记录
	 * @param money
	 * @param jdbcTemplate
	 * @return
	 */
	public static List<luckrecord> getLuckRecordsMax(int money, JdbcTemplate jdbcTemplate) {
		RowMapper<luckrecord> luckmapper = new BeanPropertyRowMapper<luckrecord>(luckrecord.class);
		try {
			//List<luckrecord> wanted = jdbcTemplate.query(" select * from luckrecord where volume>? and tradetime between now()-interval 600 second and now();", luckmapper,new Object[] { money });
			List<luckrecord> wanted = jdbcTemplate.query(" select * from luckrecord where volume>?;", luckmapper,new Object[] { money });
			if (wanted != null)
				return wanted;
		} catch (Exception e) {
			System.out.println("获取大于"+money+"元红包记录失败！");
			e.printStackTrace();
		}
		return null;
	}
}

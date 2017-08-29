package com.dcone.dtss.dao;

import java.sql.ResultSet;
import java.util.*;
import org.springframework.jdbc.core.*;

import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.chat;
import com.dcone.dtss.model.dc_trade;

/**
 * 
 * @author wrs 处理用户信息
 */
public class UserDAO {
	/**
	 * 获取用户
	 * 
	 * @param uid
	 *            用户id
	 * @param jdbcTemplate
	 * @return 用户
	 */
	public static dc_user getUserByUid(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			dc_user wanted = jdbcTemplate.queryForObject("select * from dc_user where uid=?;", user_mapper,
					new Object[] { uid });
			return wanted;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uid错误,找不到用户!");
		}
		return null;
	}

	/**
	 * 获取用户
	 * 
	 * @param itcode
	 *            用户员工号
	 * @param jdbcTemplate
	 * @return 用户
	 */
	public static dc_user getUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			int i =jdbcTemplate.queryForObject("select count(*) from dc_user where itcode=?;", Integer.class,
					new Object[] { itcode });
			if(i==1) {
			dc_user wanted = jdbcTemplate.queryForObject("select * from dc_user where itcode=?;", user_mapper,
					new Object[] { itcode });
			return wanted;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("itcode错误,找不到用户!");
		}
		return null;
	}

	/**
	 * 获取用户
	 * 
	 * @param username
	 *            用户名
	 * @param jdbcTemplate
	 * @return 用户
	 */
	public static  List<dc_user> getUserByName(String username, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			List<dc_user> wanted = jdbcTemplate.query("select * from dc_user where username=?;", user_mapper,
					new Object[] { username });
			if(wanted!=null)
			return wanted;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("用户名错误,找不到用户!");
		}
		return null;
	}

	/**
	 * 在充值前测试用户员工号与用户名是否存在且匹配
	 * 
	 * @param itcode
	 *            员工号
	 * @param username
	 *            用户名
	 * @param jdbcTemplate
	 * @return true信息正确，false信息错误
	 */
	public static boolean checkItcodeUsername(String itcode, String username, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? or username=?;",
					new Object[] { itcode, username });
			if (i == 1)
				return true;
			else {
				System.out.println("用户名或itcode重复！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("用户名和itcode不匹配！");
		}
		return false;
	}

	/**
	 * 创建用户
	 * 
	 * @param itcode
	 *            员工号
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param jdbcTemplate
	 * @return 1成功，-2插入失败，-1员工号或用户名重复，0连接失败
	 */
	public static int createUser(String itcode, String username, String password, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			if (checkUserInfo(itcode, username, jdbcTemplate)) {
				System.out.println("注册急急急"+itcode+username+password);
				int i = jdbcTemplate.update("insert into dc_user (itcode,username,password,portrait) values(?,?,?,?);",
						new Object[] { itcode, username, password,"img/default.png" });
				if (i > 0) {
					return 1;
				} else {
					return -2;
				}
			} else {
				return -1;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("初始化失败！itcode或用户名重复！");
		}
		return 0;
	}

	/**
	 * 在注册时检查员工号和用户名是否重复
	 * @param itcode员工号
	 * @param username用户名
	 * @param jdbcTemplate
	 * @return true不重复，可以使用；false重复，不可使用
	 */
	public static boolean checkUserInfo(String itcode, String username, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? ;",
					new Object[] { itcode });
			if (i == 0)
				return true;
			else {
				System.out.println("用户名或itcode重复！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("用户名和itcode不匹配！");
		}
		return false;
	}

	/**
	 * 修改数据库,修改model,完成用户锁定
	 * 
	 * @param uid
	 *            用户id
	 * @return true锁定成功，false锁定失败
	 */
	public static boolean lockUserById(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			int i = jdbcTemplate.update("update dc_user set wlock = 1 where uid=?;", user_mapper, new Object[] { uid });
			if (i > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uid错误,找不到用户!");
		}
		return false;
	}

	/**
	 * 锁定用户
	 * 
	 * @param itcode
	 *            员工号
	 * @return true锁定成功，false锁定失败
	 */
	public static boolean lockUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			int i = jdbcTemplate.update("update dc_user set wlock = 1 where itcode=?;", user_mapper,
					new Object[] { itcode });
			if (i > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("itcode错误,找不到用户!");
		}
		return false;
	}

	/**
	 * 解锁用户
	 * 
	 * @param uid
	 *            用户id
	 * @param jdbcTemplate
	 * @return true解锁成功，false解锁失败
	 */
	public static boolean unlockUserByID(int uid, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			int i = jdbcTemplate.update("update dc_user set wlock = 0 where uid=?;", user_mapper, new Object[] { uid });
			if (i > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("uid错误,找不到用户!");
		}
		return false;
	}

	/**
	 * 解锁用户
	 * 
	 * @param itcode
	 *            员工号
	 * @param jdbcTemplate
	 * @return true解锁成功，false解锁失败
	 */
	public static boolean unlockUserByItcode(String itcode, JdbcTemplate jdbcTemplate) {
		RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
		try {
			int i = jdbcTemplate.update("update dc_user set wlock = 1 where itcode=?;", user_mapper,
					new Object[] { itcode });
			if (i > 0)
				return true;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("itcode错误,找不到用户!");
		}
		return false;
	}

	/**
	 * 检查用户是否被锁定
	 * 
	 * @param uid
	 *            用户id
	 * @param jdbcTemplate
	 * @return true锁定，false未被锁定
	 */
	public static boolean isLock(int uid, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.queryForInt("select wlock from dc_user where uid=?;", new Object[] { uid });
			if (i == 0)
				return true;
		} catch (Exception e) {
			System.out.println("查询用户状态失败！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 查询用户是否被锁定
	 * 
	 * @param itcode
	 *            员工号
	 * @param jdbcTemplate
	 * @return true锁定，false未被锁定
	 */
	public static boolean isLock(String itcode, JdbcTemplate jdbcTemplate) {
		dc_user user = getUserByItcode(itcode, jdbcTemplate);
		return isLock(user.getUid(), jdbcTemplate);
	}

	/**
	 * 修改密码
	 * 
	 * @param itcode
	 *            用户员工号
	 * @param old
	 *            旧的密码
	 * @param newp
	 *            新的密码
	 * @param jdbcTemplate
	 * @return true成功，false失败
	 */
	public static boolean changePassword(String itcode, String old, String newp, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? and password=?;",
					new Object[] { itcode, old });
			if (i > 0) {
				i = jdbcTemplate.update("update dc_user set password=? where itcode=?;", new Object[] { newp, itcode });
				if (i > 0)
					return true;
			}
		} catch (Exception e) {
			System.out.println("修改密码失败！");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 修改用户名
	 * 
	 * @param itcode
	 *            员工号
	 * @param name
	 *            新用户名
	 * @param jdbcTemplate
	 * @return true成功，false失败
	 */
	public static boolean changeUsername(String itcode, String name, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.update("update dc_user set username=? where itcode=?;", new Object[] { name, itcode });
			if (i > 0)
				return true;
		} catch (Exception e) {
			System.out.println("修改昵称失败");
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 验证用户登陆
	 * 
	 * @param itcode
	 * @param password
	 * @param jdbcTemplate
	 * @return
	 */
	public static dc_user verify(String itcode, String password, JdbcTemplate jdbcTemplate) {

		int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=? and password=?;",
				new Object[] { itcode, password });
		if (i == 1) {
			RowMapper<dc_user> user_mapper = new BeanPropertyRowMapper<dc_user>(dc_user.class);
			try {
				dc_user wanted = jdbcTemplate.queryForObject("select * from dc_user where itcode=?;", user_mapper,new Object[] {itcode});
				return wanted;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		} else {
			return null;
		}

	}
	
	/**
	 * 检查用户itcode是否存在
	 * @param itcode
	 * @param jdbcTemplate
	 * @return
	 */
	public static boolean checkItcode(String itcode, JdbcTemplate jdbcTemplate) {
		try {
			int i = jdbcTemplate.queryForInt("select count(*) from dc_user where itcode=?",
					new Object[] { itcode});
			if (i == 0)
				return true;
			else {
				System.out.println("itcode重复！"+itcode);
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("itcode不匹配！");
		}
		return false;
	}
	
	public static int setPortrait(String itcode,String portrait,JdbcTemplate jdbcTemplate){
		int i = jdbcTemplate.update("update dc_user set portrait=? where itcode=?;", new Object[] {portrait, itcode });
		
		return i;
	}

	
}

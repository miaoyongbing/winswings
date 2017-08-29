package com.dcone.dtss.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.*;
/**
 * 
 * @author wrs
 *处理交易信息
 */
public class TradeDAO {
	/**
	 * 获取用户全部交易
	 * @param uid 用户id
	 * @param jdbcTemplate
	 * @return 用户全部交易
	 */
	public List<dc_trade> getTradesByUid(int uid,JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trade_mapper=new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		try {
			dc_wallet wallet= WalletDAO.getWalletByUid(uid, jdbcTemplate);
			List<dc_trade> wanted=jdbcTemplate.query("select * from dc_trade where wid=?;", trade_mapper,new Object[] {wallet.getWid()});
			return wanted;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("uid错误,找不到用户!");
		}
		return null;
	}
	/**
	 * 获取用户全部交易
	 * @param itcode 员工号
	 * @param jdbcTemplate
	 * @return 用户全部交易
	 */
	public List<dc_trade> getTradesByItcode(String itcode,JdbcTemplate jdbcTemplate){
		dc_user user=UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getTradesByUid(user.getUid(), jdbcTemplate);
	}
	/**
	 * 获取用户全部交易
	 * @param username 用户名
	 * @param jdbcTemplate
	 * @return 用户全部交易
	 */
	public List<dc_trade> getTradesByUser(String username,JdbcTemplate jdbcTemplate){
		List<dc_user> temp=UserDAO.getUserByName(username, jdbcTemplate);
		List<dc_trade> wanted=new ArrayList<dc_trade>();
		for(dc_user user:temp) {
			List<dc_trade> end=getTradesByUid(user.getUid(), jdbcTemplate);
			if(!end.isEmpty())
				for(dc_trade temp2:end)
					wanted.add(temp2);
		}
		return wanted;
	}
	/**
	 * 获取一段时间内用户全部交易
	 * @param uid 用户id
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param jdbcTemplate
	 * @return 用户部分交易
	 */
	public List<dc_trade> getTimeTradesByUid(int uid,String start,String end,JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trade_mapper=new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		try {
			dc_wallet wallet= WalletDAO.getWalletByUid(uid, jdbcTemplate);
			List<dc_trade> wanted=jdbcTemplate.query("select * from dc_trade where wid=? and tradetime>? and tradetime<?;", trade_mapper,new Object[] {wallet.getWid(),start,end});
			int i=0;
			System.out.println("start is "+start+",end is "+end);
			for(dc_trade temp:wanted) {
				System.out.println("i is "+i);
				i++;
				temp.toString();
			}
			return wanted;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("uid错误,找不到用户相关交易记录!");
		}
		return null;
	}
	/**
	 * 获取用户一段时间全部交易
	 * @param itcode 员工号
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param jdbcTemplate
	 * @return 用户部分交易
	 */
	public List<dc_trade> getTimeTradesByItcode(String itcode,String start,String end,JdbcTemplate jdbcTemplate){
		dc_user user=UserDAO.getUserByItcode(itcode, jdbcTemplate);
		return getTimeTradesByUid(user.getUid(),start,end, jdbcTemplate);
	}
	/**
	 * 获取用户一段时间全部交易
	 * @param username 用户名
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param jdbcTemplate
	 * @return 用户部分交易
	 */
	public List<dc_trade> getTimeTradesByUser(String username,String start,String end,JdbcTemplate jdbcTemplate){
		List<dc_user> temp=UserDAO.getUserByName(username, jdbcTemplate);
		List<dc_trade> wanted=new ArrayList<dc_trade>();
		for(dc_user user:temp) {
			List<dc_trade> temp1=getTimeTradesByUid(user.getUid(),start,end, jdbcTemplate);
			if(!temp1.isEmpty())
				for(dc_trade e:temp1)
					wanted.add(e);
		}
		return wanted;
	}
	
	/**
	 * 判断用户支出交易能否进行
	 * @param wid 钱包id
	 * @param amount 交易额
	 * @param jdbcTemplate
	 * @return true可以，false不行
	 */
	public static boolean preTrade(int wid,int amount,JdbcTemplate jdbcTemplate) {
		RowMapper<dc_wallet> wallet_mapper=new BeanPropertyRowMapper<dc_wallet>(dc_wallet.class);
		try {
			dc_wallet wanted=jdbcTemplate.queryForObject("select * from dc_wallet where wid=?;", wallet_mapper,new Object[] {wid});
			if(wanted.getAmount()>=amount)
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("wid错误,找不到用户钱包!");
		}
		return false;
	}
	/**
	 * 创建交易：用户账户转出
	 * @param wid 打赏用户钱包id
	 * @param amount 数额
	 * @param date 时间
	 * @param memo 备注
	 * @param jdbcTemplate
	 * @return true成功，false失败
	 */
	public static boolean createTrade( String date ,int wid, int amount, String memo,JdbcTemplate jdbcTemplate) {
		if(preTrade(wid,amount, jdbcTemplate)) {
			//写入交易数据
			int i = jdbcTemplate.update("insert into dc_trade(wid,volume,tradetime,tip) values(?,?,?,?)",new Object[] { wid, amount, date, memo });
			int j = WalletDAO.walletMinusByWid(wid, amount, jdbcTemplate);
			if (i*j > 0)
				return true;
		}
		return false;
	}
	/**
	 * 创建发红包交易
	 * @param wid 钱包id
	 * @param lucknumber 红包数额
	 * @param time 时间
	 * @param tip 备注
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int createTrade(int wid,int lucknumber, String time, String tip,JdbcTemplate jdbcTemplate) {
		
		return 0;
	}
	/**
	 * 创建交易，用户账户转入
	 * @param wid 钱包id
	 * @param time 时间
	 * @param amount 数额
	 * @param tip 备注
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int createTrade(int wid,String time,int amount,String tip,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("insert into dc_trade(wid,volume,tradetime,tip) values(?,?,?,?);",new Object[] {wid,amount,time,tip});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("创建充值记录失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取用户指定类型交易记录
	 * @param wid 用户钱包id
	 * @param tip 交易类型关键字
	 * @param jdbcTemplate
	 * @return 指定交易记录
	 */
	public static List<dc_trade> getTradesByTip(int wid,String tip,JdbcTemplate jdbcTemplate){
		RowMapper<dc_trade> trademapper=new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		try {
			List<dc_trade> wanted =jdbcTemplate.query("select * from dc_trade where wid=? and tip like ?;", trademapper,new Object[] {wid,"%"+tip+"%"});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取相应记录失败！");
		}
		return null;
	}
	/**
	 * 获取指定用户指定类型一段时间内交易记录
	 * @param wid 用户钱包id
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param jdbcTemplate
	 * @param tip 交易类型
	 * @return 指定记录
	 */
	public static List<dc_trade> getTimeTipTradesByWid(int wid,String start,String end,JdbcTemplate jdbcTemplate,String tip){
		RowMapper<dc_trade> trade_mapper=new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		try {
			List<dc_trade> wanted=jdbcTemplate.query("select * from dc_trade where wid=? and tradetime>? and tradetime<? and tip like ?;", trade_mapper,new Object[] {wid,start,end,"%"+tip+"%"});
			int i=0;
			System.out.println("start is "+start+",end is "+end);
			for(dc_trade temp:wanted) {
				System.out.println("i is "+i);
				i++;
				temp.toString();
			}
			return wanted;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("uid错误,找不到用户相关交易记录!");
		}
		return null;
	}
	/**
	 * 进行综合查询
	 * @param jd jdbctemplate
	 * @param j1 判断对用户的限定
	 * @param user 用户itcode或者用户名
	 * @param j2 进行对交易类型的限定
	 * @param id 对交易另一方账户的限定
	 * @param j3 对金额的限定：0无限定，1大于，2等于，3小于
	 * @param num 金额
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
	/**
	 * 进行综合查询
	 * @param jd jdbctemplate
	 * @param j1 判断对用户的限定
	 * @param user 用户itcode或者用户名
	 * @param j2 进行对交易类型的限定
	 * @param id 对交易另一方账户的限定
	 * @param j3 对金额的限定：0无限定，1大于，2等于，3小于
	 * @param num 金额
	 * @param start 开始时间
	 * @param end 结束时间
	 * @return
	 */
	public static List<dc_trade> getConprehensiveTrades(JdbcTemplate jd,
			String j1,String user,String j2,String id,int j3,int num,String start,
			String end){
		RowMapper<dc_trade> tm=new BeanPropertyRowMapper<dc_trade>(dc_trade.class);
		int wid=0;
		switch(j1) {
		case "所有":
			switch(j2) {
			case "所有":
				switch(j3) {
				case 0:
					try {
						List<dc_trade> wanted = jd.query("select * from dc_trade where tradetime>? and tradetime <?;",
								tm, new Object[] { start, end });
						System.out.println("now wanted:"+wanted);
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 1:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume>? and tradetime>? and tradetime <?;", tm,
								new Object[] { num, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 2:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume=? and tradetime>? and tradetime <?;", tm,
								new Object[] { num, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 3:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume<? and tradetime>? and  tradetime <?;", tm,
								new Object[] { num, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				}
				break;
			case "充值":
				switch(j3) {
				case 0:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where tradetime>? and  tradetime <? and tip like ?;", tm,
								new Object[] { start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 1:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume>? and tradetime>? and  tradetime <? and tip like ?;",
								tm, new Object[] { num, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 2:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] { num, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 3:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] { num, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				}
				break;
			case "提现":
				switch(j3) {
				case 0:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where tradetime>? and tradetime <? and tip like ?;", tm,
								new Object[] { start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 1:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] { num, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 2:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] { num, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 3:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] { num, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				}
				break;
			case "红包":
				switch(j3) {
				case 0:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where tradetime>? and tradetime <? and tip like ? ;",
										tm, new Object[] {  start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where tradetime>? and tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] { start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 1:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ? ;",
										tm, new Object[] { num, start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] { num, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 2:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ? ;",
										tm, new Object[] { num, start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] { num, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 3:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ? ;",
										tm, new Object[] { num, start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] { num, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				}
				break;
			case "节目打赏":
				switch(j3) {
				case 0:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 1:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 2:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 3:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				}
				break;
			case "红包雨":
				switch(j3) {
				case 0:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where  tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 1:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume>? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 2:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume=? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 3:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where volume<? and tradetime>? and tradetime <? and tip like ? ;",
									tm, new Object[] { num, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				}
				break;
			}
			break;
		case "根据itcode":
			dc_wallet wtemp1=WalletDAO.getWalletByItcode(user, jd);
			if(wtemp1!=null)
				wid=wtemp1.getWid();
			else
				return null;
			switch(j2) {
			case "所有":
				switch(j3) {
				case 0:
					try {
						List<dc_trade> wanted = jd.query("select * from dc_trade where wid=? and tradetime>? and tradetime <?;",
								tm, new Object[] {wid, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 1:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume>? and tradetime>? and tradetime <?;", tm,
								new Object[] {wid, num, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 2:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume=? and tradetime>? and tradetime <?;", tm,
								new Object[] {wid, num, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 3:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume<? and tradetime>? and tradetime <?;", tm,
								new Object[] {wid, num, start, end });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				}
				break;
			case "充值":
				switch(j3) {
				case 0:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and tradetime>? and tradetime <? and tip like ?;", tm,
								new Object[] {wid, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 1:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume>? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] {wid, num, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 2:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume=? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] {wid, num, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 3:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume<? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] {wid, num, start, end, "%充值%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				}
				break;
			case "提现":
				switch(j3) {
				case 0:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and tradetime>? and tradetime <? and tip like ?;", tm,
								new Object[] {wid, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 1:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume>? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] {wid, num, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 2:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume=? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] {wid, num, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				case 3:
					try {
						List<dc_trade> wanted = jd.query(
								"select * from dc_trade where wid=? and volume<? and tradetime>? and tradetime <? and tip like ?;",
								tm, new Object[] {wid, num, start, end, "%提现%" });
						if (!wanted.isEmpty())
							return wanted;
					} catch (Exception e) {
						e.printStackTrace();
						System.out.println("错误,找不到交易记录!");
					}
					break;
				}
				break;
			case "红包":
				switch(j3) {
				case 0:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and tradetime>? and tradetime <? and tip like ? ;",
										tm, new Object[] {wid,  start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] {wid, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 1:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
										tm, new Object[] {wid, num, start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] {wid, num, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 2:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
										tm, new Object[] {wid, num, start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] {wid, num, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 3:
					if(id!="") {
						int i=Integer.parseInt(id);
						luck l=LuckDAO.getLuckByLid(i, jd);
						if(l==null)
							return null;
						else 
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
										tm, new Object[] {wid, num, start, end, "%"+l.getTip()+"%" });
								if (!wanted.isEmpty())
									return wanted;
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
					}else 
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
									tm, new Object[] {wid, num, start, end, "%抢%", "%红包%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				}
				break;
			case "节目打赏":
				switch(j3) {
				case 0:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 1:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 2:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 3:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%节目id：" + i + "" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "打赏%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				}
				break;
			case "红包雨":
				switch(j3) {
				case 0:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 1:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 2:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				case 3:
					if(id!="") {
						int i=Integer.parseInt(id);
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%第" + i + "轮红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					} else
						try {
							List<dc_trade> wanted = jd.query(
									"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
									tm, new Object[] {wid, num, start, end, "%红包雨%" });
							if (!wanted.isEmpty())
								return wanted;
						} catch (Exception e) {
							e.printStackTrace();
							System.out.println("错误,找不到交易记录!");
						}
					break;
				}
				break;
			}
			break;
		case "根据用户名":
			List<dc_user> users=UserDAO.getUserByName(user, jd);
			List<dc_trade> allwanted=new ArrayList<dc_trade>();
			if(!users.isEmpty()&&users!=null) {
				for(dc_user temp:users) {
					wid=WalletDAO.getWalletByUid(temp.getUid(), jd).getWid();
					switch(j2) {
					case "所有":
						switch(j3) {
						case 0:
							try {
								List<dc_trade> wanted = jd.query("select * from dc_trade where wid=? and tradetime>? and  tradetime <?;",
										tm, new Object[] {wid, start, end });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 1:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <?;", tm,
										new Object[] {wid, num, start, end });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 2:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <?;", tm,
										new Object[] {wid, num, start, end });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 3:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <?;", tm,
										new Object[] {wid, num, start, end });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						}
						break;
					case "充值":
						switch(j3) {
						case 0:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ?;", tm,
										new Object[] {wid, start, end, "%充值%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 1:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ?;",
										tm, new Object[] {wid, num, start, end, "%充值%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 2:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ?;",
										tm, new Object[] {wid, num, start, end, "%充值%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 3:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <?and tip like ?;",
										tm, new Object[] {wid, num, start, end, "%充值%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						}
						break;
					case "提现":
						switch(j3) {
						case 0:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ?;", tm,
										new Object[] {wid, start, end, "%提现%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 1:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ?;",
										tm, new Object[] {wid, num, start, end, "%提现%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 2:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ?;",
										tm, new Object[] {wid, num, start, end, "%提现%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						case 3:
							try {
								List<dc_trade> wanted = jd.query(
										"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ?;",
										tm, new Object[] {wid, num, start, end, "%提现%" });
								if (!wanted.isEmpty())
									for(dc_trade temp1:wanted)
										allwanted.add(temp1);
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("错误,找不到交易记录!");
							}
							break;
						}
						break;
					case "红包":
						switch(j3) {
						case 0:
							if(id!="") {
								int i=Integer.parseInt(id);
								luck l=LuckDAO.getLuckByLid(i, jd);
								if(l==null)
									return null;
								else 
									try {
										List<dc_trade> wanted = jd.query(
												"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
												tm, new Object[] {wid,  start, end, "%"+l.getTip()+"%" });
										if (!wanted.isEmpty())
											for(dc_trade temp1:wanted)
												allwanted.add(temp1);
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println("错误,找不到交易记录!");
									}
							}else 
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
											tm, new Object[] {wid, start, end, "%抢%", "%红包%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 1:
							if(id!="") {
								int i=Integer.parseInt(id);
								luck l=LuckDAO.getLuckByLid(i, jd);
								if(l==null)
									return null;
								else 
									try {
										List<dc_trade> wanted = jd.query(
												"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
												tm, new Object[] {wid, num, start, end, "%"+l.getTip()+"%" });
										if (!wanted.isEmpty())
											for(dc_trade temp1:wanted)
												allwanted.add(temp1);
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println("错误,找不到交易记录!");
									}
							}else 
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
											tm, new Object[] {wid, num, start, end, "%抢%", "%红包%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 2:
							if(id!="") {
								int i=Integer.parseInt(id);
								luck l=LuckDAO.getLuckByLid(i, jd);
								if(l==null)
									return null;
								else 
									try {
										List<dc_trade> wanted = jd.query(
												"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
												tm, new Object[] {wid, num, start, end, "%"+l.getTip()+"%" });
										if (!wanted.isEmpty())
											for(dc_trade temp1:wanted)
												allwanted.add(temp1);
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println("错误,找不到交易记录!");
									}
							}else 
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
											tm, new Object[] {wid, num, start, end, "%抢%", "%红包%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 3:
							if(id!="") {
								int i=Integer.parseInt(id);
								luck l=LuckDAO.getLuckByLid(i, jd);
								if(l==null)
									return null;
								else 
									try {
										List<dc_trade> wanted = jd.query(
												"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
												tm, new Object[] {wid, num, start, end, "%"+l.getTip()+"%" });
										if (!wanted.isEmpty())
											for(dc_trade temp1:wanted)
												allwanted.add(temp1);
									} catch (Exception e) {
										e.printStackTrace();
										System.out.println("错误,找不到交易记录!");
									}
							}else 
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? and tip like ?;",
											tm, new Object[] {wid, num, start, end, "%抢%", "%红包%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						}
						break;
					case "节目打赏":
						switch(j3) {
						case 0:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, start, end, "%节目id：" + i + "" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and tradetime>? and tradetime <? and tip like ? ;",
											tm, new Object[] {wid, start, end, "打赏%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 1:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%节目id：" + i + "" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "打赏%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 2:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%节目id：" + i + "" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "打赏%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 3:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%节目id：" + i + "" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "打赏%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						}
						break;
					case "红包雨":
						switch(j3) {
						case 0:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, start, end, "%第" + i + "轮红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, start, end, "%红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 1:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%第" + i + "轮红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume>? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 2:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%第" + i + "轮红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume=? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						case 3:
							if(id!="") {
								int i=Integer.parseInt(id);
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%第" + i + "轮红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							} else
								try {
									List<dc_trade> wanted = jd.query(
											"select * from dc_trade where wid=? and volume<? and tradetime>? and  tradetime <? and tip like ? ;",
											tm, new Object[] {wid, num, start, end, "%红包雨%" });
									if (!wanted.isEmpty())
										for(dc_trade temp1:wanted)
											allwanted.add(temp1);
								} catch (Exception e) {
									e.printStackTrace();
									System.out.println("错误,找不到交易记录!");
								}
							break;
						}
						break;
					}
				}
				if(!allwanted.isEmpty())
					return allwanted;
			}
			break;
		}
		return null;
	}
}

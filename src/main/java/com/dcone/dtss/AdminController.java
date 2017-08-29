package com.dcone.dtss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.dao.UserWalletDAO;
import com.dcone.dtss.dao.LN_RecordDAO;
import com.dcone.dtss.dao.L_NumberDAO;
import com.dcone.dtss.dao.LuckDAO;
import com.dcone.dtss.dao.P_PocketDAO;
import com.dcone.dtss.dao.ProgramDAO;
import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_user_wallet;
import com.dcone.dtss.model.dc_wallet;
import com.dcone.dtss.model.l_number;
import com.dcone.dtss.model.ln_record;
import com.dcone.dtss.model.luck;
import com.dcone.dtss.model.p_pocket;
import com.dcone.dtss.model.program;

import MyThead.LuckyNumberThread;
/**
 * 
 * @author wrs
 *超级用户进行红包雨等操作
 *后期应设置检查用户是否为超级用户，否则拒绝访问
 */
@Controller
public class AdminController {
	
	boolean flag = false;
	@Autowired
	JdbcTemplate template;
	/**判断用户是否登录
	 * 登录成功后显示admin页面
	 * @return
	 */
	@RequestMapping("/luckyadmin")
	public String admin() {
		//判断用户是否登录
		//登录成功后显示admin页面
		return "luckymoney";
	}
	
		
	/**
	 * 开启红包雨
	 * @param round 红包雨轮次
	 * @return 红包雨结果界面
	 */
	@RequestMapping("/luck_check") 
	public String Lucky_on(String round) {
		System.out.println("红包雨开启！");
		LuckyNumberThread t = new LuckyNumberThread();
		t.setTemplate(template);
		int r = 0;
		try {
			r = Integer.parseInt(round);
		} catch (Exception e) {
			// TODO: handle exception
		}
		t.setRound(r);
		t.setFlag(true);		
		t.start();
		return "luckymoney";
	}
	/**
	 * 查询全部红包发放记录
	 * @param model
	 * @return 显示所有红包发放记录
	 */
	@RequestMapping("/viewrecord")
	public String viewrecord(Model model) {
		List<ln_record> wanted=LN_RecordDAO.getAllRecords(template);
		String str1="",str2="",str3="",str4="",str5="";
		if(wanted!=null) {
			str1="流水号";
			str2="红包雨轮次";
			str3="用户钱包id";
			str4="红包数额";
			str5="交易时间";
		}
		else {
			str1="暂无红包雨相关交易记录！";
		}
		model.addAttribute("str1", str1);
		model.addAttribute("str2", str2);
		model.addAttribute("str3", str3);
		model.addAttribute("str4", str4);
		model.addAttribute("str5", str5);
		model.addAttribute("record",wanted);
		return "viewrecord";
	}
	/**判断用户是否登录
	 * 登录成功后显示admin页面
	 * @return
	 */
	@RequestMapping("/admin")
	public String ToAdmin() {
		return "admin";
	}
	
	/**
	 * 用户查询
	 * @return
	 */
	@RequestMapping("/adminselcectusers1")
	public void Adminselectuser(String selecttype,String itcode,String username,String balencetype,String balence,HttpServletResponse response) {
		System.out.println(selecttype); 
		List<dc_user_wallet> userwallts=new ArrayList<dc_user_wallet>();
		if(selecttype=="所有") 
		{ 
			userwallts=UserWalletDAO.getAllWallInfoByUser(template);
			for(dc_user_wallet userwallet: userwallts) {
				String active="激活";
				if(userwallet.getUid()==0)
				{
					active="未激活";
				}
				String s="<tr><td>"+userwallet.getItcode()+"</td><td>"+userwallet.getUsername()+"</td><td>"+active+"</td><td>"+userwallet.getAmount()+"</td></tr>";
				try {
					response.getWriter().write(s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}else if(selecttype=="itcode") {
			
		}else if(selecttype=="用户名") {
			
		}else if(selecttype=="余额") {
			
		}else {
			System.out.println("类型选择出错！！！");
		}
		

		
		System.out.println(itcode);
		System.out.println(username);
		System.out.println(balencetype);
		System.out.println(balence);
		
	}
	/**
	 * 用户查询
	 * @param judge 1所有用户，2按itcode查询，3按名字查询，4按照余额查询
	 * @param response
	 * @param str1 judge=2时：itcode；judge=3时：名字；judge=4时：1大于，2等于，3小于
	 * @param str2 judge=4时：余额
	 */
	@RequestMapping("/adminchecku")
	public void adminCheckU(@RequestParam(value="judge",defaultValue="0")String judge,
			HttpServletResponse response,@RequestParam(value="str1",defaultValue="")String str1,
			@RequestParam(value="str2",defaultValue="")String str2) {
		switch(judge) {
		case "1":
			List<dc_user_wallet> wanted1=UserWalletDAO.getAllWallInfoByUser(template);
			for (dc_user_wallet temp : wanted1) {
				try {
					if(temp.getAmount()==-1) {
					response.getWriter().write("<tr><td>" + temp.getUid() + "</td><td>" + temp.getItcode() + "</td><td>"
							+ temp.getUsername() + "</td><td>" + "未激活" + "</td></tr>");
					}else {
						response.getWriter().write("<tr><td>" + temp.getUid() + "</td><td>" + temp.getItcode() + "</td><td>"
								+ temp.getUsername() + "</td><td>" + temp.getAmount() + "</td></tr>");
					}
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
				System.out.println(temp.toString());
			}
			break;
		case "2":
			if(str1!="")
			{
				dc_user_wallet wanted=UserWalletDAO.getWallInfoByItcode(str1, template);
				if(wanted!=null) {
					try {
						response.getWriter().write("<tr><td>" + wanted.getUid() + "</td><td>" + wanted.getItcode() + "</td><td>"
								+ wanted.getUsername() + "</td><td>" + wanted.getAmount() + "</td></tr>");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
				else {
					try {
						response.getWriter().write("itcode错误！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				}
			}
			break;
		case "3":
			if(str1!="") {
			List<dc_user_wallet>wanted=UserWalletDAO.getWallInfoByUserName(str1, template);
			if(!wanted.isEmpty()) {
				for (dc_user_wallet temp : wanted) {
					try {
						response.getWriter().write("<tr><td>" + temp.getUid() + "</td><td>" + temp.getItcode() + "</td><td>"
								+ temp.getUsername() + "</td><td>" + temp.getAmount() + "</td></tr>");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
					System.out.println(temp.toString());
				}
			}
			else {
				try {
					response.getWriter().write("名字错误！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
			}
			break;
		case "4":
			if(str2!="") {
				List<dc_user_wallet> wanted=new ArrayList<dc_user_wallet>();
				int num=Integer.parseInt(str2);
				switch(str1) {
				case "1":
					wanted=UserWalletDAO.getWallInfoByAmountPlus(num, template);
					break;
				case "2":
					wanted=UserWalletDAO.getWallInfoByAmount(num, template);
					break;
				case "3":
					wanted=UserWalletDAO.getWallInfoByAmountMinus(num, template);
					break;
				}
				if(!wanted.isEmpty())
					for (dc_user_wallet temp : wanted) {
						try {
							response.getWriter().write("<tr><td>" + temp.getUid() + "</td><td>" + temp.getItcode() + "</td><td>"
									+ temp.getUsername() + "</td><td>" + temp.getAmount() + "</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
						System.out.println(temp.toString());
					}
			}
			break;
		}
	}
	/**
	 * 为用户充值
	 * @param itcode 用户itcode
	 * @param locale
	 * @param money 充值金额
	 * @param response
	 */
	@RequestMapping("/adminaddmoney")
	public void adminAddMoney(String itcode,Locale locale,String money,HttpServletResponse response) {
		dc_user user=UserDAO.getUserByItcode(itcode, template);
		if(user!=null) {
			String name=user.getUsername();
			int num=0;
			num=Integer.parseInt(money);
			if(num!=0) {
			int i=WalletDAO.balance_add(itcode, name, num, locale, template);
			if(i==1)
				try {
					response.getWriter().write("您为"+name+"充值成功!");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			else
				try {
					response.getWriter().write("您为"+name+"充值失败!");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		} else
			try {
				response.getWriter().write("itcode错误！");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	/**
	 * 为用户修改密码
	 * @param itcode 用户itcode
	 * @param locale
	 * @param code 新密码
	 * @param response
	 */
	@RequestMapping("/adminccode")
	public void adminCCode(String itcode,Locale locale,String code,HttpServletResponse response) {
		dc_user user=UserDAO.getUserByItcode(itcode, template);
		if(user!=null) {
			String name=user.getUsername();;
			String old=user.getPassword();
			boolean i=UserDAO.changePassword(itcode, old, code, template);
			if(i)
				try {
					response.getWriter().write("您为"+name+"修改密码成功!");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			else
				try {
					response.getWriter().write("您为"+name+"修改密码失败!");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		} else {
			try {
				response.getWriter().write("itcode错误！");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			}
	}
	/**
	 * 查看红包总数或者添加新红包
	 * @param judge 1查看红包总数，2添加新红包
	 * @param amount judge=2时：新红包金额
	 * @param response
	 */
	@RequestMapping("/adminluck")
	public void adminLuck(String judge,@RequestParam(value="amount",defaultValue="0")String amount,HttpServletResponse response) {
		int j=0;
		j=Integer.parseInt(judge);
		switch(j) {
		case 1:
			List<luck> l=LuckDAO.getAllLuck(template);
			int i=l.size();
			System.out.println("i is:"+i);
			try {
				response.getWriter().write(""+i+"");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			break;
		case 2:
			int num=0;
			num=Integer.parseInt(amount);
			if(num!=0) {
				List<luck> l2=LuckDAO.getAllLuck(template);
				int i2=l2.size();
				i2++;
				int result=LuckDAO.addLuck("第"+i2+"个红包", num, template);
				if(result==1)
					try {
						response.getWriter().write("添加红包成功！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				else
					try {
						response.getWriter().write("添加红包失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			} else
				try {
					response.getWriter().write("金额不能为0！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			break;
		}
	}
	/**
	 * 用于节目添加和查询所有节目
	 * @param response
	 * @param j 1表示节目添加，2表示查看所有节目
	 * @param str1 j=1时：表示节目名
	 * @param str2 j=1时：表示演员名
	 * @param str3 j=1时：表示演出单位名字
	 */
	@RequestMapping("/programadd")
	public void programAdd(HttpServletResponse response,String j,String str1,String str2,String str3) {
		switch(j){
		case "1":
			int i=0;
			program temp1=ProgramDAO.getProgramByName(str1, template);
			if(temp1==null)
			i=ProgramDAO.addProgram(str1, str2, str3, template);
			if(i>0) {
				int i2=P_PocketDAO.addPocket(str1, 0, str2, str3, template);
				if(i2>0)
					try {
						response.getWriter().write("节目添加成功！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				else
					try {
						response.getWriter().write("节目添加失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			}
			else
				try {
					response.getWriter().write("节目添加失败！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			break;
		case "2":
			List<program> program=ProgramDAO.getAllProgram(template);
			if(!program.isEmpty())
				for(program temp:program) 
					try {
						response.getWriter().write("<tr><td>"+temp.getPid()+"</td><td>"+temp.getP_name()+"</td><td>"+temp.getActor()+"</td><td>"+temp.getDepartment()+"</td></tr>");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
			break;
		}
	}
	
	/**
	 * 三类账户：红包、红包雨、节目的查询、充值、提现
	 * @param response
	 * @param j 1，红包雨账户操作；2红包账户操作；3节目账户操作
	 * @param j2 1，查询；2充值；3，提现
	 * @param j3 j2=1时：若j=1，则0所有轮次，1—3表示不同轮次红包雨；否则，1表示所有账户（红包/节目），2表示j4为指定账户id；；；
	 * j2！=1时，表示红包雨轮次/红包id/节目id
	 * @param j4 j2=2、3时，表示金额；j2=1时，若有值则表示对应账户id
	 */
	@RequestMapping("/allmoneycheck")
	public void allMoneyCheck(HttpServletResponse response,String j,String j2,String j3,
			String j4) {
		System.out.println("aaaaa"+j);
		switch(j) {
		case "1":
			int lround=0;
			lround=Integer.parseInt(j3);
			System.out.println(j2);
			switch(j2) {
			case "1"://查询
				System.out.println(j3);
				if(lround==0) {
					List<l_number> wanted1=L_NumberDAO.getAllLN(template);
					if(!wanted1.isEmpty())
						for(l_number ltemp:wanted1)
							try {
								response.getWriter().write("<tr><td>"+ltemp.getRound()+"</td><td>"+
							ltemp.getTotal()+"</td><td>"+ltemp.getTips()+"</td></tr>");
							} catch (IOException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
					else
						try {
							response.getWriter().write("查询失败！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				else {
					l_number wanted1=L_NumberDAO.getLNByRound(lround, template);
					if(wanted1!=null)
						try {
							response.getWriter().write("<tr><td>"+wanted1.getRound()+"</td><td>"+
									wanted1.getTotal()+"</td><td>"+wanted1.getTips()+"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					else
						try {
							response.getWriter().write("查询失败！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				}
				break;
			case "2"://充值
				boolean lend1=false;
				if(lround!=0) {
					int money=0;
					money=Integer.parseInt(j4);
					if(money==0)
						lend1=true;
					else {
						int addend=L_NumberDAO.addmoney(lround, money, template);
						if(addend>0)
							try {
								response.getWriter().write("充值成功！");
							} catch (IOException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
						else
							lend1=true;
					}
				} else
					lend1=true;
				if(lend1)
					try {
						response.getWriter().write("充值失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			case "3"://提现
				boolean lend2=false;
				if(lround!=0) {
					int money=0;
					money=Integer.parseInt(j4);
					if(money==0)
						lend1=true;
					else {
						boolean addend=L_NumberDAO.getMoney(lround, money, template);
						if(addend)
							try {
								response.getWriter().write("提现成功！");
							} catch (IOException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
						else
							lend1=true;
					}
				} else
					lend2=true;
				if(lend2)
					try {
						response.getWriter().write("提现失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			}
			break;
		case "2":
			boolean end2=false;
			System.out.println("temp1");
			switch(j2) {
			case "1"://查询
				if(j3.equals("1")) { 
					System.out.println("temp1");
					List<luck> wanted2=LuckDAO.getAllLuck(template);
					if(!wanted2.isEmpty())
					for(luck ltemp1:wanted2)
						try {
							response.getWriter().write("<tr><td>"+ltemp1.getLid()+
									"</td><td>"+ltemp1.getAmount()+"</td><td>"+
									ltemp1.getTip()+"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					else
						end2=true;
					}
				else {
					int j4judge2=0;
					j4judge2=Integer.parseInt(j4);
					luck wanted2=LuckDAO.getLuckByLid(j4judge2, template);
					if(wanted2==null)
						end2=true;
					else
						try {
							response.getWriter().write("<tr><td>"+wanted2.getLid()+
									"</td><td>"+wanted2.getAmount()+"</td><td>"+
									wanted2.getTip()+"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				}
				if(end2)
					try {
						response.getWriter().write("查询失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			case "2"://充值
				int j2id1=0,j2m1=0;
				j2id1=Integer.parseInt(j3);
				luck j2l2=LuckDAO.getLuckByLid(j2id1, template);
				j2m1=Integer.parseInt(j4);
				if(j2l2!=null&&j2m1>0) {
					int i=LuckDAO.setLuckByLid(j2id1, j2l2.getAmount()+j2m1, template);
					if(i>0)
						try {
							response.getWriter().write("充值成功！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					else
						end2=true;
				}else
					end2=true;
				if(end2)
					try {
						response.getWriter().write("充值失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			case "3"://提现
				int j2id2=0,j2m2=0;
				j2id2=Integer.parseInt(j3);
				luck j2l3=LuckDAO.getLuckByLid(j2id2, template);
				j2m2=Integer.parseInt(j4);
				if(j2l3!=null&&j2m2>0&&j2l3.getAmount()-j2m2>0) {
					int i=LuckDAO.setLuckByLid(j2id2, j2l3.getAmount()-j2m2, template);
					if(i>0)
						try {
							response.getWriter().write("提现成功！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					else
						end2=true;
				}else
					end2=true;
				if(end2)
					try {
						response.getWriter().write("提现失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			}
			break;
		case "3":
			boolean end3=false;
			switch(j2) {
			case "1"://查询
				if(j3.equals("1")) { 
					System.out.println("temp1");
					List<program> wanted3=ProgramDAO.getAllProgram(template);
					if(!wanted3.isEmpty())
					for(program ltemp1:wanted3)
					{
						p_pocket ltemp2=P_PocketDAO.getPocketById(ltemp1.getPid(), template);
						try {
							response.getWriter().write("<tr><td>"+ltemp1.getPid()+
									"</td><td>"+ltemp1.getP_name()+"</td><td>"+
									ltemp1.getActor()+"</td><td>"+ltemp1.getDepartment()+
									"</td><td>"+ltemp2.getAmount()+"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
					else
						end3=true;
					}
				else {
					int j4judge2=0;
					j4judge2=Integer.parseInt(j4);
					program wanted2=ProgramDAO.getProgramByPid(j4judge2, template);
					if(wanted2==null)
						end3=true;
					else
					{
						p_pocket jtemp1=P_PocketDAO.getPocketById(j4judge2, template);
						try {
							response.getWriter().write("<tr><td>"+wanted2.getPid()+
									"</td><td>"+wanted2.getP_name()+"</td><td>"+
									wanted2.getActor()+"</td><td>"+wanted2.getDepartment()+
									"</td><td>"+jtemp1.getAmount()+"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					}
				}
				if(end3)
					try {
						response.getWriter().write("查询失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			case "2"://充值
				int j2id1=0,j2m1=0;
				j2id1=Integer.parseInt(j3);
				p_pocket j2l2=P_PocketDAO.getPocketById(j2id1, template);
				j2m1=Integer.parseInt(j4);
				if(j2l2!=null&&j2m1>0) {
					int i=P_PocketDAO.setPocketByPid(j2id1,j2l2.getAmount()+j2m1, template);
					if(i>0)
						try {
							response.getWriter().write("充值成功！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					else
						end3=true;
				}else
					end3=true;
				if(end3)
					try {
						response.getWriter().write("充值失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			case "3"://提现
				int j2id2=0,j2m2=0;
				j2id2=Integer.parseInt(j3);
				p_pocket j2l3=P_PocketDAO.getPocketById(j2id2, template);
				j2m2=Integer.parseInt(j4);
				if(j2l3!=null&&j2m2>0&&j2l3.getAmount()-j2m2>0) {
					int i=P_PocketDAO.setPocketByPid(j2id2,j2l3.getAmount()-j2m2, template);
					if(i>0)
						try {
							response.getWriter().write("提现成功！");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
					else
						end3=true;
				}else
					end3=true;
				if(end3)
					try {
						response.getWriter().write("提现失败！");
					} catch (IOException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}
				break;
			}
			break;
		}
	}
	@RequestMapping("/conprehensivecheck")
	public void conprehensiveCheck(HttpServletResponse response, String str1, String str2, String str3,@RequestParam(value="str4",defaultValue="") String str4,
			String str5, String str6, String str7, String str8) {
		String starttime=str7+" 00:00:00",endtime=str8+" 23:59:59";
		System.out.println(str1+","+str2+","+str3+","+str4+","+str5+","+str6+","+str7+","+str8+",");
		int i=0,j=0;
		switch(str5) {
		case "所有":
			i=0;
			break;
		case "大于":
			i=1;
			j=Integer.parseInt(str6);
			break;
		case "等于":
			i=2;
			j=Integer.parseInt(str6);
			break;
		case "小于":
			i=3;
			j=Integer.parseInt(str6);
			break;
		}
		System.out.println("i="+i);
		boolean check=false;
		if(i>0&&j>=0) {
			List<dc_trade> wanted1=TradeDAO.getConprehensiveTrades(template, str1, str2, str3, str4, i, j, starttime, endtime);
			System.out.println("wanted:"+wanted1);
			if(wanted1!=null)
			{
				if(!wanted1.isEmpty())
					for(dc_trade temp:wanted1)
						try {
							response.getWriter().write("<tr><td>"+temp.getTid()+
						"</td><td>"+UserDAO.getUserByUid(WalletDAO.getWalletByWid(temp.getWid(), template).getUid(), template).getItcode()+
						"</td><td>"+UserDAO.getUserByUid(WalletDAO.getWalletByWid(temp.getWid(), template).getUid(), template).getUsername()+
						"</td><td>"+temp.getTip()+
						"</td><td>"+temp.getVolume()+
						"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				else
					check=true;
			}
			else
				check=true;
			if(check)
				try {
					response.getWriter().write("查询失败！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		} else if(i==0)
		{
			System.out.println("111");
			List<dc_trade> wanted=TradeDAO.getConprehensiveTrades(template, str1, str2, str3, str4, i, j, starttime, endtime);
			System.out.println("wanted:"+wanted);
			if(wanted!=null)
			{
				if(!wanted.isEmpty())
					for(dc_trade temp:wanted)
						try {
							response.getWriter().write("<tr><td>"+temp.getTid()+
						"</td><td>"+UserDAO.getUserByUid(WalletDAO.getWalletByWid(temp.getWid(), template).getUid(), template).getItcode()+
						"</td><td>"+UserDAO.getUserByUid(WalletDAO.getWalletByWid(temp.getWid(), template).getUid(), template).getUsername()+
						"</td><td>"+temp.getTip()+
						"</td><td>"+temp.getVolume()+
						"</td></tr>");
						} catch (IOException e) {
							// TODO 自动生成的 catch 块
							e.printStackTrace();
						}
				else
					check=true;
			}
			else
				check=true;
			if(check)
				try {
					response.getWriter().write("查询失败！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
		}
		else
			try {
				response.getWriter().write("查询失败！");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	/**
	 * 开启红包雨、查询红包雨是否开启过、查询红包雨发放记录
	 * @param response
	 * @param j 0查询红包雨是否开启过，1开启红包雨，2查询红包雨记录
	 * @param str1 j=1：开启红包雨轮次；j=4：选择红包雨轮次
	 * @param str2 j=4：对发放用户的限定：itcode、所有、姓名
	 * @param str3 j=4：itcode或者姓名
	 * @param str4 j=4：对金额的限定
	 * @param str5 j=4：具体金额
	 */
	@RequestMapping("/luckyrain1")
	public void luckyRain1(HttpServletResponse response, String j, String str1, String str2, String str3, String str4,
			String str5) {
		System.out.println(str2+","+str4);
		switch(j) {
		case "0":
			List<ln_record> temp1=LN_RecordDAO.getRecordsByround(1, template);
			List<ln_record> temp2=LN_RecordDAO.getRecordsByround(2, template);
			List<ln_record> temp3=LN_RecordDAO.getRecordsByround(3, template);
			try {
				if(!temp1.isEmpty())
				    response.getWriter().write("1");
				else
					response.getWriter().write("0");
				if(!temp2.isEmpty())
					response.getWriter().write("1");
				else
					response.getWriter().write("0");
				if(!temp3.isEmpty())
					response.getWriter().write("1");
				else
					response.getWriter().write("0");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			break;
		case "1":
			int round=0;
			round=Integer.parseInt(str1);
			if(round>0) {
				LuckyNumberThread lr=new LuckyNumberThread();
				lr.setRound(round);
				lr.setFlag(true);
				lr.setTemplate(template);
				lr.run();
				try {
					response.getWriter().write("开启红包雨成功！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			} else
				try {
					response.getWriter().write("开启红包雨失败！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			break;
		case "2":
			boolean end=false;
			if(str1.equals("0"))
			{
				List<ln_record> wanted=LN_RecordDAO.getAllRecords(template);
				if(str2.equals("0")&&str4.equals("0")) {
					System.out.println("nono");
					if(!wanted.isEmpty()) {
						for(ln_record temp:wanted) {
							int wid=temp.getWid();
							dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
							dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
							try {
								response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
							            user.getUsername()+"</td><td>"+temp.getLucky_number()
										+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
							} catch (IOException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
						}
					}
					else
						end=true;
				}else if(str2.equals("0")) {
					int num=Integer.parseInt(str5);
					if(!wanted.isEmpty()) {
						for(ln_record temp:wanted) {
							boolean judge=false;
							switch(str4) {
							case "1":
								if(temp.getLucky_number()>num)
									judge=true;
								break;
							case "2":
								if(temp.getLucky_number()==num)
									judge=true;
								break;
							case "3":
								if(temp.getLucky_number()<num)
									judge=true;
								break;
							}
							if(judge) {
								int wid=temp.getWid();
								dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
								dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
								try {
									response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
								            user.getUsername()+"</td><td>"+temp.getLucky_number()
											+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
							}
						}
					}
					else
						end=true;
				}else if(str4.equals("0")) {
					if(!wanted.isEmpty())
					{
						for(ln_record temp:wanted) {
							boolean judge=false;
							int wid=temp.getWid();
							dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
							dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
							switch(str2) {
							case "1":
								if(user.getItcode().equals(str3))
									judge=true;
								break;
							case "2":
								if(user.getUsername().equals(str3))
									judge=true;
								break;
							}
							if(judge)
								try {
									response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
								            user.getUsername()+"</td><td>"+temp.getLucky_number()
											+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
							}
					}else {
						end=true;
					}
				}else {
					int num=Integer.parseInt(str5);
					if(!wanted.isEmpty())
					{
						for(ln_record temp:wanted) {
							boolean judge1=false,judge2=false;
							int wid=temp.getWid();
							dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
							dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
							switch(str4) {
							case "1":
								if(temp.getLucky_number()>num)
									judge1=true;
								break;
							case "2":
								if(temp.getLucky_number()==num)
									judge1=true;
								break;
							case "3":
								if(temp.getLucky_number()<num)
									judge1=true;
								break;
							}
							switch(str2) {
							case "1":
								if(user.getItcode().equals(str3))
									judge2=true;
								break;
							case "2":
								if(user.getUsername().equals(str3))
									judge2=true;
								break;
							}
							if(judge1&&judge2)
								try {
									response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
								            user.getUsername()+"</td><td>"+temp.getLucky_number()
											+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
						}
					}else {
						end=true;
				}
			}
				}
			else {
				int r=Integer.parseInt(str1);
				List<ln_record> wanted=LN_RecordDAO.getRecordsByround(r, template);
				if(str2.equals("0")&&str4.equals("0")) {
					if(!wanted.isEmpty()) {
						for(ln_record temp:wanted) {
							int wid=temp.getWid();
							dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
							dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
							try {
								response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
							            user.getUsername()+"</td><td>"+temp.getLucky_number()
										+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
							} catch (IOException e) {
								// TODO 自动生成的 catch 块
								e.printStackTrace();
							}
						}
					}
					else
						end=true;
				}else if(str2.equals("0")) {
					int num=Integer.parseInt(str5);
					if(!wanted.isEmpty()) {
						for(ln_record temp:wanted) {
							boolean judge=false;
							switch(str4) {
							case "1":
								if(temp.getLucky_number()>num)
									judge=true;
								break;
							case "2":
								if(temp.getLucky_number()==num)
									judge=true;
								break;
							case "3":
								if(temp.getLucky_number()<num)
									judge=true;
								break;
							}
							if(judge) {
								int wid=temp.getWid();
								dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
								dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
								try {
									response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
								            user.getUsername()+"</td><td>"+temp.getLucky_number()
											+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
							}
						}
					}
					else
						end=true;
				}else if(str4.equals("0")) {
					if(!wanted.isEmpty())
					{
						for(ln_record temp:wanted) {
							boolean judge=false;
							int wid=temp.getWid();
							dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
							dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
							switch(str2) {
							case "1":
								if(user.getItcode().equals(str3))
									judge=true;
								break;
							case "2":
								if(user.getUsername().equals(str3))
									judge=true;
								break;
							}
							if(judge)
								try {
									response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
								            user.getUsername()+"</td><td>"+temp.getLucky_number()
											+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
							}
					}else {
						end=true;
					}
				}else {
					int num=Integer.parseInt(str5);
					if(!wanted.isEmpty())
					{
						for(ln_record temp:wanted) {
							boolean judge1=false,judge2=false;
							int wid=temp.getWid();
							dc_wallet wallet=WalletDAO.getWalletByWid(wid, template);
							dc_user user=UserDAO.getUserByUid(wallet.getUid(), template);
							switch(str4) {
							case "1":
								if(temp.getLucky_number()>num)
									judge1=true;
								break;
							case "2":
								if(temp.getLucky_number()==num)
									judge1=true;
								break;
							case "3":
								if(temp.getLucky_number()<num)
									judge1=true;
								break;
							}
							switch(str2) {
							case "1":
								if(user.getItcode().equals(str3))
									judge2=true;
								break;
							case "2":
								if(user.getUsername().equals(str3))
									judge2=true;
								break;
							}
							if(judge1&&judge2)
								try {
									response.getWriter().write("<tr><td>" +temp.getRound()+ "</td><td>" +user.getItcode()+ "</td><td>" +
								            user.getUsername()+"</td><td>"+temp.getLucky_number()
											+ "</td><td>" +temp.getTradetime()+ "</td></tr>");
								} catch (IOException e) {
									// TODO 自动生成的 catch 块
									e.printStackTrace();
								}
						}
			}
			if(end)
				try {
					response.getWriter().write("查询红包雨记录失败！");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			break;
		}}}
	}
	/**
	 * 用户查询
	 * @return
	 */
	@RequestMapping("/adminselcectusers")
	public void Adminselectusers(String selecttype,String itcode,String username,String balencetype,String balence,HttpServletResponse response) {
		System.out.println(selecttype); 
		List<dc_user_wallet> userwallts=new ArrayList<dc_user_wallet>();
		if(selecttype=="所有") 
		{ 
			userwallts=UserWalletDAO.getAllWallInfoByUser(template);
			for(dc_user_wallet userwallet: userwallts) {
				String active="激活";
				if(userwallet.getUid()==0)
				{
					active="未激活";
				}
				String s="<tr><td>"+userwallet.getItcode()+"</td><td>"+userwallet.getUsername()+"</td><td>"+active+"</td><td>"+userwallet.getAmount()+"</td></tr>";
				try {
					response.getWriter().write(s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}else if(selecttype=="itcode") {
			
		}else if(selecttype=="用户名") {
			
		}else if(selecttype=="余额") {
			
		}else {
			System.out.println("类型选择出错！！！");
		}
		

		
		System.out.println(itcode);
		System.out.println(username);
		System.out.println(balencetype);
		System.out.println(balence);
		
	}
	
	
	
}
	


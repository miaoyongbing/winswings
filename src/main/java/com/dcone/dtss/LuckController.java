package com.dcone.dtss;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcone.dtss.dao.LuckDAO;
import com.dcone.dtss.dao.LuckRecordDAO;
import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.*;

@Controller
public class LuckController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	/**
	 * 进入抢红包页面
	 * @return
	 */
	@RequestMapping("/luck")
	public String luck(HttpSession session,Model model) {
		String face=(String) session.getAttribute("face")+"?"+Math.random();
		String itcode=(String)session.getAttribute("itcode");
		int uid=UserDAO.getUserByItcode(itcode, jdbcTemplate).getUid();
		int money=WalletDAO.getMoney(uid, jdbcTemplate);
		if(money==-1) {
			model.addAttribute("money","未激活钱包");
		}else {
			model.addAttribute("money",money);
		}
		return "RedWars";
	}
	/**抢红包
	 * 后期itcode应当从session中获取
	 * @param itcode 用户员工号
	 * @param lid 红包账户id
	 * @param model
	 * @return 0没抢到红包，大于0则抢到红包
	 */
	@RequestMapping("/getluck")
	public void getluck(String lid,Model model,HttpSession session,PrintWriter out) {
		String itcode=(String) session.getAttribute("itcode");
		System.out.println("获取itcode"+itcode);
		int i=0;
		int lid1=jdbcTemplate.queryForInt(" select count(*) from luck;");
		
		dc_wallet temp=WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		System.out.println(lid);
		int judge=-1;
		lid="2";//for test
		for(int j=1;j<=lid1;j++)
		{
			i=j;
			luck l=LuckDAO.getLuckByLid(i, jdbcTemplate);
			System.out.println(l.toString());
			int result=LuckDAO.getLuckMoney(l.getLid(), temp, jdbcTemplate);
			if(result==1)	
			{
				judge=LuckRecordDAO.getRecordByLidWid(i, temp.getWid(), jdbcTemplate).getVolume();
				break;
			}
			if(result==-2)
				judge=0;
		}
		out.print(judge);
//		out.print(str1);
//		model.addAttribute("str1",str1);
//		model.addAttribute("str2",str2);
//		return "getluck";
	}
	
	/**
	 * 红包实时广播
	 * @return
	 */
	@RequestMapping(value="/luckbroadcast", method=RequestMethod.GET)
	@ResponseBody
	public List luckbroadcast() {
		List<luckrecord> records = LuckDAO.getLuckRecordsMax(100, jdbcTemplate);
		if(records==null) {
			return null;
		}else {
			List<dc_user_wallet> user_amounts=new ArrayList<dc_user_wallet>();
			for(luckrecord record :records) {
				dc_wallet wallet=WalletDAO.getWalletByWid(record.getWid(), jdbcTemplate);
				dc_user user=UserDAO.getUserByUid(wallet.getUid(), jdbcTemplate);
				dc_user_wallet user_amount=new dc_user_wallet();
				user_amount.setUsername(user.getUsername());
				user_amount.setAmount(record.getVolume());
				user_amounts.add(user_amount);
			}
			System.out.println("钱包广播"+user_amounts.toString());
			return user_amounts;
		}	
	}
	
	/**
	 * 给某个用户发红包
	 * @param itcode
	 * @param money
	 * @param session
	 * @param model
	 * @param out -1 没有该用户  -2该用户钱包未激活 -3充值失败   返回充值信息，成功
	 */
	@RequestMapping(value="/sendpersonluck", method=RequestMethod.GET)
	public void SendPersonLuck(String itcode, int money,Locale locale, HttpSession session,Model model,PrintWriter out) {
		String itcodef=(String) session.getAttribute("itcode");
		dc_user userf=UserDAO.getUserByItcode(itcodef, jdbcTemplate);
		dc_wallet walletf=WalletDAO.getWalletByItcode(itcodef, jdbcTemplate);
		dc_user user=UserDAO.getUserByItcode(itcode, jdbcTemplate);
		if(user==null) {
			System.out.println("返回没有该用户");
			out.print(-1);
		}else {
			//钱包的余额的增加和减少
			int money1=WalletDAO.getMoney(user.getUid(), jdbcTemplate);
			if(money1==-1) {
				System.out.println("返回-2");
				out.print(-2);
			}else {
				dc_wallet wallet =WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
				String formattedDate=df.format(new Date());
				boolean i=TradeDAO.createTrade(formattedDate,walletf.getWid(), money, "给"+user.getUsername()+"发的红包", jdbcTemplate);
				int re=TradeDAO.createTrade(wallet.getWid(), formattedDate, money, "来自"+userf.getUsername()+"的红包", jdbcTemplate);
				int j=WalletDAO.walletMinusByItcode(itcodef, money, jdbcTemplate);
				if(re*j==1&&i) {
					System.out.println("返回成功");
					out.print("给"+user.getUsername()+"发红包成功");
				}else {
					System.out.println("返回失败");
					out.print(-3);
				}
			}
			
		}
		
		
	}
}

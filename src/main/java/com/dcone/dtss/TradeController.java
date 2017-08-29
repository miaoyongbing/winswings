package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dcone.dtss.dao.LuckRecordDAO;
import com.dcone.dtss.dao.P_TradeDAO;
import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_wallet;
import com.dcone.dtss.model.luckrecord;
import com.dcone.dtss.model.p_trade;
import com.dcone.dtss.model.record;

@Controller
/**
 * 
 * @author wrs
 *交易处理控制器
 */
public class TradeController {
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	/**
	 * 查询交易记录
	 * @return 查询结果界面
	 */
	@RequestMapping(value="/trade_check")
	public void tradeCheck(String judge1, String date,
			Model model, HttpSession session,HttpServletResponse response) {
		int judge=0;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");

		judge=Integer.parseInt(judge1);
		String itcode=(String) session.getAttribute("itcode");
		dc_wallet wallet=WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		TradeDAO trade=new TradeDAO();
		List<record> records=new ArrayList<record>();
		switch(judge) {
		case 1:
			List<dc_trade> trade1=trade.getTradesByItcode(itcode, jdbcTemplate);
			for(dc_trade temp:trade1) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		case 2:
			List<dc_trade> trade2=TradeDAO.getTradesByTip(wallet.getWid(), "红包", jdbcTemplate);
			System.out.println("trade:"+trade2.toString());
			for(dc_trade temp:trade2) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		case 3:
			List<dc_trade> trade3=TradeDAO.getTradesByTip(wallet.getWid(), "充值", jdbcTemplate);
			List<dc_trade> trade31=TradeDAO.getTradesByTip(wallet.getWid(), "提现", jdbcTemplate);
			for(dc_trade temp:trade3) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			for(dc_trade temp:trade31) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		case 4:
			List<dc_trade> trade4=TradeDAO.getTradesByTip(wallet.getWid(), "打赏", jdbcTemplate);
			for(dc_trade temp:trade4) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		}
		if(records!=null)
			for(record t:records) {
				try {
					response.getWriter().write("<tr><td>"+t.getTid()+"</td><td>"+t.getTip()+"</td><td>"+t.getVolume()+"</td><td>"+t.getTradetime()+"</td></tr>");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
	}
	/**
	 * 查询指定时间内指定类型记录
	 * @param judge1 交易类型
	 * @param start 开始时间
	 * @param end 结束时间
	 * @param session session信息
	 * @param response 返回网页信息
	 */
	@RequestMapping(value="/trade_check_time",method=RequestMethod.GET)
	public void checkTradeTime(String judge1,String start,String end,HttpSession session,HttpServletResponse response) {
		String starttime=start+" 00:00:00",endtime=end+" 23:59:59";
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");

		System.out.println("judge1:"+judge1+",start:"+starttime+",end:"+endtime);
		int judge=0;
		judge=Integer.parseInt(judge1);
		String itcode=(String) session.getAttribute("itcode");
		dc_wallet wallet=WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		TradeDAO trade=new TradeDAO();
		List<record> records=new ArrayList<record>();
		switch(judge) {
		case 1:
			List<dc_trade> trade1=TradeDAO.getTimeTipTradesByWid(wallet.getWid(), starttime, endtime, jdbcTemplate, "");
			for(dc_trade temp:trade1) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		case 2:
			List<dc_trade> trade2=TradeDAO.getTimeTipTradesByWid(wallet.getWid(), starttime, endtime, jdbcTemplate, "红包");
			System.out.println("trade:"+trade2.toString());
			for(dc_trade temp:trade2) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		case 3:
			List<dc_trade> trade3=TradeDAO.getTimeTipTradesByWid(wallet.getWid(), starttime, endtime, jdbcTemplate, "充值");
			List<dc_trade> trade31=TradeDAO.getTimeTipTradesByWid(wallet.getWid(), starttime, endtime, jdbcTemplate, "提现");
			for(dc_trade temp:trade3) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			for(dc_trade temp:trade31) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		case 4:
			List<dc_trade> trade4=TradeDAO.getTimeTipTradesByWid(wallet.getWid(), starttime, endtime, jdbcTemplate, "打赏");
			for(dc_trade temp:trade4) {
				record r=new record();
				r.setTid(temp.getTid());
				r.setTip(temp.getTip());
				r.setTradetime(temp.getTradetime());
				r.setVolume(temp.getVolume());
				records.add(r);
			}
			break;
		}
		if(records!=null)
			for(record t:records) {
				try {
					response.getWriter().write("<tr><td>"+t.getTid()+"</td><td>"+t.getTip()+"</td><td>"+t.getVolume()+"</td><td>"+t.getTradetime()+"</td></tr>");
				} catch (IOException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
	}
}

//String starttime=date+" 00:00:00",endtime=date+" 23:59:59";
////UserDAO.unlockUserByItcode(itcode, jdbcTemplate);
///*if(UserDAO.isLock(itcode, jdbcTemplate))
//	System.out.println("unlock failed!");
//else
//	System.out.println("unlock successed!");*/
//System.out.println("starttime is:"+starttime+" and endtime is:"+endtime);
//TradeDAO td=new TradeDAO();
//List<dc_trade> wanted=td.getTimeTradesByItcode(itcode, starttime, endtime, jdbcTemplate);
//if(wanted!=null) {
//	for(dc_trade temp:wanted) 
//		System.out.println(temp.toString());
//	model.addAttribute("list",wanted);
//	model.addAttribute("result", "查询成功！，以下是您"+date+"的交易记录：");
//	model.addAttribute("str1", "交易流水号");
//	model.addAttribute("str2", "钱包id");
//	model.addAttribute("str3", "转入金额");
//	model.addAttribute("str4", "交易时间");
//	model.addAttribute("str5","备注");
//}
//return "trade_check_result";

package com.dcone.dtss;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.TradeDAO;
import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.dc_trade;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.dc_wallet;

import form.WalletForm;
/**
 * 
 * @author wrs
 *钱包账户操作
 */
@Controller
public class BalanceController {
	
	private static final Logger logger = LoggerFactory.getLogger(BalanceController.class);
	@Autowired
    JdbcTemplate jdbcTemplate;
	/**
	 * 进入充值页面
	 * @return
	 */
	@RequestMapping(value="/balance_add", method=RequestMethod.GET)
	public String balanceAdd() {
		return "balance_add";
	}

	/**
	 * 进入红包界面
	 * @return
	 */
	@RequestMapping(value="/wallet", method=RequestMethod.GET)
	public String wallet(HttpSession session,Model model) {
		String face=(String) session.getAttribute("face")+"?"+Math.random();
		String itcode=(String)session.getAttribute("itcode");
		int uid=UserDAO.getUserByItcode(itcode, jdbcTemplate).getUid();
		int money=WalletDAO.getMoney(uid, jdbcTemplate);
		if(money==-1) {
			model.addAttribute("money","未激活钱包");
		}else {
			model.addAttribute("money",money);
		}
		model.addAttribute("itcode",itcode);
		model.addAttribute("face",face);
		return "myWallet";
	}
	
	/**
	 * 进行充值操作
	 * @param response
	 * @param money 充值金额
	 * @param session session信息
	 * @param locale
	 */
	@RequestMapping(value="/balance_adding")
	//public String balanceAdding(@Valid WalletForm walletForm ,BindingResult bindingResult ,Locale locale,  Model model) {
	//if (bindingResult.hasErrors()) {
	public void balanceAdding(HttpServletResponse response,String money,HttpSession session,Locale locale) {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");

		String itcode=(String) session.getAttribute("itcode");
		String name=UserDAO.getUserByItcode(itcode, jdbcTemplate).getUsername();
		logger.info("itcode:" +itcode +"username:"+UserDAO.getUserByItcode(itcode, jdbcTemplate).getUsername() + " 充值 "+ money);
		logger.info(jdbcTemplate.toString());
		int i=0;
		i=Integer.parseInt(money);
		int judge =0;
		if(i>0)
		judge=WalletDAO.balance_add(itcode,name, i,locale, jdbcTemplate);
		if (judge == 1) {
			try {
				response.getWriter().write("充值成功");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		} else {
			try {
				response.getWriter().write("充值失败！");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
	/**
	 * 提现操作
	 * @param session
	 * @param response
	 * @param num 提现金额
	 */
	@RequestMapping(value="/balance_m")
	public void balance_m(HttpSession session,HttpServletResponse response,String num) {
		int money=0;
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");

		money=Integer.parseInt(num);
		String itcode=(String) session.getAttribute("itcode");
		boolean judge=WalletDAO.getMoney(UserDAO.getUserByItcode(itcode, jdbcTemplate).getUid(), money, jdbcTemplate);
		if(judge)
			try {
				response.getWriter().write("提现成功！");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		else
			try {
				response.getWriter().write("提现失败！");
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
	}
	/**
	 * 激活钱包
	 * @return true 激活成功，false 激活失败
	 */
	@RequestMapping(value="/initwallet", method=RequestMethod.GET)
	public void initwallet(String itcode,Model model,PrintWriter out) {
		System.out.println("激活钱包");
		boolean i=WalletDAO.initWallet(itcode, jdbcTemplate);
		if(i) {
			out.print("true");
		}else {
			out.print("false");
		}
			}
	/**
	 * 查看钱包余额
	 * @return
	 */
	@RequestMapping(value="/checkwallet", method=RequestMethod.GET)
	public void checkwallet(String itcode,Model model,PrintWriter out) {
		System.out.println("检查钱包");
		dc_wallet wallet=WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
		if(wallet!=null) {
			out.print(wallet.getAmount());
		}else {
			out.print("false");
		}
	}
	
	
}

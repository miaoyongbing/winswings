package com.dcone.dtss;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 主页操作
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * 跳转登录页面
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String login(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		logger.info(formattedDate);
		return "login";
	}
	
	/**
	 * 跳转主页
	 */
	@RequestMapping(value = "home", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		logger.info(formattedDate);
		return "home";
	}
	
	@RequestMapping(value = "/luckymoney", method = RequestMethod.GET)
	public String lucymoeny(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		logger.info(formattedDate);
		return "luckymoney";
	}
	@RequestMapping(value = "/thanks", method = RequestMethod.GET)
	public String thanks(Locale locale, Model model) {
		
		return "thanks";
	}
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.setAttribute("itcode", null);
		session.setAttribute("username", null);
		return "login";
	}
	@RequestMapping(value = "/perinfo", method = RequestMethod.GET)
	public String perinfo(Locale locale, Model model) {
		
		return "perinfo";
	}
	
	@RequestMapping(value = "/ours", method = RequestMethod.GET)
	public String ours(Locale locale, Model model) {	
		return "ours";
	}
	
	@RequestMapping(value = "/chatting", method = RequestMethod.GET)
	public String chatting(Locale locale, Model model) {
		
		return "chatting";
	}
}

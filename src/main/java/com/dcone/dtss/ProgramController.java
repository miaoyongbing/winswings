package com.dcone.dtss;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dcone.dtss.dao.P_PocketDAO;
import com.dcone.dtss.dao.P_TradeDAO;
import com.dcone.dtss.dao.ProgramDAO;
import com.dcone.dtss.dao.WalletDAO;
import com.dcone.dtss.model.*;

/**
 * 
 * @author wrs
 *
 */
@Controller
public class ProgramController {
	@Autowired
	JdbcTemplate jdbcTemplate;
	/**
	 * 显示节目列表，可以打赏
	 * @return
	 */
	@RequestMapping("/perform")
	public String program(@RequestParam(value="c",defaultValue="0")String c,HttpSession session,String select,String text2,Model model) {
		int judge=Integer.parseInt(c);
		System.out.println("select is "+select);
		if(judge==1)
		{
			if(select!=null&&text2!=null/*&&session!=null*/)
			{
				int i=Integer.parseInt(text2);
				String itcode=(String) session.getAttribute("itcode");
				//String itcode="000001";
				if(itcode!=null) {
					dc_wallet wallet=WalletDAO.getWalletByItcode(itcode, jdbcTemplate);
					if(wallet!=null) {
					Date date = new Date();
					String url1 = " yyyy-MM-dd";
					String url2 = " HH:mm:ss";
					SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
					SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
					String time = fmtDate1.format(date) + fmtDate2.format(date);
					System.out.println("hello"+itcode);
					int result=P_PocketDAO.getMoney(select, i, wallet.getWid(), time, jdbcTemplate);
					System.out.println(result);
					if (result == 1)
						model.addAttribute("c", "2");
					else
						model.addAttribute("c", "3");
					}else {
						model.addAttribute("c", "4");
					}
				}
			}
			else {
				model.addAttribute("c", "3");
			}
		}
		System.out.println("hekkkk======");
		List<program> result=ProgramDAO.getAllProgram(jdbcTemplate);
		List<programtotal> wanted =new ArrayList<programtotal>();
		for(program temp:result) {
			System.out.println("hekkkk");
			programtotal q=new programtotal();
			q.setPid(temp.getPid());
			q.setP_name(temp.getP_name());
			q.setActor(temp.getActor());
			q.setDepartment(temp.getDepartment());
			q.setAmount(P_PocketDAO.getTotal(temp.getPid(), jdbcTemplate));
			q.setRank(P_PocketDAO.getRank(temp.getPid(), jdbcTemplate));
			wanted.add(q);
			System.out.println(q.toString());
		}
		model.addAttribute("list",wanted);
		return "perform";
	}
	/**
	 * 处理打赏（需修改）
	 * @param volume 打赏金额
	 * @param pid 节目账户id
	 * @param wid 用户钱包id（后期应当改为从session中获取用户名，从而获取用户钱包）
	 * @param model
	 * @return
	 */
	@RequestMapping("/programmoney")
	public String programmoney(String volume,String pid,String wid,Model model) {
		int i=0,j=0,k=0;
		String str1="",str2="";
		if(volume!=null&&pid!=null&&wid!=null) {
			i=Integer.parseInt(volume);
			j=Integer.parseInt(pid);
			k=Integer.parseInt(wid);
			p_pocket temp=P_PocketDAO.getPocketById(j, jdbcTemplate);
			Date date = new Date();
			String url1 = " yyyy-MM-dd";
			String url2 = " HH:mm:ss";
			SimpleDateFormat fmtDate1 = new SimpleDateFormat(url1);
			SimpleDateFormat fmtDate2 = new SimpleDateFormat(url2);
			String time = fmtDate1.format(date) + fmtDate2.format(date);
			int result=P_PocketDAO.getMoney(temp.getP_name(), i, k, time, jdbcTemplate);
			System.out.println(result);
			if(result==1)
			{
				str1="打赏成功！";
				str2="您打赏的金额为:"+P_TradeDAO.getTrade(j, k, time, jdbcTemplate).getVolume();
			}
		}
		else {
			str1="打赏失败";
			str2="您输入的信息有误！";
		}
		model.addAttribute("str1", str1);
		model.addAttribute("str2", str2);
		model.addAttribute("temp", 1);
		return"programmoney";
	}
	
	@RequestMapping("/programlist")
	public String programlist(Model model) {
		int i=ProgramDAO.getRank(1, jdbcTemplate);
		model.addAttribute("i", i);
		return "programlist";
	}
}

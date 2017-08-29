package com.dcone.dtss;

import java.awt.Color;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dcone.dtss.dao.UserDAO;
import com.dcone.dtss.model.dc_user;
import com.dcone.dtss.model.chat;

/**
 * 与用户相关的操作
 * 
 * @author wrs 此controller用于处理个人信息的修改
 */
@Controller
public class UserController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	JdbcTemplate jdbcTemplate;

	/**
	 * 修改用户密码
	 * 
	 * @param text1
	 *            旧的密码
	 * @param text2新的密码
	 * @param model模型
	 * @param sessionsession信息
	 * @return 结果页面
	 */
	@RequestMapping(value = "/changepassword", method = RequestMethod.GET)
	public void ChangePassword(String text1, String text2, Model model, HttpSession session, PrintWriter out) {
		int res = -1;
		if (text1 != null && text2 != null) {
			System.out.println("itcode=" + (String) session.getAttribute("itcode"));
			boolean judge = UserDAO.changePassword((String) session.getAttribute("itcode"), text1, text2, jdbcTemplate);
			if (judge) {
				res = 1;
			} else {
				res = 0;
			}
		}

		System.out.println(res);
		out.print(res);
	}

	/**
	 * 修改用户名
	 * 
	 * @param text1
	 *            新用户名
	 * @param model
	 *            模型
	 * @param session
	 *            session信息
	 * @return 结果页面
	 */
	@RequestMapping(value = "/changeusername", method = RequestMethod.GET)
	public void ChangeUsername(String text1, Model model, HttpSession session, PrintWriter out) {
		String str1 = "", str2 = "";
		if (text1 != null) {
			String itcode = (String) session.getAttribute("itcode");
			System.out.println("success" + text1);
			boolean judge = UserDAO.changeUsername(itcode, text1, jdbcTemplate);
			if (judge) {
				str1 = "修改用户名成功！";
				str2 = "欢迎您，" + text1;
				session.setAttribute("username", text1);
			} else {
				str1 = "修改用户名失败！";
			}
		}
		model.addAttribute("str1", str1);
		model.addAttribute("str2", str2);
		out.println(str1);
	}

	/**
	 * 获取用户头像
	 * 
	 * @param text新用户名
	 * @param model模型
	 * @param sessionsession信息
	 * @return 结果页面
	 */
	@RequestMapping(value = "/getuserportrait", method = RequestMethod.GET)
	public void GetUserPortraite(Model model, HttpSession session, PrintWriter out) {
		String itcode = (String) session.getAttribute("itcode");
		System.out.println("获取itcode" + itcode);
		dc_user user = UserDAO.getUserByItcode(itcode, jdbcTemplate);
		out.println(user.getPortrait() + "?" + Math.random());
	}

	/**
	 * 用户验证登陆
	 * 
	 * @param itcode
	 * @param password
	 * @param request
	 * @param model
	 * @param session
	 * @param out
	 *            返回 true 普通用户，admin 超级管理员，false 密码或itcode错误
	 */
	@RequestMapping(value = "/validuser", method = RequestMethod.GET)
	public void validuser(String itcode, String password, HttpServletRequest request, Model model, HttpSession session,
			PrintWriter out) {
		ServletContext application = request.getServletContext();
		System.out.println(itcode);
		System.out.println(password);
		UserDAO userdao = new UserDAO();
		dc_user user = userdao.verify(itcode, password, jdbcTemplate);

		if (user == null) {
			out.println("false");
		} else {
			session.setAttribute("itcode", itcode);
			session.setAttribute("username", user.getUsername());
			session.setAttribute("face", user.getPortrait());
			session.setMaxInactiveInterval(900);

			if (application.getAttribute("OnlineUsers") == null) {
				ArrayList<dc_user> users = new ArrayList<dc_user>();
				dc_user temp = new dc_user();
				temp.setPortrait(user.getPortrait());
				temp.setUsername(user.getUsername());
				temp.setItcode(itcode);
				users.add(temp);
				application.setAttribute("OnlineUsers", users);
			} else { 
				ArrayList<dc_user> users = (ArrayList<dc_user>) application.getAttribute("OnlineUsers");
				dc_user temp = new dc_user();
				temp.setUsername(user.getUsername());
				temp.setPortrait(user.getPortrait());

				users.add(temp);
				application.setAttribute("OnlineUsers", users);
			}

			if (itcode.equals("000000")) {
				out.write("admin");

			} else {
				out.write("true");
			}
		}
	}

	/**
	 * 添加用户
	 * 
	 * @param itcode
	 * @param username
	 * @param password
	 * @param model
	 * @param session
	 * @param out
	 *            1成功，-2插入失败，-1员工号或用户名重复，0连接失败
	 */
	@RequestMapping(value = "/AddUser", method = RequestMethod.GET)
	public void Adduser(String itcode, String username, String password, Model model, HttpSession session,
			PrintWriter out) {
		System.out.println("注册" + itcode + username + password);
		int result = UserDAO.createUser(itcode, username, password, jdbcTemplate);
		out.print(result);
	}

	/**
	 * 验证码校验
	 * 
	 * @param valicode
	 * @param req
	 * @param resp
	 * @param model
	 * @param out
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/Valicode", method = RequestMethod.GET)
	public void Valicode(String valicode, HttpServletRequest req, HttpServletResponse resp, Model model,
			PrintWriter out) throws UnsupportedEncodingException {
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=UTF-8;pageEncoding=UTF-8");
		HttpSession session = req.getSession();
		String answer = (String) session.getAttribute("answer");
		valicode = valicode.toUpperCase();
		System.out.print("验证码" + valicode);
		if (valicode.equals(answer)) {
			out.print(true);
			System.out.print("验证码正确");
		} else {
			System.out.print("验证码错误");
			out.print(false);
		}
	}

	/**
	 * 检查itcode是否可以注册
	 * 
	 * @param itcode
	 * @param req
	 * @param resp
	 * @param model
	 * @param out
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/Checkitcode", method = RequestMethod.GET)
	public void Checkitcode(String itcode, HttpServletRequest req, HttpServletResponse resp, Model model,
			PrintWriter out) throws UnsupportedEncodingException {

		UserDAO userdao = new UserDAO();
		if (userdao.checkItcode(itcode, jdbcTemplate)) {
			System.out.println("可以注册" + itcode);
			out.print(true);

		} else {
			System.out.println("用户已经存在");
			out.print(false);
		}
	}

	/**
	 * 图片上传
	 * 
	 * @param itcode
	 * @param request
	 * @param response
	 * @param model
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void Upload(String itcode, HttpServletRequest request, HttpServletResponse response, Model model)
			throws UnsupportedEncodingException {
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		if (isMultipart) {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			File repository = new File("F:\\temp");
			factory.setRepository(repository);
			ServletFileUpload upload = new ServletFileUpload(factory);
			try {
				HttpSession session1 = request.getSession();

				itcode = session1.getAttribute("itcode").toString();
				List<FileItem> items = upload.parseRequest(request);
				for (int i = 0; i < items.size(); i++) {
					FileItem item = items.get(i);
					String name = item.getName();
					if (name != "") {
						String str = request.getSession().getServletContext().getRealPath("/img/");
						item.write(new File("..\\redpocket\\src\\main\\webapp\\img\\" + itcode + ".png"));
						System.out.println(str);
						item.write(new File(str + itcode + ".png"));
						UserDAO user = new UserDAO();
						int num = user.setPortrait(itcode, "img/" + itcode + ".png", jdbcTemplate);
						if (num == 1) {
							System.out.println("success");
							response.getWriter().write("success");
						}

					}
				}
			} catch (FileUploadException e) {
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private static final long serialVersionUID = 1L;
	public static final Random random = new Random();
	public static final String[] CHARS = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O",
			"P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9" };

	public static Color getRandomColor() {
		return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
	}

	public static Color getReverseColor(Color c) {
		return new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue());
	}

	@RequestMapping(value = "/Code", method = RequestMethod.GET)
	public void code(HttpServletRequest request, HttpServletResponse response, Model model, HttpSession session) {
		response.setContentType("image/jpeg");
		response.addHeader("Cache_Control", "no-cache");
		response.addHeader("Pragma", "no-cache");
		response.addDateHeader("Expires", 0);
		String a;
		String b;
		String c;
		String d;
		Random r = new Random();
		a = CHARS[r.nextInt(36)];
		b = CHARS[r.nextInt(36)];
		c = CHARS[r.nextInt(36)];
		d = CHARS[r.nextInt(36)];
		String answer = a + b + c + d;
		session.setAttribute("answer", answer);
		BufferedImage img = new BufferedImage(100, 30, BufferedImage.TYPE_INT_RGB);
		java.awt.Graphics g = img.getGraphics();
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 22));

		Color color = getRandomColor();
		Color reverse = getReverseColor(color);

		g.setColor(color);
		g.fillRect(0, 0, 100, 30);
		g.setColor(reverse);
		g.drawString(a + b + c + d, 20, 20);
		try {
			ImageIO.write(img, "jpeg", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

package com.dcone.dtss.dao;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.dcone.dtss.model.program;
/**
 * 
 * @author wrs
 *处理节目列表操作
 */
public class ProgramDAO {
	/**
	 * 通过id获取节目
	 * @param pid 节目id
	 * @param jdbcTemplate
	 * @return 指定节目
	 */
	public static program getProgramByPid(int pid,JdbcTemplate jdbcTemplate) {
		RowMapper<program> pmapper=new BeanPropertyRowMapper<program>(program.class);
		try {
			program wanted=jdbcTemplate.queryForObject("select * from program where pid=?", pmapper,new Object[] {pid});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据id获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 根据节目名获取节目
	 * @param name 节目名
	 * @param jdbcTemplate
	 * @return 节目名
	 */
	public static program getProgramByName(String name,JdbcTemplate jdbcTemplate) {
		RowMapper<program> pmapper=new BeanPropertyRowMapper<program>(program.class);
		try {
			program wanted=jdbcTemplate.queryForObject("select * from program where p_name=?", pmapper,new Object[] {name});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据节目名获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 查询演员参演节目数
	 * @param name 演员名
	 * @param jdbcTemplate
	 * @return 参演节目数
	 */
	public static int checkActor(String name,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.queryForInt("select count(*) from program where actor=?",new Object[] {name});
			return i;
		}catch(Exception e) {
			System.out.println("获取该表演者参演的节目数失败!");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 根据演员名获取所有节目
	 * @param name 演员名
	 * @param jdbcTemplate
	 * @return 演员参演所有节目
	 */
	public static List<program> getPocketsByActor(String name,JdbcTemplate jdbcTemplate){
		RowMapper<program> pmapper=new BeanPropertyRowMapper<program>(program.class);
		try {
			List<program> wanted=jdbcTemplate.query("select * from program where actor=?", pmapper,new Object[] {name});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据演员名获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 获取部门参演的所有节目
	 * @param name 部门名
	 * @param jdbcTemplate
	 * @return 部门参演所有节目
	 */
	public static List<program> getPocketsByDepartment(String name,JdbcTemplate jdbcTemplate){
		RowMapper<program> pmapper=new BeanPropertyRowMapper<program>(program.class);
		try {
			List<program> wanted=jdbcTemplate.query("select * from program where department=?", pmapper,new Object[] {name});
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("根据参演单位获取节目失败！");
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 添加节目
	 * @param pname 节目名
	 * @param actor 表演者
	 * @param depart 参演单位
	 * @param jdbcTemplate
	 * @return 1成功，0失败
	 */
	public static int addProgram(String pname,String actor,String depart,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.update("insert into program(p_name,actor,department) values(?,?,?);",new Object[] {pname,actor,depart});
			if(i>0)
				return 1;
		}catch(Exception e) {
			System.out.println("添加节目失败！");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取排名
	 * @param pid 节目id
	 * @param jdbcTemplate
	 * @return 节目排名
	 */
	public static int getRank(int pid,JdbcTemplate jdbcTemplate) {
		try {
			int i=jdbcTemplate.queryForInt("select rank from programrank where pid=?;",new Object[] {pid});
			if(i>0)
				return i;
		}catch(Exception e) {
			System.out.println("获取排名失败");
			e.printStackTrace();
		}
		return 0;
	}
	/**
	 * 获取全部节目
	 * @param jdbcTemplate
	 * @return 全部节目
	 */
	public static List<program> getAllProgram(JdbcTemplate jdbcTemplate){
		RowMapper<program> pmapper=new BeanPropertyRowMapper<program>(program.class);
		try {
			List<program> wanted=jdbcTemplate.query("select * from program;", pmapper);
			if(wanted!=null)
				return wanted;
		}catch(Exception e) {
			System.out.println("获取全部节目失败！");
			e.printStackTrace();
		}
		return null;
	}
}

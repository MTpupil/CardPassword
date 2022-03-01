package com.mtpupil.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtpupil.pojo.Code;
import com.mtpupil.pojo.User;
import com.mtpupil.service.CodeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 眼盛星河 心向远方
 * The eyes are full of stars,and the heart yearns for the distance.
 *
 * @author 木瞳
 * on 2022-01-18  13:30
 */

@Controller
public class CodeController {
	
	@Autowired
	CodeServiceImpl codeService;
	
	//登录
	@RequestMapping("/login")
	public String index() {
		return "login";
	}
	
	//注销登录
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("user");
		return "login";
	}
	
	//查询卡密总数
	@RequestMapping("/welcome.html")
	public String welcome(Model model) {
		model.addAttribute("num", codeService.getCodeNum());
		return "welcome";
	}
	
	//生成并添加卡密
	@RequestMapping("/addCode")
	public String addCode(Model model, int number, int length, Integer codeTime, String firstCode) {
		
		List codeList = new ArrayList();
		String codeType = "";
		switch (codeTime) {
			case 7200:
				codeType = "测试卡";
				break;
			case 86400:
				codeType = "天卡";
				break;
			case 604800:
				codeType = "周卡";
				break;
		}
		//随机生成卡密
		StringBuilder result = new StringBuilder(firstCode);
		Random random = new Random();
		//随机生成卡密
		String[] str = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s",
				"t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9", "0"};//卡密字符集
		for (int i = 0; i < number; i++) {
			for (int j = 0; j < length; j++) {
				result.append(str[random.nextInt(36)]);//循环拼接
			}
			Code code = new Code(result.toString(), codeTime, codeType);
			codeList.add(code);
			result = new StringBuilder(firstCode);
		}
		
		codeService.addCode(codeList);
		model.addAttribute("msg", "添加成功");
		return "redirect:order-add.html";
	}
	
	//验证登录，防止直接访问
	@GetMapping({"/index", "/"})
	public String admin(HttpSession session, Model model) {
		if (session.getAttribute("user") == null) {
			model.addAttribute("msg", "你还没有登录，请先登录");
			return "login";
		} else {
			return "index";
		}
	}
	
	
	//查询显示所有卡密
	@RequestMapping(value = "/order-list.html/{pageNum}")
	public String toCodeList(Model model, @PathVariable int pageNum) {
		
		PageHelper.startPage(pageNum, 10);
		
		//查询用户数据
		List<Code> codeList = codeService.queryAllCode();
		
		//把用户数据封装到PageInfo分页结果对象
		PageInfo<Code> page = new PageInfo<>(codeList);
		
		//取出PageInfo的属性
		//PageInfo包含了非常全面的分页属性
		//System.out.println("当前页码="+page.getPageNum());
		//System.out.println("每页显示条数="+page.getPageSize());
		//System.out.println("当前页起始行号="+page.getStartRow());
		//System.out.println("当前页结束行号="+page.getEndRow());
		//System.out.println("总记录数="+page.getTotal());
		//System.out.println("总页数="+page.getPages());
		//System.out.println("是否为第1页="+page.isIsFirstPage());
		//System.out.println("是否为最后1页="+page.isIsLastPage());
		//System.out.println("是否有上一页="+page.isHasPreviousPage());
		//System.out.println("是否有下一页="+page.isHasNextPage());
		//System.out.println("当前页数据=");
		//for(Code c:page.getList()){
		//	System.out.println(c);
		//}
		model.addAttribute("page", page);
		return "order-list";
	}
	
	//跳转生成卡密界面
	@RequestMapping("/order-add.html")
	public String toCodeAdd() {
		return "order-add";
	}
	
	
	//后台管理界面
	@PostMapping("/index")
	public String admin(HttpSession session, Model model, String username, String password) {
		
		
		User user = codeService.login(username, password);
		if (user == null) {
			model.addAttribute("msg", "登录失败，请检查用户名和密码");
			
			return "login";
		} else {
			int number = codeService.getCodeNum();
			model.addAttribute("num", number);
			model.addAttribute("username", username);
			session.setAttribute("user", username);
			return "index";
		}
		
		
	}
	
	//查询卡密
	@RequestMapping("/query/{pageNum}")
	public String queryCodeByType(Model model,@PathVariable int pageNum, String code,  String codeType) {
		
		//PageHelper.startPage(pageNum, 10);
		List<Code> codes = new ArrayList<>();
		//判断传来的参数是否为空
		if (!"".equals(codeType)) {
			//不为空，进行查询，并添加到model中
			codes = codeService.queryCodeByType(codeType);
			model.addAttribute("codeType",codeType);
		}
		if (!"".equals(code)) {
			codes.add(codeService.queryCode(code));
			model.addAttribute("code", code);
		}
		//PageInfo<Code> page = new PageInfo<>(codes);
		model.addAttribute("page", codes);
		return "order-listByType";
	}
	
	
	@RequestMapping("/useCode.html")
	public String toUseCode() {
		return "useCode";
	}
	
	//模拟使用卡密
	@PostMapping("/use")
	public String useCode(HttpSession session, Model model, String code) {
		/*思路：定义一个变量存储用户到期时间，查询卡密的时间，如果用户已经到期，
		 * 在当前时间加上卡密时间，如果没有到期，在变量上加上时间*/
		//获取时间session
		Long dateStamp = (Long) session.getAttribute("time");
		//查询卡密
		Code result = codeService.queryCode(code);
		//判断卡密是否正确
		if (result == null) {
			model.addAttribute("msg", "卡密不存在或已被使用");
		} else {
			long time;
			//卡密正确
			//判断session是否为空
			if (dateStamp == null) {
				//为空将当前时间赋值给session，并加上卡密时间
				//java时间戳精确到毫秒，所以去除后三位
				time = System.currentTimeMillis() / 1000 + result.getCodeTime();
			} else {
				//不为空将session的时间取出来，并加上卡密拥有的时间
				time = dateStamp + result.getCodeTime();
			}
			//放回session
			session.setAttribute("time", time);
			//处理时间戳
			Date date = new Timestamp(time * 1000);
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//输出格式  yyyy-MM-dd HH:mm:ss
			model.addAttribute("msg", "到期时间" + formatter.format(date));
			//最终删除该卡密
			codeService.deleteCode(code);
		}
		return "useCode";
	}
	
	//删除一个卡密
	@RequestMapping("/delete/{code}")
	public String delete(@PathVariable String code) {
		codeService.deleteCode(code);
		return "redirect:/order-list.html/1";
	}
}

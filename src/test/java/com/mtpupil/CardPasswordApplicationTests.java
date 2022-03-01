package com.mtpupil;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mtpupil.pojo.Code;
import com.mtpupil.service.CodeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@SpringBootTest
class CardPasswordApplicationTests {
	@Autowired
	CodeServiceImpl codeService;
	
	@Test
	void contextLoads() {
	
	}
	
	@Test
	public void test1111() {
		
		//3.设置分页参数
		int pageNun = 1; // 页码
		int pageSize = 2;// 每页显示条数
		PageHelper.startPage(pageNun,pageSize);
		
		//4. 查询用户数据
		List<Code> list = codeService.queryAllCode();
		
		//5.把用户数据封装到PageInfo分页结果对象
		PageInfo<Code> page = new PageInfo<>(list);
		
		//6.取出PageInfo的属性
		//测试PageInfo全部属性
		//PageInfo包含了非常全面的分页属性
		System.out.println("当前页码="+page.getPageNum());
		System.out.println("每页显示条数="+page.getPageSize());
		System.out.println("当前页起始行号="+page.getStartRow());
		System.out.println("当前页结束行号="+page.getEndRow());
		System.out.println("总记录数="+page.getTotal());
		System.out.println("总页数="+page.getPages());
		System.out.println("是否为第1页="+page.isIsFirstPage());
		System.out.println("是否为最后1页="+page.isIsLastPage());
		System.out.println("是否有上一页="+page.isHasPreviousPage());
		System.out.println("是否有下一页="+page.isHasNextPage());
		System.out.println("当前页数据=");
		for(Code c:page.getList()){
			System.out.println(c);
		}
	}
	
	@Test
	public void teeeest(){
		Code code = codeService.queryCode("MTooo7wg1s2wfsq9");
		System.out.println("卡密时间:" + code.getCodeTime());
		System.out.println("卡密类型:" + code.getCodeType());
		System.out.println("当前时间：" +System.currentTimeMillis()/1000);
		Date date = new Timestamp(System.currentTimeMillis()/1000);
		System.out.println(date);
		
	}
}

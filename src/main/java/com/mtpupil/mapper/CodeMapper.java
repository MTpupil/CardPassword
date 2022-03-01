package com.mtpupil.mapper;

import com.mtpupil.pojo.Code;
import com.mtpupil.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 眼盛星河 心向远方
 * The eyes are full of stars,and the heart yearns for the distance.
 *
 * @author 木瞳
 * on 2022-01-18  13:20
 */
@Repository
public interface CodeMapper {
	//查询所有卡密
	List<Code> queryAllCode();
	
	//获取卡密数量
	int getCodeNum();
	
	//添加卡密
	void addCode(List codeList);
	
	//查询用户（登录验证）
	User login(String username, String password);
	
	//通过类型查询卡密
	List<Code> queryCodeByType(String codeType);
	
	//通过卡密查询卡密
	Code queryCode(String code);
	
	//删除卡密
	public default void deleteCode(String code) {
	
	}
	
	
}

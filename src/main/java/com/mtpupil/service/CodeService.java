package com.mtpupil.service;

import com.mtpupil.pojo.Code;
import com.mtpupil.pojo.User;

import java.util.List;

/**
 * 眼盛星河 心向远方
 * The eyes are full of stars,and the heart yearns for the distance.
 *
 * @author 木瞳
 * on 2022-01-18  13:27
 */
public interface CodeService {
	public List<Code> queryAllCode();
	public int getCodeNum();
	
	//添加卡密
	public void addCode(List codeList);
	
	public User login(String username, String password);
	
	public List<Code> queryCodeByType(String codeType);
	
	public Code queryCode(String  code);
	
	public void deleteCode(String code);
}

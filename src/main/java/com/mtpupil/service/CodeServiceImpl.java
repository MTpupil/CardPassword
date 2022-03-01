package com.mtpupil.service;

import com.mtpupil.mapper.CodeMapper;
import com.mtpupil.pojo.Code;
import com.mtpupil.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 眼盛星河 心向远方
 * The eyes are full of stars,and the heart yearns for the distance.
 *
 * @author 木瞳
 * on 2022-01-18  13:28
 */

@Service
public class CodeServiceImpl implements CodeService{
	
	@Autowired
	CodeMapper codeMapper;
	
	@Override
	public List<Code> queryAllCode() {
		return codeMapper.queryAllCode();
	}
	
	@Override
	public int getCodeNum() {
		return codeMapper.getCodeNum();
	}
	
	@Override
	public void addCode(List codeList) {
		codeMapper.addCode(codeList);
	}
	
	@Override
	public User login(String username, String password) {
		return codeMapper.login(username,password);
	}
	
	@Override
	public List<Code> queryCodeByType(String codeType) {
		return codeMapper.queryCodeByType(codeType);
	}
	
	@Override
	public Code queryCode(String code) {
		return codeMapper.queryCode(code);
	}
	
	@Override
	public void deleteCode(String code) {
		codeMapper.deleteCode(code);
	}
}

package com.mtpupil.pojo;

/**
 * 眼盛星河 心向远方
 * The eyes are full of stars,and the heart yearns for the distance.
 *
 * @author 木瞳
 * on 2022-01-18  13:17
 */
public class Code {
	private String code;
	private String codeType;
	private Integer codeTime;
	
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return "Code{" +
				"code='" + code + '\'' +
				", codeTime=" + codeTime +
				", codeType=" + codeType +
				'}';
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public Integer getCodeTime() {
		return codeTime;
	}
	
	public void setCodeTime(Integer codeTime) {
		this.codeTime = codeTime;
	}
	
	public String getCodeType() {
		return codeType;
	}
	
	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	public Code() {
	}
	
	public Code(String code, Integer codeTime, String codeType) {
		this.code = code;
		this.codeType = codeType;
		this.codeTime = codeTime;
	}
}

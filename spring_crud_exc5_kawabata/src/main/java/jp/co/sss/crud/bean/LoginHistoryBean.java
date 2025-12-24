package jp.co.sss.crud.bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginHistoryBean {

	private String empName;
	private LocalDateTime loginTime;

	public LoginHistoryBean(String empName) {
		this.empName = empName;
		this.loginTime = LocalDateTime.now();
	}

	public String getEmpName() {
		return empName;
	}

	public String getLoginTime() {
		return loginTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"));
	}
}
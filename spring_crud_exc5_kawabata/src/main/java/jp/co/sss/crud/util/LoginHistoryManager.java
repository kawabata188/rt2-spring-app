package jp.co.sss.crud.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import jp.co.sss.crud.bean.LoginHistoryBean;

@Component
public class LoginHistoryManager {

	private final List<LoginHistoryBean> historyList = new ArrayList<>();

	// 履歴追加
	public void add(String empName) {
		historyList.add(0, new LoginHistoryBean(empName));
	}

	// 履歴取得
	public List<LoginHistoryBean> getList() {
		return historyList;
	}
}
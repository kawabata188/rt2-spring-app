package jp.co.sss.crud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jp.co.sss.crud.bean.EmployeeBean;
import jp.co.sss.crud.form.PasswordChangeForm;
import jp.co.sss.crud.service.PasswordChangeService;

@Controller
public class PasswordChangeController {

	@Autowired
	private PasswordChangeService passwordChangeService;

	/**
	 * パスワード変更入力画面表示
	 */
	@GetMapping("/password/change")
	public String input(
			Integer empId,
			@ModelAttribute PasswordChangeForm form,
			HttpSession session) {

		EmployeeBean loginUser = (EmployeeBean) session.getAttribute("loginUser");

		// 未ログイン対策（念のため）
		if (loginUser == null) {
			return "redirect:/";
		}

		// 一般ユーザーは自分以外の変更NG
		if (loginUser.getAuthority() == 1
				&& !loginUser.getEmpId().equals(empId)) {
			return "redirect:/list";
		}

		form.setEmpId(empId);

		return "password/password_change";
	}

	/**
	 * パスワード変更確認画面表示
	 */
	@PostMapping("/password/check")
	public String check(
			@Valid @ModelAttribute PasswordChangeForm form,
			BindingResult result,
			HttpSession session,
			Model model) {

		if (result.hasErrors()) {
			return "password/password_change";
		}

		return "password/password_check";
	}

	/**
	 * パスワード変更処理
	 */
	@PostMapping("/password/complete")
	public String complete(
			@ModelAttribute PasswordChangeForm form) {
		passwordChangeService.execute(form);

		return "redirect:/password/complete";
	}

	/**
	 * 完了画面表示
	 */
	@GetMapping("/password/complete")
	public String completeView() {

		return "password/password_complete";
	}
}
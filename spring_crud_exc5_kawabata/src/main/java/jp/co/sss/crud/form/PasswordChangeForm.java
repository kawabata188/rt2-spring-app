package jp.co.sss.crud.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public class PasswordChangeForm {
	private Integer empId;

	@NotBlank(message = "{NotBlank.passwordChangeForm.newPassword}")
	@Pattern(regexp = "^[a-zA-Z0-9]{0,16}$", message = "新しいパスワードは16文字までの半角英数字で入力してください。")
	private String newPassword;

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public Integer getEmpId() {
		return empId;
	}

	public void setEmpId(Integer empId) {
		this.empId = empId;
	}

}

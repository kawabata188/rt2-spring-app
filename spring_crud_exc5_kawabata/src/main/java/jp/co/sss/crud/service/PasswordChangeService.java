package jp.co.sss.crud.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sss.crud.entity.Employee;
import jp.co.sss.crud.form.PasswordChangeForm;
import jp.co.sss.crud.repository.EmployeeRepository;

@Service
public class PasswordChangeService {

	@Autowired
	private EmployeeRepository repository;

	public void execute(PasswordChangeForm form) {
		Employee employee = repository.findById(form.getEmpId()).orElseThrow();
		employee.setEmpPass(form.getNewPassword());
		repository.save(employee);
	}

}

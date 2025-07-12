package com.example.hr.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.application.HrApplication;
import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;

@Service
public class HrService {
	private final HrApplication hrApplication;
	private final ModelMapper modelMapper;
	
	public HrService(HrApplication hrApplication, ModelMapper modelMapper) {
		this.hrApplication = hrApplication;
		this.modelMapper = modelMapper;
	}

	@Transactional
	public HireEmployeeResponse hireEmployee(HireEmployeeRequest request) {
		// DTO -> Domain's Entity: Object2Object Mapping
		var employee = modelMapper.map(request, Employee.class);
		hrApplication.hireEmployee(employee);
		return new HireEmployeeResponse("Ok", request.identity());
	}

	@Transactional
	public EmployeeResponse fireEmployee(String identity) {
		var firedEmployee = hrApplication.fireEmployee(TcKimlikNo.of(identity))
										 .orElseThrow(() -> new IllegalArgumentException("Cannot find employee with identity %s to fire.".formatted(identity)));			               
		return modelMapper.map(firedEmployee, EmployeeResponse.class);
	}

	public EmployeeResponse findEmployee(String identity) {
		var employee = hrApplication.findEmployeeByIdentity(TcKimlikNo.of(identity))
				                    .orElseThrow(() -> new IllegalArgumentException("Cannot find employee with identity %s.".formatted(identity)));
		return modelMapper.map(employee, EmployeeResponse.class);
	}
	
}

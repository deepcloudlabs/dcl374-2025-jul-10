package com.example.hr.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.RequestScope;

import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.example.validation.TcKimlikNo;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestScope
@RequestMapping("/employees")
@CrossOrigin
@Validated
@SecurityRequirement(name = "bearerAuth")
public class HrRestController {
	private final HrService hrService;
	
	public HrRestController(HrService hrService) {
		this.hrService = hrService;
	}

	@PostMapping
	public HireEmployeeResponse hireEmployee(@RequestBody @Validated HireEmployeeRequest request) {
		return hrService.hireEmployee(request);
	}

	@DeleteMapping("/{identity}")
	public EmployeeResponse fireEmployee(@PathVariable @TcKimlikNo String identity){
		return hrService.fireEmployee(identity);
	}

	@GetMapping("/{identity}")
	public EmployeeResponse findEmployeeByIdentity(@PathVariable @TcKimlikNo String identity) {
		return hrService.findEmployee(identity);
	}
	
	/*
	@GetMapping("/{identity}")
	public ResponseEntity<EmployeeResponse> findEmployeeEntityByIdentity(@PathVariable String identity) {
		return ResponseEntity.ok(hrService.findEmployee(identity));
	}
	*/
}

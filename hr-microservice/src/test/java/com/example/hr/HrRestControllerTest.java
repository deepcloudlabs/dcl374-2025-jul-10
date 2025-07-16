package com.example.hr;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.example.hr.controller.HrRestController;
import com.example.hr.domain.FiatCurrency;
import com.example.hr.dto.request.HireEmployeeRequest;
import com.example.hr.dto.response.EmployeeResponse;
import com.example.hr.dto.response.HireEmployeeResponse;
import com.example.hr.service.HrService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(HrRestController.class)
class HrRestControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private HrService hrService;

	@Autowired
	private ObjectMapper objectMapper;

	@WithMockUser(authorities = { "ROLE_WEBUSER" })
	@ParameterizedTest
	@CsvFileSource(resources = "hire_employees.csv")
	void hireEmployee_shouldReturnHireEmployeeResponse(
			String identity,
			String firstName,
			String lastName,
			String iban,
			double salary,
			String currency,
			String departments,
			String jobStyle,
			int birthYear,
			String photo,
			String token
	) throws Exception {
		var request = new HireEmployeeRequest(identity, firstName, lastName, iban, salary, FiatCurrency.valueOf(currency),
				List.of(departments.split(":")), jobStyle, birthYear,photo);
		String status = "OK";
		var response = new HireEmployeeResponse(status, identity);

		when(hrService.hireEmployee(any(HireEmployeeRequest.class))).thenReturn(response);

		// 2. Call exercise method
		mockMvc.perform(post("/employees").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer %s".formatted(token))
				.content(objectMapper.writeValueAsString(request)))
		// 3. Verification
		.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value(status))
		        .andExpect(jsonPath("$.identity").value(identity));
	}

	@WithMockUser(authorities = { "ROLE_WEBUSER" })
	@ParameterizedTest
	@CsvFileSource(resources = "fire_employees.csv")
	void fireEmployee_shouldReturnEmployeeResponse(			
			String identity,
			String firstName,
			String lastName,
			String iban,
			double salary,
			String currency,
			String departments,
			String jobStyle,
			int birthYear,
			String token
) throws Exception {
		var response = new EmployeeResponse(identity, firstName, lastName, iban, salary, FiatCurrency.valueOf(currency),
				List.of(departments.split(":")), jobStyle, birthYear);

		when(hrService.fireEmployee(identity)).thenReturn(response);

		mockMvc.perform(delete("/employees/{identity}", identity)
		        .header("Authorization", "Bearer %s".formatted(token)))
		        .andExpect(status().isOk())
				.andExpect(jsonPath("$.identity").value(identity))
				.andExpect(jsonPath("$.firstName").value(firstName));
	}

	@WithMockUser
	@ParameterizedTest
	@CsvFileSource(resources = "find_employees.csv")
	void findEmployeeByIdentity_shouldReturnEmployeeResponse(
			String identity,
			String firstName,
			String lastName,
			String iban,
			double salary,
			String currency,
			String departments,
			String jobStyle,
			int birthYear
			) throws Exception {
		var response = new EmployeeResponse(identity, firstName, lastName, iban, salary, FiatCurrency.valueOf(currency),
				List.of(departments.split(":")), jobStyle, birthYear);

		when(hrService.findEmployee(identity)).thenReturn(response);

		mockMvc.perform(get("/employees/{identity}", identity)).andExpect(status().isOk())
				.andExpect(jsonPath("$.identity").value(identity))
				.andExpect(jsonPath("$.iban").value(iban));
	}
}

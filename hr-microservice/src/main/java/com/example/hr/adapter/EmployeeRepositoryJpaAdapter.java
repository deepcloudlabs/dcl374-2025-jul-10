package com.example.hr.adapter;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.example.hr.domain.Employee;
import com.example.hr.domain.TcKimlikNo;
import com.example.hr.entity.EmployeeEntity;
import com.example.hr.repository.EmployeeEntityRepository;
import com.example.hr.repository.EmployeeRepository;

@Repository
public class EmployeeRepositoryJpaAdapter implements EmployeeRepository {
	private final EmployeeEntityRepository employeeEntityRepository;
	private final ModelMapper modelMapper;
	
	public EmployeeRepositoryJpaAdapter(EmployeeEntityRepository employeeEntityRepository, ModelMapper modelMapper) {
		this.employeeEntityRepository = employeeEntityRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	@Transactional(readOnly = true)
	public boolean exists(TcKimlikNo identity) {
		return employeeEntityRepository.existsById(identity.getValue());
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Employee persist(Employee employee) {
		var entity = modelMapper.map(employee,EmployeeEntity.class);
		var savedEntity = employeeEntityRepository.save(entity);
		return modelMapper.map(savedEntity, Employee.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Employee> findById(TcKimlikNo identity) {
		return employeeEntityRepository.findById(identity.getValue()).map(entity -> modelMapper.map(entity, Employee.class));
	}

	@Override
	@Transactional(propagation = Propagation.MANDATORY)
	public Optional<Employee> remove(Employee employee) {
		var identity = employee.getIdentity().getValue();
		var entity = employeeEntityRepository.findById(identity)
				                             .orElseThrow( () -> new IllegalArgumentException("Cannot find employee %s to delete.".formatted(identity)));
		employeeEntityRepository.delete(entity);
		return Optional.of(modelMapper.map(entity, Employee.class));
	}

}

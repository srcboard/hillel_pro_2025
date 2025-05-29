package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.example.demo.dao.CustomerDao;
import com.example.demo.dto.CustomerDto;

@SpringBootApplication
public class Lesson39SpringDataAccessApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(Lesson39SpringDataAccessApplication.class, args);
		
		CustomerDao customerDaoBean = configurableApplicationContext.getBean(CustomerDao.class);
		
		for (int i = 1; i <= 10; i++) {
			if (customerDaoBean.getById(i) == null) {
				customerDaoBean.add(new CustomerDto("Bob"+i, "bob@gmail"+i, i));
			}
		}
		
		System.out.println(customerDaoBean.getById(1));
		
		CustomerDto customerDto = customerDaoBean.getById(2);
		System.out.println(customerDto);
		if (!customerDto.getFullName().endsWith(" updated")) {
			customerDto.setFullName(customerDto.getFullName() + " updated");
			customerDaoBean.update(customerDto);
			System.out.println(customerDaoBean.getById(2));
		}
		
		customerDaoBean.delete(3);
		
	}

}

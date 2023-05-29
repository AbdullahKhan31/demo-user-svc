package com.demo.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.modelmapper.ModelMapper;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.web.filter.CorsFilter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spi.DocumentationType;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserServiceApplicationTests {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private InternalResourceViewResolver viewResolver;

	@MockBean
	private FilterRegistrationBean<CorsFilter> corsFilter;

	@Autowired
	private Docket swaggerConfiguration;

	@BeforeEach
	public void setup() {
	}

	@Test
	void contextLoads() {
		assertNotNull(modelMapper);
		assertNotNull(viewResolver);
		assertNotNull(corsFilter);
		assertNotNull(swaggerConfiguration);
	}

}


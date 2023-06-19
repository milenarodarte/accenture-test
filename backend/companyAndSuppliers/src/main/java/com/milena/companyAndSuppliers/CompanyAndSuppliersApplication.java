package com.milena.companyAndSuppliers;

import com.milena.companyAndSuppliers.config.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompanyAndSuppliersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompanyAndSuppliersApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
		FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new CorsFilter());
		registrationBean.addUrlPatterns("/*"); //
		registrationBean.setOrder(1);
		return registrationBean;
	}


}

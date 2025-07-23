package com.easybank.accounts;

import com.easybank.accounts.dto.AccountsContactInfoDTO;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/*
// Uncomment the following lines if you want to use component scanning for specific packages

@ComponentScans({
		@ComponentScan("com.easybank.accounts"),
		@ComponentScan("com.easybank.accounts.controller"),
		@ComponentScan("com.easybank.accounts.service"),
		@ComponentScan("com.easybank.accounts.repository"),
		@ComponentScan("com.easybank.accounts.audit")})
@EnableJpaRepositories(basePackages = "com.easybank.accounts.repository")
@EntityScan(basePackages = "com.easybank.accounts.entity")*/

@EnableJpaAuditing(auditorAwareRef = "AuditAwareImpl")
@OpenAPIDefinition(
		info = @Info(
				title = "Accounts Service API Documentation",
				version = "1.0",
				description = "API for managing accounts in EasyBank",
				contact = @Contact(
						name = "Vamshi Krishna",
						email = "vamshikrishna1251@outlook.com",
						url = "https://www.linkedin.com/in/vamshi-p/"
				),
				license = @License(
						name = "Apache 2.0",
						url = "http://www.apache.org/licenses/LICENSE-2.0"
				)
		),
		externalDocs = @ExternalDocumentation(
				description = "EasyBank Accounts Service Documentation",
				url = "https://easybank.com/swagger-ui/index.html#/accounts-controller" // Update this URL to your actual documentation URL
		)
)
@EnableConfigurationProperties(value = {AccountsContactInfoDTO.class})
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}

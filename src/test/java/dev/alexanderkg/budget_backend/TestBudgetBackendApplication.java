package dev.alexanderkg.budget_backend;

import org.springframework.boot.SpringApplication;

public class TestBudgetBackendApplication {

	public static void main(String[] args) {
		SpringApplication.from(BudgetBackendApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}

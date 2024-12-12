package dev.alexanderkg.budget_backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class BudgetBackendApplicationTests {

	@Test
	void contextLoads() {
		// can be empty since it checks if the application context loads up,
		// and it won't be green if the application context fails
	}

}

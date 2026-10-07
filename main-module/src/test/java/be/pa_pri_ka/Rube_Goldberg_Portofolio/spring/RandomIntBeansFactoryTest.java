package be.pa_pri_ka.Rube_Goldberg_Portofolio.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RandomIntBeansFactoryTest {

	@Autowired
	private RandomIntBeansFactory beanFactory;

	@Test
	void providesRandomBeans() {
		RandomIntBean randomIntBean1 = beanFactory.gimmeARandomBean();
		RandomIntBean randomIntBean2 = beanFactory.gimmeARandomBean();

		assertNotEquals(randomIntBean2.getInt(), randomIntBean1.getInt());
	}
}

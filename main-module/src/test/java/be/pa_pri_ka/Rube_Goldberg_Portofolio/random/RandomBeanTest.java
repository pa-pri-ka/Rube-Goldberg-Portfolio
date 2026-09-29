package be.pa_pri_ka.Rube_Goldberg_Portofolio.random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RandomBeanTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private RandomBean randomBean;

	@Test
	void providesARandomInteger() {
		final int randomInteger = this.randomBean.getRandomInteger();
		assertTrue(0 < randomInteger);
	}

	@Test
	void providesADifferentIntegerAtEveryBeanFetch() {
		final int firstRandomInteger = this.randomBean.getRandomInteger();
		final RandomBean newRandomBean = this.applicationContext.getBean(RandomBean.class);
		final int secondRandomInteger = newRandomBean.getRandomInteger();
		assertNotEquals(firstRandomInteger, secondRandomInteger);
	}
}

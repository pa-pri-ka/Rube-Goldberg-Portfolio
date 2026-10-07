package be.pa_pri_ka.Rube_Goldberg_Portofolio.spring;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.random.RandomGenerator;

@Component
@Scope("prototype")
public class RandomIntBean {

	private final int randomInt;

	RandomIntBean() {
		this.randomInt = RandomGenerator.getDefault().nextInt();
	}

	public int getInt() {
		return randomInt;
	}
}

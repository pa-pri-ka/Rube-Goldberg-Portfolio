package be.pa_pri_ka.Rube_Goldberg_Portofolio.random;

import jakarta.annotation.PostConstruct;

import java.util.Random;
import java.util.random.RandomGenerator;

class RandomBean {

	/**
	 * @noinspection UnsecureRandomNumberGeneration
	 */
	private static final RandomGenerator random = new Random();

	private int randomInteger;

	int getRandomInteger() {
		return this.randomInteger;
	}

	@PostConstruct
	void generateRandomInteger() {
		while (0 >= this.randomInteger) {
			this.randomInteger = random.nextInt();
		}
	}
}

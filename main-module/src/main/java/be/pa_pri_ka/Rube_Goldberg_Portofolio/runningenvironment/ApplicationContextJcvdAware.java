package be.pa_pri_ka.Rube_Goldberg_Portofolio.runningenvironment;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class ApplicationContextJcvdAware implements ApplicationContextAware {

	/** @noinspection InstanceVariableMayNotBeInitialized*/
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final org.springframework.context.ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	public void logExistingBeans() {
		System.out.println(">>> BEANS");
		System.out.println("Beans count: " + this.applicationContext.getBeanDefinitionCount());

		final String[] beanDefinitionNames = this.applicationContext.getBeanDefinitionNames();
		Arrays.stream(beanDefinitionNames).filter(name -> !name.contains(".")).sorted().forEach(System.out::println);
		Arrays.stream(beanDefinitionNames).filter(name -> name.contains(".")).sorted().forEach(System.out::println);
	}
}

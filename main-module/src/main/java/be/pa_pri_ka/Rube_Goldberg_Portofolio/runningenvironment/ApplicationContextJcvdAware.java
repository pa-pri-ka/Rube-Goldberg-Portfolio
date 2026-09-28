package be.pa_pri_ka.Rube_Goldberg_Portofolio.runningenvironment;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ApplicationContextJcvdAware implements ApplicationContextAware {

	/**
	 * @noinspection InstanceVariableMayNotBeInitialized
	 */
	private ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(final org.springframework.context.ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	@PostConstruct
	void logExistingBeans() {
		System.out.println(">>> BEANS");
		System.out.println("Beans count: " + this.applicationContext.getBeanDefinitionCount());

		this.listBeanNamesByFilter()
				.forEach((filter, names) -> names
						.forEach(System.out::println));
	}

	private Map<BeanFilter, List<String>> listBeanNamesByFilter() {
		final Map<BeanFilter, List<String>> map = Arrays.stream(this.applicationContext.getBeanDefinitionNames())
				.sorted()
				.map(name -> {
					if (!name.contains(".")) {
						return Map.entry(BeanFilter.NO_DOTS, name);
					}
					return Map.entry(BeanFilter.OTHER, name);
				})
				.collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
		return new EnumMap<>(map);
	}

	private enum BeanFilter {
		NO_DOTS,
		OTHER
	}
}

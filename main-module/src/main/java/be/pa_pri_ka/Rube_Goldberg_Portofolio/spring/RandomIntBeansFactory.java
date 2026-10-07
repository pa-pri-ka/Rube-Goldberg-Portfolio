package be.pa_pri_ka.Rube_Goldberg_Portofolio.spring;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.stereotype.Service;

@Service
public class RandomIntBeansFactory {
	private final ObjectFactory<? extends RandomIntBean> randomIntBeanFactory;

	public RandomIntBeansFactory(ObjectFactory<? extends RandomIntBean> randomIntBeanFactory) {
		this.randomIntBeanFactory = randomIntBeanFactory;
	}

	public RandomIntBean gimmeARandomBean() {
		return randomIntBeanFactory.getObject();
	}
}




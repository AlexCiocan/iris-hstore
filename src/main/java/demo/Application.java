package demo;

import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@ImportResource(value = { "classpath:web-application-config.xml" })
public class Application extends SpringBootServletInitializer {
	@Autowired
	private WebApplicationContext appContext;
	public static void main(String args[]) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);

		System.out.println("Here are some beans provided by spring boot");
		String beanNames[] = ctx.getBeanDefinitionNames();

		Arrays.sort(beanNames);

		 for (String beanName : beanNames) {
		 System.out.println(beanName);
		 }
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {

		return application.sources(Application.class);
	}

	@Override
	public void onStartup(ServletContext servletContext)
			throws ServletException {
		// TODO Auto-generated method stub

		// Create the 'root' Spring application contex
		//AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		AnnotationConfigWebApplicationContext rootContext=new AnnotationConfigWebApplicationContext();	
		rootContext.setConfigLocation("/WEB-INF/web-application-integration-config.xml");
		
		// Manage the lifecycle of the root application context
		servletContext.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		
		dispatcherContext.register(Application.class);
		
		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
				"dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		servletContext.addFilter("characterEncodingFilter",
				CharacterEncodingFilter.class);
		
		super.onStartup(servletContext);
	}

	@Bean
	public Filter characterEncodingFilter() {
		final CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return characterEncodingFilter;
	}

}

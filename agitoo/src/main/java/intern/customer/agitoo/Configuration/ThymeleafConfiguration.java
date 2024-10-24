package intern.customer.agitoo.Configuration;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


//@EnableWebMvc
@AllArgsConstructor
@Configuration
@ComponentScan
public class ThymeleafConfiguration implements WebMvcConfigurer {

    //this config created to be able to see the view as under template for thymeleaf

    @Autowired
    private ApplicationContext applicationContext;


    public void setApplicationContext (final ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;
    }

//    @Bean
//    public SpringResourceTemplateResolver templateResolver() {
//        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver ();
//        templateResolver.setApplicationContext (applicationContext);
//        templateResolver.setPrefix ("/templates/");
//        templateResolver.setSuffix (".html");
//        templateResolver.setTemplateMode (TemplateMode.HTML);
//        templateResolver.setCacheable (true);
//
//        return templateResolver;
//    }

//    @Bean
//    public SpringTemplateEngine templateEngine() {
//        SpringTemplateEngine templateEngine = new SpringTemplateEngine ();
//        templateEngine.setTemplateResolver (templateResolver ());
//        templateEngine.setEnableSpringELCompiler (true);
//
//        return templateEngine;
//    }
//
//    @Bean
//    public ThymeleafViewResolver viewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine (templateEngine ());
//
//        return viewResolver;
//    }

//    @Bean
//    public ResourceBundleMessageSource messageSource() {
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("Messages");
//        return messageSource;
//    }

    @Override
    public void addResourceHandlers (final ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler ("/css/**")
                .addResourceLocations ("classpath:/static/")
                .resourceChain (true);
        registry
                .addResourceHandler ("/js/**")
                .addResourceLocations ("classpath:/static/")
                .resourceChain (true);
    }
}

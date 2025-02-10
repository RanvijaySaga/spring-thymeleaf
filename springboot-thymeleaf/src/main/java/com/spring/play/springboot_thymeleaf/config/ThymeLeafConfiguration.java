package com.spring.play.springboot_thymeleaf.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Description;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;

// in spring boot No explicit configuration is necessary. By default, HTML files should be placed in the resources/templates location
// commenting the configuration annotation since I'm using Spring boot in this application.
//@Configuration
public class ThymeLeafConfiguration {

    /**
     * The templateResolver bean properties prefix and suffix indicate the location of the view pages within the webapp directory and their filename extension, respectively.
     * The templateMode property specifies the type of template to be used. In this case, it is HTML5.
     * The templateResolver bean is used to resolve the Thymeleaf templates.
     * The templateResolver bean is configured to look for Thymeleaf templates in the /WEB-INF/views/ directory with the .html extension.
     */
    @Bean
    @Description("Thymeleaf Template Resolver")
    public SpringResourceTemplateResolver templateResolver() {
        // SpringResourceTemplateResolver automatically integrates with Spring's own
        // resource resolution infrastructure, which is highly recommended.
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        return templateResolver;
    }

    /**
     * The ViewResolver interface in Spring MVC maps the view names returned by a controller to actual view objects.
     * ThymeleafViewResolver implements the ViewResolver interface, and it’s used to determine which Thymeleaf views to render, given a view name.
     * The viewResolver bean is configured to use the templateEngine bean to render the views.
     */
    @Bean
    @Description("Thymeleaf Template Engine")

    public SpringTemplateEngine templateEngine() {
        // SpringTemplateEngine automatically applies SpringStandardDialect and
        // enables Spring's own MessageSource message resolution mechanisms.
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    /**
     * The viewResolver bean is used to resolve the Thymeleaf templates.
     * The viewResolver bean is configured to look for Thymeleaf templates in the /WEB-INF/views/ directory with the .html extension.
     */
    @Bean
    @Description("Thymeleaf View Resolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }
}

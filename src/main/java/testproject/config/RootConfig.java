package testproject.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by Exidnus on 06.01.2016.
 */
@Configuration
@ComponentScan(basePackages = {"testproject"},
        excludeFilters = {@Filter(type= FilterType.ANNOTATION, value = EnableWebMvc.class)})
public class RootConfig {
}

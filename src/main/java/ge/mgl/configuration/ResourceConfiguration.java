package ge.mgl.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by MJaniko on 3/12/2017.
 */
@Configuration
public class ResourceConfiguration extends WebMvcConfigurerAdapter{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("/resources/");

        registry.addResourceHandler("/admin/**")
                .addResourceLocations("/resources-security/");
    }

}

package alerts.api.util.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/***
 * Class to configure Swagger Documentation
 * 
 * @author Bavithra Jayaraj
 *
 */
@Configuration
@Profile({ "dev" })
@EnableSwagger2
public class SwaggerConfiguration {

    @Value("${release.version}")
    private String releaseVersion;

    @Value("${api.version}")
    private String apiVersion;

    /***
     * Configure all endpoint's into documentation
     * 
     * @author Bavithra Jayaraj
     *
     * @return<code>Docket</code> object
     */
    @Bean
    public Docket api() {
	return new Docket(DocumentationType.SWAGGER_2)
		.useDefaultResponseMessages(false)
		.select()
		.apis(RequestHandlerSelectors.basePackage("alerts.api.controller"))
		.paths(PathSelectors.any()).build().apiInfo(apiInfo());
    }

    /***
     * Configure API info to documentation
     * 
     * @author Bavithra Jayaraj
     *
     * @return <code>ApiInfo</code> object
     */
    private ApiInfo apiInfo() {
	return new ApiInfoBuilder().title("alerts Java API")
		.description("alerts Java API - Endpoint's documentation")
		.version(releaseVersion.concat("_").concat(apiVersion))
		.contact(new Contact("Bavithra Jayaraj", "", "bavithrajayaraj@gmail.com"))
		.build();
    }

}

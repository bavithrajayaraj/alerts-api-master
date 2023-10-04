package alerts.api.util.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

/***
 * Component created to expose ModelMapper as Autowired
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@Component
public class GenericMapper extends ModelMapper {
	public ObjectMapper objectMapper() {
		return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
	}
}

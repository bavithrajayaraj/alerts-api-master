package alerts.api.dto.response;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;

/***
 * The response error structure
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@AllArgsConstructor
public final class ResponseError {

    @JsonProperty("status_code")
    private HttpStatus statusCode;
    @Getter
    private String message;
    
    public ResponseError(HttpStatus notFound, String string) {
		this.statusCode = notFound;
		this.message = string;
	}

	public int getStatusCode() { 
	return statusCode.value();
    }
}

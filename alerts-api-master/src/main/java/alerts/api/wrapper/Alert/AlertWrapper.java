package alerts.api.wrapper.Alert;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import alerts.api.entity.Alert.AlertEntity;
import alerts.api.wrapper.ResponseWrapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * Represents a Alert response json
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AlertWrapper extends ResponseWrapper<AlertEntity> {

	@ApiModelProperty(notes = " Id", name = "id", required = true, value = "Id")
	private String id;

	@ApiModelProperty(notes = "Alert Id", name = "alert_id", required = true, value = "Alert Id")
	private String alert_id;

	@ApiModelProperty(notes = "service id", name = "service_id", required = true, value = "Service Id")
	private String service_id;

	@ApiModelProperty(notes = "Service name", name = "service_name", required = true, value = "Service name")
	private String service_name;

	@ApiModelProperty(notes = "Model", name = "Model", required = true, value = "Model")
	private String model;

	@ApiModelProperty(notes = "Alert Type", name = "alert_type", required = true, value = "alert Type")
	private String alert_type;

	@ApiModelProperty(notes = "alert timestamp", name = "alert_ts", required = true, value = "alert TimeStamp")
	private long alert_ts;

	@ApiModelProperty(notes = "Severity", name = "severity", required = true, value = "Severity")
	private String severity;

	@ApiModelProperty(notes = "team Slack", name = "team_slack", required = true, value = "team slack")
	private String teasm_slack;

}

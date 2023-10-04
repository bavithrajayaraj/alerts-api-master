package alerts.api.dto.model.alert;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import alerts.api.dto.BaseDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * Class to represent the body request to manage alerts
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AlertDTO extends BaseDTO {

	public String getAlert_id() {
		return alert_id;
	}

	public void setAlert_id(String alert_id) {
		this.alert_id = alert_id;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_name() {
		return service_name;
	}

	public void setService_name(String service_name) {
		this.service_name = service_name;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getAlert_type() {
		return alert_type;
	}

	public void setAlert_type(String alert_type) {
		this.alert_type = alert_type;
	}

	public long getAlert_ts() {
		return alert_ts;
	}

	public void setAlert_ts(long alert_ts) {
		this.alert_ts = alert_ts;
	}

	public String getSeverity() {
		return severity;
	}

	public void setSeverity(String severity) {
		this.severity = severity;
	}

	public String getTeam_slack() {
		return team_slack;
	}

	public void setTeam_slack(String team_slack) {
		this.team_slack = team_slack;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public AlertDTO(String id, String alert_id,  String service_id,  String service_name,
			 String model,  String alert_type,  long alert_ts,  String severity,
			 String team_slack) {
		super();
		this.id = id;
		this.alert_id = alert_id;
		this.service_id = service_id;
		this.service_name = service_name;
		this.model = model;
		this.alert_type = alert_type;
		this.alert_ts = alert_ts;
		this.severity = severity;
		this.team_slack = team_slack;
	}

	private static final long serialVersionUID = -3636540370641171305L;

	@NotBlank
	@ApiModelProperty(notes = " Id", name = "id", required = true, value = " Id")
	private String id;
	
	@NotBlank
	@ApiModelProperty(notes = "Alert Id", name = "alert_id", required = true, value = "Alert Id")
	private String alert_id;

	@NotBlank
	@ApiModelProperty(notes = "service id", name = "service_id", required = true, value = "Service Id")
	private String service_id;

	@NotBlank
	@ApiModelProperty(notes = "Service name", name = "service_name", required = true, value = "Service name")
	private String service_name;

	@NotBlank
	@ApiModelProperty(notes = "Model", name = "Model", required = true, value = "Model")
	private String model;

	@NotBlank
	@ApiModelProperty(notes = "Alert Type", name = "alert_type", required = true, value = "alert Type")
	private String alert_type;

	@NotBlank
	@ApiModelProperty(notes = "alert timestamp", name = "alert_ts", required = true, value = "alert TimeStamp")
	private long alert_ts;

	@NotBlank
	@ApiModelProperty(notes = "Severity", name = "severity", required = true, value = "Severity")
	private String severity;

	@NotBlank
	@ApiModelProperty(notes = "team Slack", name = "team_slack", required = true, value = "team slack")
	private String team_slack;

}

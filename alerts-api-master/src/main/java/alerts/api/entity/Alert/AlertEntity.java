package alerts.api.entity.Alert;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import alerts.api.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/***
 * Alert entity on the database
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "alert")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class AlertEntity extends BaseEntity {


	@Id
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid2")
	private String id;

	public AlertEntity(String id, String alert_id, String service_id, String service_name, String model,
			String alert_type, long alert_ts, String severity, String team_slack) {
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

	@Column(nullable = false)
	private String alert_id;

	@Column(nullable = false)
	private String service_id;

	@Column(nullable = false)
	private String service_name;

	@Column(nullable = false)
	private String model;

	@Column(nullable = false)
	private String alert_type;

	@Column(nullable = false)
	private long alert_ts;

	@Column(nullable = false)
	private String severity;

	@Column(nullable = false)
	private String team_slack;

}

package alerts.api.controller.product;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import alerts.api.controller.alert.AlertController;
import alerts.api.dto.model.alert.AlertDTO;
import alerts.api.entity.Alert.AlertEntity;
import alerts.api.exception.RecordNotFoundException;
import alerts.api.service.Alert.AlertService;
import alerts.api.wrapper.Alert.AlertWrapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
@DisplayName("Alert Controller Test")
public class AlertControllerTest {

	private static final String URL = "/alerts";
	private static final String NO_EXISTS_ID = "XPTO";
	private static final String id = "0005";

	private static final String alert_id = "c950482e9911ec7e41f7ca5e5d9a424f";

	private static final String service_id = "my_test_service_id";

	private static final String service_name = "my_test_service";

	private static final String model = "my_test_model";

	private static final String alert_type = "anomaly";

	private static final long alert_ts = 1695644160;

	private static final String severity = "warning";

	private static final String team_slack = "slack_ch";

	@InjectMocks
	AlertController controller;

	@Mock
	AlertService service;

	@BeforeEach
	private void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    @Order(1)
    @DisplayName("Get all alerts")
    public void whenGetAllalertsTest() {
	when(service.findAll()).thenReturn(new ArrayList<AlertEntity>());
	
	ResponseEntity<Iterable<AlertWrapper>> response = controller.findAll();
	assertThat(response).isNotNull();
	assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.OK.value());
    }

	@Test
	@Order(2)
	@DisplayName("Get no exist Alert by id")
	public void whenGetNotExistsAlertTest() {

		// when(service.findById(any())).thenReturn(mockAlert());
		ResponseEntity<AlertWrapper> response = controller.findById(NO_EXISTS_ID);
		assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.NOT_FOUND.value());
	}

	@Test
    @Order(5)
    @DisplayName("Add Alert")
    public void whenAddAlertTest() throws RecordNotFoundException {
	
	when(service.save(any())).thenReturn(mockAlertEntity());
	ResponseEntity<AlertWrapper> response = null;
	response = controller.create(mockAlertDTO());
	assertThat(response).isNotNull();
	assertThat(response.getStatusCodeValue()).isEqualTo(HttpStatus.CREATED.value());
    }

	private AlertEntity mockAlertEntity() {
		return new AlertEntity(id, alert_id, service_id, service_name, model, alert_type, alert_ts, severity,
				team_slack);
	}

	private AlertDTO mockAlertDTO() {
		return new AlertDTO(id, alert_id, service_id, service_name, model, alert_type, alert_ts, severity, team_slack);
	}

	private Optional<AlertEntity> mockOptionalAlertEntity() {
		return Optional.of(new AlertEntity(id, alert_id, service_id, service_name, model, alert_type, alert_ts,
				severity, team_slack));
	}

}

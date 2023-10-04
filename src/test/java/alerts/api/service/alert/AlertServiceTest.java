package alerts.api.service.alert;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
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
import org.mockito.Spy;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import alerts.api.dto.model.alert.AlertDTO;
import alerts.api.entity.Alert.AlertEntity;
import alerts.api.exception.RecordNotFoundException;
import alerts.api.repository.Alert.AlertRepository;
import alerts.api.service.Alert.impl.AlertServiceImpl;
import alerts.api.dto.model.alert.AlertDTO;
import alerts.api.util.mapper.GenericMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class, MockitoTestExecutionListener.class })
@DisplayName("Alert Service Test")
public class AlertServiceTest {

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
	private AlertServiceImpl service;

	@Mock
	private AlertRepository repository;

	@Spy
	private GenericMapper modelMapper;

	@BeforeEach
	private void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	@Order(1)
	@DisplayName("Add a Alert")
	public void whenSaveAlertTest() {
		AlertDTO dto = new AlertDTO(id, alert_id, service_id, service_name, model, alert_type, alert_ts, severity,
				team_slack);
		AlertEntity entity = new AlertEntity(id, alert_id, service_id, service_name, model, alert_type, alert_ts,
				severity, team_slack);

		when(repository.save(any())).thenReturn(entity);
		doReturn(entity).when(modelMapper).map(dto, AlertEntity.class);

		AlertEntity response = service.save(dto);

		assertThat(response).isNotNull();
	}

	@Test
    @Order(4)
    @DisplayName("Get all alerts")
    public void whenGetAllalertsTest() throws RecordNotFoundException {
	
	when(repository.findAll()).thenReturn(new ArrayList<>());
	
	
	Iterable<AlertEntity> response = service.findAll();
	
	assertThat(response).isNotNull();
    }

}

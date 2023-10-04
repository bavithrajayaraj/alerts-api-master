package alerts.api.controller.alert;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import alerts.api.dto.model.alert.AlertDTO;
import alerts.api.entity.Alert.AlertEntity;
import alerts.api.service.Alert.AlertService;
import alerts.api.util.converter.ConverterToWrapper;
import alerts.api.wrapper.Alert.AlertWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.log4j.Log4j2;

/***
 * Controller to manage alerts
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@Log4j2
@RestController
@RequestMapping("/alerts")
@Api(value = "Test API Alert Controller", tags = { "alerts" }, produces = "application/json")
public class AlertController {

	@Autowired
	private AlertService service;

	private ConverterToWrapper<AlertWrapper, AlertEntity> wrapperConverter;

	public AlertController() {
		wrapperConverter = new ConverterToWrapper<>(AlertWrapper::new);
	}

	/***
	 * Return all alerts in the database
	 * 
	 * @author Bavithra Jayaraj
	 *
	 * @return ResponseEntity with a Iterable<AlertWrapper> object and the http
	 *         status
	 * 
	 *         HTTP Status: 200 - OK: Everything worked as expected 500: Something
	 *         wrong on API
	 */
	@GetMapping
	@ApiOperation(value = "Get list of alerts", response = Iterable.class, produces = "application/json", tags = "alerts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return the list of alerts"),
			@ApiResponse(code = 500, message = "An exception ocurred"), })
	public ResponseEntity<Iterable<AlertWrapper>> findAll() {

		Iterable<AlertEntity> response = service.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(wrapperConverter.toList(response));
	}

	/***
	 * Return a Alert searching by ID
	 * 
	 * @author Bavithra Jayaraj
	 *
	 * @param id Alert id
	 * @return ResponseEntity with a AlertWrapper object and the http status
	 * 
	 *         HTTP Status: 200 - OK: Everything worked as expected. Alert found.
	 *         404 - Not found: Alert not found in the database 500: Something wrong
	 *         on API
	 */
	@GetMapping(path = "/{id}")
	@ApiOperation(value = "Find Alert by id", response = AlertWrapper.class, produces = "application/json", tags = "alerts")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Return the Alert"),
			@ApiResponse(code = 404, message = "Alert not exist in the database"),
			@ApiResponse(code = 500, message = "An exception ocurred"), })
	public ResponseEntity<AlertWrapper> findById(
			@ApiParam(name = "id", type = "String", value = "Alert id", example = "0001", required = true) @PathVariable(value = "id", required = true) String id) {
		return service.findById(id).map(record -> ResponseEntity.ok().body(wrapperConverter.convert(record)))
				.orElse(ResponseEntity.notFound().build());
	}

	/**
	 * Add a new Alert on the database
	 * 
	 * @author Bavithra Jayaraj
	 *
	 * @param request AlertDTO object containing the data to save
	 * @return ResponseEntity with a AlertWrapper object and the http status
	 * 
	 *         HTTP Status: 201 - Created: Everything worked as expected. 400 - Bad
	 *         Request: Required fields are emtpy 500: Something wrong on API
	 */
	@PostMapping
	@ApiOperation(value = "Add a new Alert", response = AlertWrapper.class, produces = "application/json", tags = "alerts")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Return the Alert with the id"),
			@ApiResponse(code = 400, message = "Exist one or more required fields empty"),
			@ApiResponse(code = 500, message = "An exception ocurred"), })
	public ResponseEntity<AlertWrapper> create(
			@ApiParam(name = "request", type = "AlertDTO", value = "Alert data to save", required = true) @Valid @RequestBody AlertDTO request) {
		AlertEntity Alert = service.save(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(wrapperConverter.convert(Alert));
	}

}

package alerts.api.service.Alert.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import alerts.api.dto.model.alert.AlertDTO;
import alerts.api.entity.Alert.AlertEntity;
import alerts.api.exception.RecordNotFoundException;
import alerts.api.repository.Alert.AlertRepository;
import alerts.api.service.Alert.AlertService;
import alerts.api.util.mapper.GenericMapper;
import lombok.extern.log4j.Log4j2;

/***
 * Alert service implementation. This class has all business rules applied to do
 * any operation,
 *
 *
 * @author Bavithra Jayaraj
 *
 */
@Log4j2
@Service
public class AlertServiceImpl implements AlertService {
	Logger log = LoggerFactory.getLogger(AlertService.class);

	@Autowired
	AlertRepository repository;

	@Autowired
	GenericMapper modelMapper;

	/**
	 * @see alertservice#save(AlertDTO)
	 */
	@Override
	public AlertEntity save(AlertDTO dto) {

		log.info(String.format("[ADD Alert]: %s", dto.toString()));
		AlertEntity Alert = modelMapper.map(dto, AlertEntity.class);
		return repository.save(Alert);
	}

	/**
	 * @see alertservice#save(AlertDTO, String)
	 */
	@Override
	public AlertEntity save(AlertDTO Alert, String id) throws RecordNotFoundException {

		log.info(String.format("[UPDATE Alert]: %s - %s", id, Alert.toString()));

		Optional<AlertEntity> prodDb = findById(id);
		AlertEntity prd = null;

		if (!prodDb.isPresent()) {
			throw new RecordNotFoundException();
		}
		prd = prodDb.get();
		modelMapper.map(Alert, prd);

		// Alert.mergeToEntity(prd);

		return repository.save(prd);
	}

	/**
	 * @see alertservice#findAll()
	 */
	@Override
	public Iterable<AlertEntity> findAll() {
		log.info("[ALL alerts]");

		return repository.findAll();
	}

	/**
	 * @see alertservice#findById(String)
	 */
	@Override
	public Optional<AlertEntity> findById(String id) {
		log.info(String.format("[FIND Alert BY ID]: %s", id));

		return repository.findById(id);

	}

	/***
	 * Return the specification to dinamyc query
	 * 
	 * @author Bavithra Jayaraj
	 *
	 * @param expression Alert name or description
	 * @param minPrice   Alert ts start
	 * @param maxPrice   Alert ts end
	 * @return Specification<AlertEntity> dinamyc specification
	 */
	private Specification<AlertEntity> getalertspecification(String expression, BigDecimal minPrice,
			BigDecimal maxPrice) {

		return (Root<AlertEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (StringUtils.isNotBlank(expression)) {

				Predicate predicatesByExpresion = builder.or(
						builder.like(builder.lower(root.get("name")), "%" + expression.toLowerCase() + "%"),
						builder.like(builder.lower(root.get("description")), "%" + expression.toLowerCase() + "%"));
				predicates.add(predicatesByExpresion);
			}

			if (null != minPrice) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("price"), minPrice));
			}

			if (null != maxPrice) {
				predicates.add(builder.lessThanOrEqualTo(root.get("price"), maxPrice));
			}

			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}

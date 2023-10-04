package alerts.api.service.Alert;

import java.util.Optional;

import alerts.api.dto.model.alert.AlertDTO;
import alerts.api.entity.Alert.AlertEntity;
import alerts.api.exception.RecordNotFoundException;

/***
 * Alert service interface
 *
 *
 * @author Bavithra Jayaraj
 *
 */
public interface AlertService {
    
    
    /**
     * Find all alerts
     * 
     * @author Bavithra Jayaraj
     *
     * @return Iterable<AlertEntity>
     */
    Iterable<AlertEntity> findAll();
    
    /**
     * Find Alert by id 
     * 
     * @author Bavithra Jayaraj
     *
     * @param id Alert id
     * @return Iterable<AlertEntity>
     */
    Optional<AlertEntity> findById(String id);
    
    /**
     * Add a Alert
     * 
     * @author Bavithra Jayaraj
     *
     * @param dto Alert data 
     * @return AlertEntity saved
     */
    AlertEntity save(AlertDTO dto);
    
    /***
     * Update a Alert 
     * 
     * @author Bavithra Jayaraj
     *
     * @param Alert Alert data
     * @param id Alert id
     * @return AlertEntity saved
     * @throws RecordNotFoundException Alert not found
     */
    AlertEntity save(AlertDTO Alert, String id) throws RecordNotFoundException;
    
    
}

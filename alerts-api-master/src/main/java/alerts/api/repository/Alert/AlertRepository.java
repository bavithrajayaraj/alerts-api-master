package alerts.api.repository.Alert;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import alerts.api.entity.Alert.AlertEntity;

/***
 * Alert repository interface
 *
 *
 * @author Bavithra Jayaraj
 *
 */
public interface AlertRepository extends CrudRepository<AlertEntity, String>, JpaSpecificationExecutor<AlertEntity>{

}

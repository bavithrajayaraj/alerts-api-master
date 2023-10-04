package alerts.api.wrapper;

import org.modelmapper.ModelMapper;

/**
 * Generic response json
 *
 *
 * @author Bavithra Jayaraj
 *
 * @param <T>
 */
public class ResponseWrapper<T> {
    /***
     * Return objet wrapper with the entity values
     * 
     * @author Bavithra Jayaraj
     *
     * @param entity
     * @return
     */
    public ResponseWrapper<T> convert(T entity) {
	new ModelMapper().map(entity, this);
	return this;
    }    
}

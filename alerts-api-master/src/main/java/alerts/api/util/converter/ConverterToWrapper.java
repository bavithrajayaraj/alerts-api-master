package alerts.api.util.converter;

import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import alerts.api.wrapper.ResponseWrapper;
/***
 * Class that convert <code>S</code> class to <code>T</code> object
 *
 *
 * @author Bavithra Jayaraj
 *
 * @param <T>
 * @param <S>
 */
@SuppressWarnings("rawtypes")
public class ConverterToWrapper<T extends ResponseWrapper, S> {
    
    private Supplier<T> wrapperType; 
    
    public ConverterToWrapper(Supplier<T> wrapperType) {
	this.wrapperType = wrapperType;
    }
    
    /**
     * Conver a list of <code>S</code> class to a list of <code>T</code> ResponseWrapper class
     * 
     * @author Bavithra Jayaraj
     *
     * @param result Iterable<S> 
     * @return Iterable<T>
     */
    public Iterable<T> toList(Iterable<S> result) {
	Iterable<T> retorno = null;
	
	retorno = StreamSupport.stream(result.spliterator(), false)
		      .map(p-> convert(p))
		      .collect(Collectors.toList());
	return retorno;
    }
    
    /***
     * Convert object S to T 
     * 
     * @author Bavithra Jayaraj
     *
     * @param entity
     * @return
     */
    @SuppressWarnings("unchecked")
    public T convert(S entity) { 
	return (T) wrapperType.get().convert(entity);
    }

    
}

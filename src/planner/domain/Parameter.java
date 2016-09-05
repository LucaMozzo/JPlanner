package planner.domain;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by LUCA on 01/09/2016.
 *
 * Specifies the objects supported by an action
 */
public class Parameter<E> {

    private Class type;

    public Parameter(){
        Type type = getClass().getGenericSuperclass();
        ParameterizedType paramType = (ParameterizedType) type;
        type = (Class<E>) paramType.getActualTypeArguments()[0];
    }
}

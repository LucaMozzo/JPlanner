package planner.types;

import planner.domain.Variable;

import java.lang.reflect.Field;

/**
 * Created by LUCA on 30/06/2016.
 *
 * Represents a custom object
 */
public abstract class Object {

    /**
     * Returns a field variable in the class with the given name
     * @param name the name of the field
     * @return the variable
     */
    public Variable getPropertyByName(String name) {
        try {
            Field f = getClass().getDeclaredField(name);
            f.setAccessible(true);
            return (Variable) f.get(this);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassCastException e) {
            System.err.println("Only Variable<> objects are allowed in custom objects");
        }
        return null;
    }

    /**
     * Two objects match if they have the same properties with the same values (different from equals)
     * @param other the other Object
     * @return whether they match
     */
    public boolean matches(Object other){
        return false;
    }
}

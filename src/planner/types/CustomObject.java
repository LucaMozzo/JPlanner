package planner.types;

import planner.domain.Variable;

import java.lang.reflect.Field;

/**
 * Created by LUCA on 30/06/2016.
 *
 * Represents a custom object
 */
public abstract class CustomObject implements Duplicable{

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
            System.err.println("Only Variable<> customObjects are allowed in custom customObjects");
        }
        return null;
    }

    @Override
    public boolean equals(Object other){
        if(other instanceof CustomObject) {
            Field[] fields = getClass().getDeclaredFields();
            Class otherClass = other.getClass();

            for (Field f : fields)
                try {
                    if (!f.equals(otherClass.getDeclaredField(f.getName())))
                        return false;
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            return true;
        }
        return false;
    }

    /**
     * Two customObjects match if they have are the same exact object (same hashcode)
     * @param other the other CustomObject
     * @return whether they match
     */
    public boolean matches(CustomObject other){
        return hashCode() == other.hashCode();
    }

    @Override
    public CustomObject duplicate(){
        try {
            return (CustomObject)this.clone();
        } catch (CloneNotSupportedException e) {
            System.err.println("Error while cloning object @" + this.hashCode());
        }
        return null;
    }

    @Override
    public String toString(){
        return this.getClass().getName() + "(" + hashCode() + ")";
    }
}

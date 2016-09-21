package planner.types;

/**
 * @author Luca Mozzo
 *
 * Describes the ability of an object to create a value-copy of itself
 */
public interface Duplicable {

    /**
     * Create a copy of this instance
     * @return the copy
     */
    Duplicable duplicate();
}

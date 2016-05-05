package plan.domain;

import java.util.LinkedList;

/**
 * Created by LUCA on 01/05/2016.
 *
 * A durative action is an action that has a determined cost (in time) that influences the choice
 */
public class DurativeAction extends Action {

    private float duration;

    /**
     * The constructor allows to add the duration of the action
     * @param preconditions the preconditions
     * @param effects the effects
     * @param duration the duration
     */
    public DurativeAction(LinkedList<Precondition> preconditions, LinkedList<Effect> effects, float duration) {
        super(preconditions, effects);

        this.duration = duration;
    }

    /**
     * Getter for the duration
     * @return the duration
     */
    public float getDuration(){
        return duration;
    }
}

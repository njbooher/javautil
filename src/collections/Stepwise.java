package collections;
import java.util.Iterator;

/**
 * Utilities for creating and using functors
 * 
 * @author Nick Booher
 */
public class Stepwise {

  /**
   * Iterates over something and applies an action to each item
   * 
   * @param items
   *          an iterator for a collection of items
   * @param action
   *          an Action to be applied to each item
   * @return the Action's Reaction to this iteration
   */
  public static <E> Reaction each(Iterator<E> items, Action<E> action) {

    Reaction reaction = Reaction.CONTINUE;

    while (reaction == Reaction.CONTINUE && items.hasNext()) {

      E item = items.next();
      reaction = action.doIt(item);

    }

    if (reaction == Reaction.CONTINUE) {
      return Reaction.STOP_NOTFOUND;
    } else {
      return reaction;
    }

  }

  /**
   * A method wrapper
   */
  public interface Action<E> {

    public Reaction doIt(E item);

  }
  
  /**
   * Possible Reactions to an Action
   */
  public enum Reaction {
    CONTINUE, STOP, STOP_NOTFOUND, STOP_UPDATED;
  }

}

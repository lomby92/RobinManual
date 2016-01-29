package client.model.filter;

/**
 * Created by lmillucci on 29/01/16.
 */
public interface IFilter {

    int[] filter(boolean up, boolean down, boolean left, boolean right, int time);

}

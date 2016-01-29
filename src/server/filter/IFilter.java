package server.filter;

/**
 * Created by lmillucci on 29/01/16.
 */
public interface IFilter {

    public int filterVelocity(int desiredVel, int actualVel, int timer);

}

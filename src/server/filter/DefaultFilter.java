package server.filter;

/**
 * Filtro di default.
 * Restituisce la velocità desiderata senza alcun filtraggio.
 *
 * Created by lmillucci on 29/01/16.
 */
public class DefaultFilter implements IFilter {

    public int filterVelocity(int desiredVel, int actualVel, int timer){
        return desiredVel;
    }
}

package client.model.filter;

/**
 * Filtro di default che non tiene conto della velocita' attuale e restitusice dei valori fiss
 * Created by lmillucci on 29/01/16.
 */
public class DefaultFilter implements IFilter {
    @Override
    public int[] filter(boolean up, boolean down, boolean left, boolean right, int time) {
        int vel[] = new int[2];
        if(left){
            vel[0] = 50;
            vel[1] = 80;
        }else if(right){
            vel[0] = 80;
            vel[1] = 50;
        }
        if(up){
            vel[0] = 80;
            vel[1] = 80;
        }else if(down){
            vel[0] = -50;
            vel[1] = -50;
        }

        return vel;
    }
}

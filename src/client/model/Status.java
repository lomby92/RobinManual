package client.model;

/**
 * Created by lmillucci on 29/01/16.
 */
public class Status {
    private static Status ourInstance = new Status();

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    private boolean isConnected = false;

    public static Status getInstance() {
        return ourInstance;
    }

    private Status() {
    }


}

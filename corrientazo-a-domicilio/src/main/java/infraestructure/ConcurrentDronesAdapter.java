package infraestructure;

import usecases.ConcurrentDronesPort;

public class ConcurrentDronesAdapter implements ConcurrentDronesPort {

    private static ConcurrentDronesPort instance;

    private ConcurrentDronesAdapter() {
    }

    @Override
    public Integer getAmount() {
        return Integer.valueOf(20);
    }

    public static synchronized ConcurrentDronesPort getInstance() {
        if (instance == null) {
            instance = new ConcurrentDronesAdapter();
        }
        return instance;
    }
}

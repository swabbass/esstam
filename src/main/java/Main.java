import java.util.LinkedList;


public class Main {


    private static StamEs es;

    public static void main(String[] args) {
        connectToLocalEs();

        Utils.executeMesurable(Main::startHeavyLoad);
    }

    private static void connectToLocalEs() {
        es = StamEs.getInstance();
    }

    private static void startHeavyLoad() {
        for (long i = 1; i <= 1000000; i++) {
            es.indexStamDocWaitForBulk(Utils.generate());
        }
    }
}

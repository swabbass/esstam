import com.google.gson.Gson;

import java.util.Calendar;
import java.util.Random;
import java.util.UUID;

final class Utils {
    private Utils() {
    }

    private final static Random rnd = new Random();
    public final static Gson gson = new Gson();



    public static void log(Object o) {
        System.out.println(o);
    }

    public static void executeMesurable(Runnable runnable) {
        long time = System.currentTimeMillis();
        runnable.run();
        long dTime = System.currentTimeMillis() - time;
        log(String.format("took %d ms", dTime));
    }

    public static StamDoc generate() {
        String uniqueID = UUID.randomUUID().toString();

        return new StamDoc(uniqueID,
                rnd.nextInt(Integer.MAX_VALUE),
                rnd.nextInt(Integer.MAX_VALUE),
                rnd.nextInt(Integer.MAX_VALUE),
                rnd.nextInt(Integer.MAX_VALUE),
                rnd.nextInt(Integer.MAX_VALUE),
                rnd.nextInt(Integer.MAX_VALUE));
    }
}

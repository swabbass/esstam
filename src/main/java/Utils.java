import com.google.gson.Gson;

import java.util.LinkedList;
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

    public static long executeMesurable(Runnable runnable) {
        long time = System.currentTimeMillis();
        runnable.run();
        long dTime = System.currentTimeMillis() - time;
      //  log(String.format("took %d ms", dTime));
        return dTime;
    }

    public static LinkedList<StamDoc> generate(int count) {
        LinkedList<StamDoc> list = new LinkedList<>();
        for (int i = 0; i < count; ++i) {
            list.addLast(generate());
        }
        return list;
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

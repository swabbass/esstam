import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {


    private static StamEs es;

    public static void main(String[] args) {
        try{
            connectToLocalEs();
            //   Utils.executeMesurable(Main::startHeavyLoad);
            startMultiThreaded(1000); //4000 , 1k per thread  in paralell
            startMultiThreaded(10000);
            startMultiThreaded(100000);
            startMultiThreaded(1000000);
        }catch (Exception e){
            try {
                es.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }


    }

    private static void connectToLocalEs() {
        es = StamEs.getInstance();
    }

    private static void startMultiThreaded(int load) {
        final int MAX_THREAD = 4;
        final ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);

        for (int i = 0; i < MAX_THREAD; i++) {
            ///for each bulk we should have a thread, each one will insert in parallel 1 milion docs
            executorService.execute(()->{
                Utils.executeMesurable(()->{
                    long time=es.indexBulkFromList(Utils.generate(load));
                    Utils.log(String.format("Thread id %d finished in actual time %d",Thread.currentThread().getId(),time));
                });
            });
        }
        executorService.shutdown();

    }


    private static void startHeavyLoad() {
        long time=0;
        for (long i = 1; i <= 1000; i++) {
           time+= es.indexStamDocWaitForBulk(Utils.generate());
        }
        Utils.log("startHeavyLoad took actual time like : "+time);
    }
}

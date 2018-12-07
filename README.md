# esstam

 Each bulk is limited to 1k docs 
 
 <b>NOTE: all are radomly different with different UUIDs for uniqness </b>

 4 threads in pool that simulates 4 loads to index, in the same index! and same type! in parallel

 all times in milliseconds

 local machine es downloaded without any furthur config 

## usage

after downloading es and installing it in your machine set the cluster name and host(default 127.0.0.1) port (default 9300)

```java
  private static final String CLUSTER_NAME = "elasticsearch_warda";//get from http://localhost:9200/?pretty from browser or curl
   private static final String HOST = "127.0.0.1";//leave it if did not change
   private static final int PORT = 9300;//leave it if did not change
```

## the doc it self is small like 

you can change it by changing the class StamDoc just make sure it is a pojo ,gson will parse it to json

```
{"id":"b8bcffe0-9362-4252-a1f9-654ddefbe5fb","m1":1946573399,"m2":510437596,"m3":1201126719,"m4":1411209271,"m5":1020306497,"m6":2019522512}
```

# load of 1k docs for each thread 

Thread id 31 finished in actual time 556

Thread id 29 finished in actual time 620

Thread id 32 finished in actual time 626

Thread id 30 finished in actual time 627

# load of 10k docs for each thread 

Thread id 35 finished in actual time 703

Thread id 36 finished in actual time 746

Thread id 34 finished in actual time 954

Thread id 33 finished in actual time 842


# load of 100k docs for each thread 

Thread id 38 finished in actual time 4953

Thread id 40 finished in actual time 5868

Thread id 37 finished in actual time 6672

Thread id 39 finished in actual time 9276


# load of 1M docs for each thread 

Thread id 42 finished in actual time 52340

Thread id 44 finished in actual time 55676

Thread id 41 finished in actual time 57841

Thread id 43 finished in actual time 61090

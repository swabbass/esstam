import com.google.gson.Gson;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequestBuilder;
import org.elasticsearch.client.AdminClient;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;

public final class StamEs implements Closeable {

    private final static String INDEX_NAME = "stam_index";
    private final static String TYPE_NAME = "stam_type";

    private static final String CLUSTER_NAME = "elasticsearch_warda";
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 9300;
    private static final String KEY_CLUSTER_NAME = "cluster.name";
    private static final int BULK_LIMIT = 1000;

    private static StamEs instance = null;
    private TransportClient client = null;


    private LinkedList<StamDoc> bulkData = new LinkedList<>();


    private StamEs() {
        Settings settings = Settings.builder().
                put(KEY_CLUSTER_NAME, CLUSTER_NAME).build();
        try {
            client = new PreBuiltTransportClient(settings)
                    .addTransportAddress(new TransportAddress(InetAddress.getByName(HOST), PORT));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }


    public static StamEs getInstance() {
        if (instance == null) {
            instance = new StamEs();
        }
        return instance;
    }


    private IndexRequestBuilder createIndexBuilderForStamDoc(StamDoc doc) {
        return client.prepareIndex(INDEX_NAME, TYPE_NAME)
                .setSource(Utils.gson.toJson(doc), XContentType.JSON);
    }

    public void indexStamDocWaitForBulk(StamDoc doc) {
        bulkData.addLast(doc);
        if (bulkData.size() == BULK_LIMIT) {
            BulkRequestBuilder bulkRequestBuilder = client.prepareBulk();
            for (StamDoc tmp : bulkData) {
                bulkRequestBuilder.add(createIndexBuilderForStamDoc(tmp));
            }
            Utils.executeMesurable(() -> {
                BulkResponse bulkItemResponses = bulkRequestBuilder.get();
                Utils.log(bulkItemResponses.status());
            });
            bulkData.clear();
        }
    }

    @Override
    public void close() throws IOException {
        if (client != null) {
            client.close();
        }
    }
}

package com.luanphm.sql.action.RestSql;

import com.luanphm.sql.enums.DatabaseEngine;
import com.luanphm.sql.engines.spark.action.SparkSqlQueryAction;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestCancellableNodeClient;
import org.elasticsearch.xpack.sql.action.SqlQueryRequest;
import org.elasticsearch.xpack.sql.plugin.SqlResponseListener;
import org.elasticsearch.xpack.sql.proto.Protocol;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:44
 */
public abstract class AbstractRestSqlQueryAction extends BaseRestHandler {

    public abstract DatabaseEngine getDatabaseEngine();

    @Override
    public List<Route> routes() {
        return Collections.unmodifiableList(Arrays.asList(
                Route.builder(RestRequest.Method.GET, getDatabaseEngine().sqlQueryEngine.endpoint).build(),
                Route.builder(RestRequest.Method.POST, getDatabaseEngine().sqlQueryEngine.endpoint).build()
        ));
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client)
            throws IOException {
        SqlQueryRequest sqlRequest;
        try (XContentParser parser = request.contentOrSourceParamParser()) {
            sqlRequest = SqlQueryRequest.fromXContent(parser);
        }

        return channel -> {
            RestCancellableNodeClient cancellableClient = new RestCancellableNodeClient(client, request.getHttpChannel());
            cancellableClient.execute(SparkSqlQueryAction.INSTANCE, sqlRequest, new SqlResponseListener(channel, request, sqlRequest));
        };
    }

    @Override
    protected Set<String> responseParams() {
        return Collections.singleton(Protocol.URL_PARAM_DELIMITER);
    }

    @Override
    public String getName() {
        return getDatabaseEngine().name + "_sql_query";
    }

}

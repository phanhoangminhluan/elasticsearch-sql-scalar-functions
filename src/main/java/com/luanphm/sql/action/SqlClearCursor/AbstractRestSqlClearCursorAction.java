package com.luanphm.sql.action.SqlClearCursor;

import com.luanphm.sql.enums.ActionEngine;
import org.elasticsearch.client.node.NodeClient;
import org.elasticsearch.common.xcontent.XContentParser;
import org.elasticsearch.rest.BaseRestHandler;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.rest.action.RestToXContentListener;
import org.elasticsearch.xpack.sql.action.SqlClearCursorAction;
import org.elasticsearch.xpack.sql.action.SqlClearCursorRequest;
import org.elasticsearch.xpack.sql.proto.Protocol;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 10:16
 */
public abstract class AbstractRestSqlClearCursorAction extends BaseRestHandler {


    public abstract ActionEngine getDatabaseEngine();

    @Override
    public List<Route> routes() {
        return Collections.unmodifiableList(Arrays.asList(
                Route.builder(RestRequest.Method.GET, getDatabaseEngine().sqlClearCursor.endpoint).build(),
                Route.builder(RestRequest.Method.POST, getDatabaseEngine().sqlClearCursor.endpoint).build()
        ));
    }

    @Override
    protected RestChannelConsumer prepareRequest(RestRequest request, NodeClient client) throws IOException {
        SqlClearCursorRequest sqlRequest;
        try (XContentParser parser = request.contentParser()) {
            sqlRequest = SqlClearCursorRequest.fromXContent(parser);
        }

        return channel -> client.executeLocally(getDatabaseEngine().sqlClearCursor.action, sqlRequest, new RestToXContentListener<>(channel));
    }

    @Override
    public String getName() {
        return getDatabaseEngine().name + "_sql_clear_cursor";
    }
}

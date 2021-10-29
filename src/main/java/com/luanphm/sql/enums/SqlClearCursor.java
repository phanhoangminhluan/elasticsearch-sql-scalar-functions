package com.luanphm.sql.enums;

import com.luanphm.sql.action.SqlClearCursor.AbstractRestSqlClearCursorAction;
import com.luanphm.sql.action.SqlClearCursor.AbstractSqlClearCursorAction;
import com.luanphm.sql.action.SqlClearCursor.AbstractTransportSqlClearCursorAction;
import com.luanphm.sql.engines.spark.action.SqlClearCursor.SparkRestSqlClearCursorAction;
import com.luanphm.sql.engines.spark.action.SqlClearCursor.SparkSqlClearCursorAction;
import com.luanphm.sql.engines.spark.action.SqlClearCursor.SparkTransportSqlClearCursorAction;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.xpack.sql.action.SqlClearCursorRequest;
import org.elasticsearch.xpack.sql.action.SqlClearCursorResponse;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;
import org.elasticsearch.xpack.sql.proto.Protocol;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 10:20
 */
public enum SqlClearCursor {

    SPARK(
            DatabaseEngine.SPARK.name,
            new SparkSqlClearCursorAction(),
            new SparkRestSqlClearCursorAction(),
            SparkTransportSqlClearCursorAction.class
    );

    public final String actionName;
    public final String endpoint;
    public final AbstractSqlClearCursorAction action;
    public final AbstractRestSqlClearCursorAction restHandler;
    public final Class<? extends AbstractTransportSqlClearCursorAction<? extends PlanExecutor>> transportActionClass;
    public final ActionPlugin.ActionHandler<SqlClearCursorRequest, SqlClearCursorResponse> actionHandler;


    SqlClearCursor(String engine, AbstractSqlClearCursorAction action, AbstractRestSqlClearCursorAction restHandler, Class<? extends AbstractTransportSqlClearCursorAction<? extends PlanExecutor>> transportActionClass) {
        this.actionName = String.format(engine, AbstractSqlClearCursorAction.FORMAT_NAME);
        this.endpoint = "/" + engine + Protocol.CLEAR_CURSOR_REST_ENDPOINT;
        this.action = action;
        this.restHandler = restHandler;
        this.transportActionClass = transportActionClass;
        this.actionHandler = new ActionPlugin.ActionHandler<>(action, transportActionClass);
    }
}

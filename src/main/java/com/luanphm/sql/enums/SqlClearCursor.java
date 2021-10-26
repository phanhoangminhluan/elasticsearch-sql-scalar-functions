package com.luanphm.sql.enums;

import com.luanphm.sql.action.SqlClearCursor.AbstractRestSqlClearCursorAction;
import com.luanphm.sql.action.SqlClearCursor.AbstractSqlClearCursorAction;
import com.luanphm.sql.action.SqlClearCursor.AbstractTransportSqlClearCursorAction;
import com.luanphm.sql.engines.spark.action.SqlClearCursor.SparkRestSqlClearCursorAction;
import com.luanphm.sql.engines.spark.action.SqlClearCursor.SparkSqlClearCursorAction;
import com.luanphm.sql.engines.spark.action.SqlClearCursor.SparkTransportSqlClearCursorAction;
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
    public final Class<? extends AbstractTransportSqlClearCursorAction> transportActionClass;


    SqlClearCursor(String engine, AbstractSqlClearCursorAction action, AbstractRestSqlClearCursorAction restHandler, Class<? extends AbstractTransportSqlClearCursorAction> transportActionClass) {
        this.actionName = String.format(engine, AbstractSqlClearCursorAction.FORMAT_NAME);
        this.endpoint = "/" + engine + Protocol.CLEAR_CURSOR_REST_ENDPOINT;
        this.action = action;
        this.restHandler = restHandler;
        this.transportActionClass = transportActionClass;
    }
}

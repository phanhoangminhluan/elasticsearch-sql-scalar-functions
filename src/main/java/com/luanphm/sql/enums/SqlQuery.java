package com.luanphm.sql.enums;

import com.luanphm.sql.action.SqlQuery.AbstractRestSqlQueryAction;
import com.luanphm.sql.action.SqlQuery.AbstractSqlQueryAction;
import com.luanphm.sql.action.SqlQuery.AbstractTransportSqlQueryAction;
import com.luanphm.sql.engines.spark.action.SqlQuery.SparkRestSqlQueryAction;
import com.luanphm.sql.engines.spark.action.SqlQuery.SparkSqlQueryAction;
import com.luanphm.sql.engines.spark.action.SqlQuery.SparkTransportSqlQueryAction;
import org.elasticsearch.xpack.sql.proto.Protocol;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 23:13
 */
public enum SqlQuery {

    SPARK(
            DatabaseEngine.SPARK.name,
            new SparkSqlQueryAction(),
            new SparkRestSqlQueryAction(),
            SparkTransportSqlQueryAction.class
    );

    public final String actionName;
    public final String endpoint;
    public final AbstractSqlQueryAction action;
    public final AbstractRestSqlQueryAction restHandler;
    public final Class<? extends AbstractTransportSqlQueryAction> transportActionClass;

    SqlQuery(String engine, AbstractSqlQueryAction action, AbstractRestSqlQueryAction restHandler, Class<? extends AbstractTransportSqlQueryAction> transportActionClass) {
        this.actionName = String.format(AbstractSqlQueryAction.FORMAT_NAME, engine);;
        this.endpoint = "/" + engine + Protocol.SQL_QUERY_REST_ENDPOINT;
        this.action = action;
        this.restHandler = restHandler;
        this.transportActionClass = transportActionClass;
    }
}

package com.luanphm.sql.enums;

import com.luanphm.sql.action.RestSql.AbstractSqlQueryAction;
import org.elasticsearch.xpack.sql.proto.Protocol;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 23:13
 */
public enum SqlQueryEngine {

    SPARK("spark");

    public final String actionName;
    public final String endpoint;

    SqlQueryEngine(String engine) {
        this.actionName = String.format(AbstractSqlQueryAction.FORMAT_NAME, engine);;
        this.endpoint = "/" + engine + Protocol.SQL_QUERY_REST_ENDPOINT;;
    }
}

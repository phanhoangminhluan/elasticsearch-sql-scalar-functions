package com.luanphm.plugin.actions.RestSql;

import com.luanphm.plugin.actions.enums.DatabaseEngine;
import org.elasticsearch.action.ActionType;
import org.elasticsearch.xpack.sql.action.SqlQueryResponse;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 21:54
 */
public abstract class AbstractSqlQueryAction extends ActionType<SqlQueryResponse> {

    public static final String FORMAT_NAME = "indices:data/read/%s/sql";

    protected AbstractSqlQueryAction(DatabaseEngine databaseEngine) {
//        super(databaseEngine.sqlQueryEngine.actionName, SqlQueryResponse::new);
        super(databaseEngine.sqlQueryEngine.actionName, SqlQueryResponse::new);
    }

}

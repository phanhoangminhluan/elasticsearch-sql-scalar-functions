package com.luanphm.sql.action.SqlQuery;

import org.elasticsearch.action.ActionType;
import org.elasticsearch.xpack.sql.action.SqlQueryResponse;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 21:54
 */
public abstract class AbstractSqlQueryAction extends ActionType<SqlQueryResponse> {

    public static final String FORMAT_NAME = "indices:data/read/%s_sql";

    protected AbstractSqlQueryAction(String engine) {
        super(String.format(FORMAT_NAME, engine), SqlQueryResponse::new);
    }

}

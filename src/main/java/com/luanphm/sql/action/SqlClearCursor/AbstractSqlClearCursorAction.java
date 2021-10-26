package com.luanphm.sql.action.SqlClearCursor;

import org.elasticsearch.action.ActionType;
import org.elasticsearch.xpack.sql.action.SqlClearCursorResponse;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 10:22
 */
public abstract class AbstractSqlClearCursorAction extends ActionType<SqlClearCursorResponse> {

    public static final String FORMAT_NAME = "indices:data/read/%s_sql/close_cursor";

    public AbstractSqlClearCursorAction(String engine) {
        super(String.format(AbstractSqlClearCursorAction.FORMAT_NAME, engine), SqlClearCursorResponse::new);
    }
}

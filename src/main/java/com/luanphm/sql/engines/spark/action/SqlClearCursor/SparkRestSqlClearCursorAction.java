package com.luanphm.sql.engines.spark.action.SqlClearCursor;

import com.luanphm.sql.action.SqlClearCursor.AbstractRestSqlClearCursorAction;
import com.luanphm.sql.enums.ActionEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 14:17
 */
public class SparkRestSqlClearCursorAction extends AbstractRestSqlClearCursorAction {

    @Override
    public ActionEngine getDatabaseEngine() {
        return SparkTransportSqlClearCursorAction.ACTION_ENGINE;
    }
}

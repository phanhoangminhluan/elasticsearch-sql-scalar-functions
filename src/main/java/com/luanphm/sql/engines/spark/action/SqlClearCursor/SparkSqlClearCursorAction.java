package com.luanphm.sql.engines.spark.action.SqlClearCursor;

import com.luanphm.sql.action.SqlClearCursor.AbstractSqlClearCursorAction;
import com.luanphm.sql.enums.DatabaseEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 14:17
 */
public class SparkSqlClearCursorAction extends AbstractSqlClearCursorAction {

    public SparkSqlClearCursorAction() {
        super(DatabaseEngine.SPARK.name);
    }
}

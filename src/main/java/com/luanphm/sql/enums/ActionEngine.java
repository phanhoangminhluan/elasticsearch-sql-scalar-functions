package com.luanphm.sql.enums;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:02
 */
public enum ActionEngine {

    SPARK(
            DatabaseEngine.SPARK.name,
            SqlQuery.SPARK,
            SqlClearCursor.SPARK
    );

    public final String name;
    public final SqlQuery sqlQueryEngine;
    public final SqlClearCursor sqlClearCursor;

    ActionEngine(String name, SqlQuery sqlQueryEngine, SqlClearCursor sqlClearCursor) {
        this.name = name;
        this.sqlQueryEngine = sqlQueryEngine;
        this.sqlClearCursor = sqlClearCursor;
    }
}

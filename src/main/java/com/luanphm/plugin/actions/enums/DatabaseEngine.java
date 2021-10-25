package com.luanphm.plugin.actions.enums;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:02
 */
public enum DatabaseEngine {

    SPARK("spark", SqlQueryEngine.SPARK);

    public final String name;
    public final SqlQueryEngine sqlQueryEngine;

    DatabaseEngine(String name, SqlQueryEngine sqlQueryEngine) {
        this.name = name;
        this.sqlQueryEngine = sqlQueryEngine;
    }
}

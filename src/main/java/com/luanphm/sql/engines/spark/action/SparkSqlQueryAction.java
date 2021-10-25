package com.luanphm.sql.engines.spark.action;

import com.luanphm.sql.action.RestSql.AbstractSqlQueryAction;
import com.luanphm.sql.enums.DatabaseEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:07
 */
public class SparkSqlQueryAction extends AbstractSqlQueryAction {

    public static final DatabaseEngine DATABASE_ENGINE = DatabaseEngine.SPARK;

    public static final AbstractSqlQueryAction INSTANCE = new SparkSqlQueryAction();

    protected SparkSqlQueryAction() {
        super(DATABASE_ENGINE);
    }

}

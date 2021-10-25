package com.luanphm.sql.engines.spark.action;

import com.luanphm.sql.action.RestSql.AbstractRestSqlQueryAction;
import com.luanphm.sql.enums.DatabaseEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 21:09
 */
public class SparkRestSqlQueryAction extends AbstractRestSqlQueryAction {

    @Override
    public DatabaseEngine getDatabaseEngine() {
        return SparkSqlQueryAction.DATABASE_ENGINE;
    }

}

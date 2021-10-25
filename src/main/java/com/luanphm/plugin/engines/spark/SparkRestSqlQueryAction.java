package com.luanphm.plugin.engines.spark;

import com.luanphm.plugin.actions.RestSql.AbstractRestSqlQueryAction;
import com.luanphm.plugin.actions.enums.DatabaseEngine;

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

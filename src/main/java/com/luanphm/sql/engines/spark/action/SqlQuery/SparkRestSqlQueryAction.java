package com.luanphm.sql.engines.spark.action.SqlQuery;

import com.luanphm.sql.action.SqlQuery.AbstractRestSqlQueryAction;
import com.luanphm.sql.enums.ActionEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 21:09
 */
public class SparkRestSqlQueryAction extends AbstractRestSqlQueryAction {

    @Override
    public ActionEngine getDatabaseEngine() {
        return SparkTransportSqlQueryAction.ACTION_ENGINE;
    }

}

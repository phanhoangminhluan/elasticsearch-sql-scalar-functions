package com.luanphm.sql.engines.spark.action.SqlQuery;

import com.luanphm.sql.action.SqlQuery.AbstractSqlQueryAction;
import com.luanphm.sql.enums.DatabaseEngine;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:07
 */
public class SparkSqlQueryAction extends AbstractSqlQueryAction {

    public SparkSqlQueryAction() {
        super(DatabaseEngine.CDP.name);
    }

}

package com.luanphm.sql.enums;

import com.luanphm.sql.engines.spark.execution.SparkSqlPlanExecutor;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.TriFunction;
import org.elasticsearch.common.io.stream.NamedWriteableRegistry;
import org.elasticsearch.xpack.ql.index.IndexResolver;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:02
 */
public enum ActionEngine {

    SPARK(
            DatabaseEngine.CDP.name,
            SqlQuery.SPARK,
            SqlClearCursor.SPARK,
            SparkSqlPlanExecutor::new
    );

    public final String name;
    public final SqlQuery sqlQueryEngine;
    public final SqlClearCursor sqlClearCursor;
    public final TriFunction<Client, IndexResolver, NamedWriteableRegistry, PlanExecutor> planExecutor;


    ActionEngine(String name, SqlQuery sqlQueryEngine, SqlClearCursor sqlClearCursor, TriFunction<Client, IndexResolver, NamedWriteableRegistry, PlanExecutor> planExecutor) {
        this.name = name;
        this.sqlQueryEngine = sqlQueryEngine;
        this.sqlClearCursor = sqlClearCursor;
        this.planExecutor = planExecutor;
    }
}

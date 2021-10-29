package com.luanphm.sql.engines.spark.execution;

import com.luanphm.sql.engines.spark.expression.function.SparkSqlFunctionRegistry;
import com.luanphm.sql.util.StackTraceUtil;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.io.stream.NamedWriteableRegistry;
import org.elasticsearch.xpack.ql.index.IndexResolver;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.28 23:58
 */
public class SparkSqlPlanExecutor extends PlanExecutor {

    public SparkSqlPlanExecutor(Client client, IndexResolver indexResolver, NamedWriteableRegistry writeableRegistry) {
        super(
                client,
                indexResolver,
                writeableRegistry,
                new SparkSqlFunctionRegistry()
        );

        StackTraceUtil.printStackTrace("new SparkSqlPlanExecutor");
    }
}

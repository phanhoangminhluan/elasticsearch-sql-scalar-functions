package com.luanphm.sql.engines.spark.action.SqlQuery;

import com.luanphm.sql.action.SqlQuery.AbstractTransportSqlQueryAction;
import com.luanphm.sql.engines.spark.execution.SparkSqlPlanExecutor;
import com.luanphm.sql.enums.ActionEngine;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;
import org.elasticsearch.xpack.sql.plugin.SqlLicenseChecker;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:36
 */
public class SparkTransportSqlQueryAction extends AbstractTransportSqlQueryAction<SparkSqlPlanExecutor> {

    public static final ActionEngine ACTION_ENGINE = ActionEngine.SPARK;

    public SparkTransportSqlQueryAction(
            Settings settings,
            ClusterService clusterService,
            TransportService transportService,
            ThreadPool threadPool,
            ActionFilters actionFilters,
            SparkSqlPlanExecutor planExecutor,
            SqlLicenseChecker sqlLicenseChecker
    ) {
        super(
                SparkTransportSqlQueryAction.ACTION_ENGINE,
                settings,
                clusterService,
                transportService,
                threadPool,
                actionFilters,
                planExecutor,
                sqlLicenseChecker
        );
    }

}

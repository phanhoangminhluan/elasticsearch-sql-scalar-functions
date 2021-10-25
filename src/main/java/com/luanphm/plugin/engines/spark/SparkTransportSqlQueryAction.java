package com.luanphm.plugin.engines.spark;

import com.luanphm.plugin.actions.RestSql.AbstractTransportSqlQueryAction;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.util.BigArrays;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.transport.TransportService;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;
import org.elasticsearch.xpack.sql.plugin.SqlLicenseChecker;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 22:36
 */
public class SparkTransportSqlQueryAction extends AbstractTransportSqlQueryAction {


    @Inject
    public SparkTransportSqlQueryAction(
            Settings settings,
            ClusterService clusterService,
            TransportService transportService,
            ThreadPool threadPool,
            ActionFilters actionFilters,
            PlanExecutor planExecutor,
            SqlLicenseChecker sqlLicenseChecker,
            BigArrays bigArrays
    ) {
        super(
                SparkSqlQueryAction.DATABASE_ENGINE,
                SparkSqlQueryAction.INSTANCE,
                settings,
                clusterService,
                transportService,
                threadPool,
                actionFilters,
                planExecutor,
                sqlLicenseChecker,
                bigArrays
        );
    }

}

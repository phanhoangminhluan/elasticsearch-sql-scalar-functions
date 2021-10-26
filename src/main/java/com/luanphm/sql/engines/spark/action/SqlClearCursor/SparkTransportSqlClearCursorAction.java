package com.luanphm.sql.engines.spark.action.SqlClearCursor;

import com.luanphm.sql.action.SqlClearCursor.AbstractTransportSqlClearCursorAction;
import com.luanphm.sql.enums.ActionEngine;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.transport.TransportService;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;
import org.elasticsearch.xpack.sql.plugin.SqlLicenseChecker;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 14:18
 */
public class SparkTransportSqlClearCursorAction extends AbstractTransportSqlClearCursorAction {

    public static final ActionEngine ACTION_ENGINE = ActionEngine.SPARK;

    @Inject
    public SparkTransportSqlClearCursorAction(
            TransportService transportService,
            ActionFilters actionFilters,
            PlanExecutor planExecutor,
            SqlLicenseChecker sqlLicenseChecker
    ) {
        super(
                SparkTransportSqlClearCursorAction.ACTION_ENGINE,
                transportService,
                actionFilters,
                planExecutor,
                sqlLicenseChecker
        );
    }
}

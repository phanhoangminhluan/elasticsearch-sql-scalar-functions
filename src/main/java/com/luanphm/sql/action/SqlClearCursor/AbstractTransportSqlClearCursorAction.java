package com.luanphm.sql.action.SqlClearCursor;

import com.luanphm.sql.enums.ActionEngine;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.ActionFilters;
import org.elasticsearch.action.support.HandledTransportAction;
import org.elasticsearch.common.inject.Inject;
import org.elasticsearch.tasks.Task;
import org.elasticsearch.transport.TransportService;
import org.elasticsearch.xpack.ql.util.StringUtils;
import org.elasticsearch.xpack.sql.action.SqlClearCursorRequest;
import org.elasticsearch.xpack.sql.action.SqlClearCursorResponse;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;
import org.elasticsearch.xpack.sql.plugin.SqlLicenseChecker;
import org.elasticsearch.xpack.sql.proto.Protocol;
import org.elasticsearch.xpack.sql.session.Cursor;
import org.elasticsearch.xpack.sql.session.Cursors;
import org.elasticsearch.xpack.sql.session.SqlConfiguration;
import org.elasticsearch.xpack.sql.util.DateUtils;

import static java.util.Collections.emptyMap;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 13:53
 */
public class AbstractTransportSqlClearCursorAction<T extends PlanExecutor> extends HandledTransportAction<SqlClearCursorRequest, SqlClearCursorResponse> {
    private final PlanExecutor planExecutor;
    private final SqlLicenseChecker sqlLicenseChecker;

    @Inject
    public AbstractTransportSqlClearCursorAction(ActionEngine actionEngine,
                                                 TransportService transportService,
                                                 ActionFilters actionFilters,
                                                 T planExecutor,
                                                 SqlLicenseChecker sqlLicenseChecker
    ) {
        super(actionEngine.sqlClearCursor.actionName, transportService, actionFilters, SqlClearCursorRequest::new);
        this.planExecutor = planExecutor;
        this.sqlLicenseChecker = sqlLicenseChecker;
    }

    @Override
    protected void doExecute(Task task, SqlClearCursorRequest request, ActionListener<SqlClearCursorResponse> listener) {
        sqlLicenseChecker.checkIfSqlAllowed(request.mode());
        operation(planExecutor, request, listener);
    }

    public static void operation(PlanExecutor planExecutor, SqlClearCursorRequest request,
                                 ActionListener<SqlClearCursorResponse> listener) {
        Cursor cursor = Cursors.decodeFromStringWithZone(request.getCursor()).v1();
        planExecutor.cleanCursor(
                new SqlConfiguration(DateUtils.UTC, Protocol.FETCH_SIZE, Protocol.REQUEST_TIMEOUT, Protocol.PAGE_TIMEOUT, null,
                        request.mode(), StringUtils.EMPTY, request.version(), StringUtils.EMPTY, StringUtils.EMPTY,
                        Protocol.FIELD_MULTI_VALUE_LENIENCY, Protocol.INDEX_INCLUDE_FROZEN),
                cursor, ActionListener.wrap(
                        success -> listener.onResponse(new SqlClearCursorResponse(success)), listener::onFailure));
    }
}

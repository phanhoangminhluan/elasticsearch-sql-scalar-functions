package com.luanphm.sql.engines.spark.plugin;

import com.luanphm.sql.enums.ActionEngine;
import com.luanphm.sql.plugin.AbstractSqlScalarFunctionPlugin;
import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.common.io.stream.NamedWriteableRegistry;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.xpack.ql.index.IndexResolver;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.24 21:05
 */
public final class SparkSqlScalarFunctionPlugin extends AbstractSqlScalarFunctionPlugin {

    public SparkSqlScalarFunctionPlugin(Settings settings) {
        super(settings);
    }

    private final ActionEngine ACTION_ENGINE = ActionEngine.SPARK;

    @Override
    public List<RestHandler> getRestHandlers(
            Settings settings,
            RestController restController,
            ClusterSettings clusterSettings,
            IndexScopedSettings indexScopedSettings,
            SettingsFilter settingsFilter,
            IndexNameExpressionResolver indexNameExpressionResolver,
            Supplier<DiscoveryNodes> nodesInCluster
    ) {

        List<RestHandler> restHandlers = new ArrayList<>();

        restHandlers.add(ACTION_ENGINE.sqlQueryEngine.restHandler);
        restHandlers.add(ACTION_ENGINE.sqlClearCursor.restHandler);

        return restHandlers;
    }

    @Override
    public List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions() {

        List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>>  actionHandlers = new ArrayList<>();

        actionHandlers.add(ACTION_ENGINE.sqlQueryEngine.actionHandler);
        actionHandlers.add(ACTION_ENGINE.sqlClearCursor.actionHandler);

        return actionHandlers;
    }

    @Override
    public PlanExecutor getPlanExecutor(Client client, IndexResolver indexResolver, NamedWriteableRegistry namedWriteableRegistry) {
        return ACTION_ENGINE.planExecutor.apply(client, indexResolver, namedWriteableRegistry);
    }
}

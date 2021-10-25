/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */
package org.elasticsearch.xpack.sql.plugin;

import org.elasticsearch.action.ActionRequest;
import org.elasticsearch.action.ActionResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.cluster.metadata.IndexNameExpressionResolver;
import org.elasticsearch.cluster.node.DiscoveryNodes;
import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.common.inject.Module;
import org.elasticsearch.common.io.stream.NamedWriteableRegistry;
import org.elasticsearch.common.settings.ClusterSettings;
import org.elasticsearch.common.settings.IndexScopedSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.settings.SettingsFilter;
import org.elasticsearch.common.xcontent.NamedXContentRegistry;
import org.elasticsearch.env.Environment;
import org.elasticsearch.env.NodeEnvironment;
import org.elasticsearch.license.LicenseUtils;
import org.elasticsearch.license.XPackLicenseState;
import org.elasticsearch.plugins.ActionPlugin;
import org.elasticsearch.plugins.Plugin;
import org.elasticsearch.repositories.RepositoriesService;
import org.elasticsearch.rest.RestController;
import org.elasticsearch.rest.RestHandler;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.threadpool.ThreadPool;
import org.elasticsearch.watcher.ResourceWatcherService;
import org.elasticsearch.xpack.core.XPackPlugin;
import org.elasticsearch.xpack.ql.index.IndexResolver;
import org.elasticsearch.xpack.sql.SqlFeatureSet;
import org.elasticsearch.xpack.sql.action.SqlClearCursorAction;
import org.elasticsearch.xpack.sql.action.SqlQueryAction;
import org.elasticsearch.xpack.sql.action.SqlTranslateAction;
import org.elasticsearch.xpack.sql.execution.PlanExecutor;
import org.elasticsearch.xpack.sql.type.SqlDataTypeRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class SqlPlugin extends Plugin implements ActionPlugin {

    private final SqlLicenseChecker sqlLicenseChecker = new SqlLicenseChecker(
        (mode) -> {
            XPackLicenseState licenseState = getLicenseState();
            switch (mode) {
                case JDBC:
                    if (licenseState.checkFeature(XPackLicenseState.Feature.JDBC) == false) {
                        throw LicenseUtils.newComplianceException("jdbc");
                    }
                    break;
                case ODBC:
                    if (licenseState.checkFeature(XPackLicenseState.Feature.ODBC) == false) {
                        throw LicenseUtils.newComplianceException("odbc");
                    }
                    break;
                case PLAIN:
                case CLI:
                    break;
                default:
                    throw new IllegalArgumentException("Unknown SQL mode " + mode);
            }
        }
    );

    // remove unneeded Settings
    public SqlPlugin(Settings settings) {
    }

    // overridable by tests
    protected XPackLicenseState getLicenseState() { return XPackPlugin.getSharedLicenseState(); }

    @Override
    public Collection<Object> createComponents(Client client, ClusterService clusterService, ThreadPool threadPool,
                                               ResourceWatcherService resourceWatcherService, ScriptService scriptService,
                                               NamedXContentRegistry xContentRegistry, Environment environment,
                                               NodeEnvironment nodeEnvironment, NamedWriteableRegistry namedWriteableRegistry,
                                               IndexNameExpressionResolver expressionResolver,
                                               Supplier<RepositoriesService> repositoriesServiceSupplier) {

        return createComponents(client, clusterService.getClusterName().value(), namedWriteableRegistry);
    }

    /**
     * Create components used by the sql plugin.
     */
    Collection<Object> createComponents(Client client, String clusterName, NamedWriteableRegistry namedWriteableRegistry) {
        IndexResolver indexResolver = new IndexResolver(client, clusterName, SqlDataTypeRegistry.INSTANCE);
        return Arrays.asList(sqlLicenseChecker, indexResolver, new PlanExecutor(client, indexResolver, namedWriteableRegistry));
    }

    @Override
    public Collection<Module> createGuiceModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(b -> XPackPlugin.bindFeatureSet(b, SqlFeatureSet.class));
        return modules;
    }

    @Override
    public List<RestHandler> getRestHandlers(Settings settings, RestController restController,
                                             ClusterSettings clusterSettings, IndexScopedSettings indexScopedSettings,
                                             SettingsFilter settingsFilter, IndexNameExpressionResolver indexNameExpressionResolver,
                                             Supplier<DiscoveryNodes> nodesInCluster) {

        return Arrays.asList(
                new RestSqlQueryAction(),
                new RestSqlTranslateAction(),
                new RestSqlClearCursorAction(),
                new RestSqlStatsAction(),
                new RestSqlAsyncGetResultsAction(),
                new RestSqlAsyncGetStatusAction(),
                new RestSqlAsyncDeleteResultsAction()
        );
    }

    @Override
    public List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions() {
        return Arrays.asList(new ActionHandler<>(SqlQueryAction.INSTANCE, TransportSqlQueryAction.class),
                new ActionHandler<>(SqlTranslateAction.INSTANCE, TransportSqlTranslateAction.class),
                new ActionHandler<>(SqlClearCursorAction.INSTANCE, TransportSqlClearCursorAction.class),
                new ActionHandler<>(SqlStatsAction.INSTANCE, TransportSqlStatsAction.class),
                new ActionHandler<>(SqlAsyncGetResultsAction.INSTANCE, TransportSqlAsyncGetResultsAction.class),
                new ActionHandler<>(SqlAsyncGetStatusAction.INSTANCE, TransportSqlAsyncGetStatusAction.class));
    }
}

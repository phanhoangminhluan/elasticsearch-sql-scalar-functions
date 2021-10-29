package com.luanphm.sql.plugin;

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
import org.elasticsearch.xpack.sql.execution.PlanExecutor;
import org.elasticsearch.xpack.sql.plugin.SqlLicenseChecker;
import org.elasticsearch.xpack.sql.type.SqlDataTypeRegistry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.29 00:20
 */
public abstract class AbstractSqlScalarFunctionPlugin extends Plugin implements ActionPlugin {

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
    public AbstractSqlScalarFunctionPlugin(Settings settings) {
    }

    // overridable by tests
    private final XPackLicenseState getLicenseState() { return XPackPlugin.getSharedLicenseState(); }

    @Override
    public final Collection<Object> createComponents(Client client, ClusterService clusterService, ThreadPool threadPool,
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
    final Collection<Object> createComponents(Client client, String clusterName, NamedWriteableRegistry namedWriteableRegistry) {
        IndexResolver indexResolver = new IndexResolver(client, clusterName, SqlDataTypeRegistry.INSTANCE);
        return Arrays.asList(
                sqlLicenseChecker,
                indexResolver,
                getPlanExecutor(client, indexResolver, namedWriteableRegistry)
        );
    }

    @Override
    public final Collection<Module> createGuiceModules() {
        List<Module> modules = new ArrayList<>();
        modules.add(b -> XPackPlugin.bindFeatureSet(b, SqlFeatureSet.class));
        return modules;
    }


    @Override
    public abstract List<RestHandler> getRestHandlers(
            Settings settings,
            RestController restController,
            ClusterSettings clusterSettings,
            IndexScopedSettings indexScopedSettings,
            SettingsFilter settingsFilter,
            IndexNameExpressionResolver indexNameExpressionResolver,
            Supplier<DiscoveryNodes> nodesInCluster
    );

    @Override
    public abstract List<ActionHandler<? extends ActionRequest, ? extends ActionResponse>> getActions();

    public abstract PlanExecutor getPlanExecutor(Client client, IndexResolver indexResolver, NamedWriteableRegistry namedWriteableRegistry);

}

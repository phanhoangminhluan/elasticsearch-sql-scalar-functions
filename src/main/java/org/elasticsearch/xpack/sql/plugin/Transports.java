/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.sql.plugin;

import org.elasticsearch.cluster.service.ClusterService;
import org.elasticsearch.xpack.core.security.SecurityContext;

public final class Transports {

    private Transports() {}

    public static String username(SecurityContext securityContext) {
        return securityContext != null && securityContext.getUser() != null ? securityContext.getUser().principal() : null;
    }

    public static String clusterName(ClusterService clusterService) {
        return clusterService.getClusterName().value();
    }
}

package com.bloxbean.cardano.yacicli.commands.localcluster.common;

import com.bloxbean.cardano.yaci.helper.LocalClientProvider;
import com.bloxbean.cardano.yacicli.commands.localcluster.ClusterService;
import com.bloxbean.cardano.yacicli.common.CommandContext;
import org.springframework.stereotype.Component;

import java.nio.file.Path;

import static com.bloxbean.cardano.yacicli.commands.localcluster.ClusterCommands.CLUSTER_NAME;

@Component
public class LocalClientProviderHelper {
    private ClusterService localClusterService;

    public LocalClientProviderHelper(ClusterService clusterService) {
        this.localClusterService = clusterService;
    }

    public LocalClientProvider getLocalClientProvider() throws Exception {
        String clusterName = CommandContext.INSTANCE.getProperty(CLUSTER_NAME);
        long protocolMagic = localClusterService.getClusterInfo(clusterName).getProtocolMagic();
        String socketPath = localClusterService.getClusterInfo(clusterName).getSocketPath();

        LocalClientProvider localClientProvider = new LocalClientProvider(socketPath, protocolMagic);
        return localClientProvider;
    }

    public Path getClusterFolder() {
        String clusterName = CommandContext.INSTANCE.getProperty(CLUSTER_NAME);
        return localClusterService.getClusterFolder(clusterName);
    }
}

package com.distribution;

import com.distribution.container.NodeDetails;
import com.distribution.threads.ConfiguringNodesThread;
import com.distribution.threads.WaitDataToNodesThread;

public class MainApp {
    public static void main(String[] args) {

        ConfiguringNodesThread configuringNodesThread= new ConfiguringNodesThread();
        NodeDetails.EXECUTOR.submit(configuringNodesThread);

        WaitDataToNodesThread waitDataToNodesThread= new WaitDataToNodesThread();
        NodeDetails.EXECUTOR.submit(waitDataToNodesThread);

    }
}

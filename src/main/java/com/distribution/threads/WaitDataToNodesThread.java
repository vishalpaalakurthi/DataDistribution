package com.distribution.threads;

import com.distribution.container.NodeDetails;
import com.distribution.model.NodeDetailModel;
import com.distribution.service.NodeDistributionService;

public class WaitDataToNodesThread extends Thread {

    NodeDistributionService distributionService = new NodeDistributionService();

    @Override
    public void run() {
        System.out.println("RUNNING >>>>>>>>>>>> WaitDataToNodesThread");
        try {
            while (true) {

                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!NodeDetails.WAIT_DATA.isEmpty()) {
                    Integer value = NodeDetails.WAIT_DATA.peek();
                    NodeDetailModel nodeDetailModel = distributionService.getAvailableNodesToAddValue(value);
                    if (nodeDetailModel.isAnyNodeAvailableForAdding()) {
                        NodeDetails.WAIT_DATA.remove();
                        distributionService.addValueToAvailableNodes(nodeDetailModel, value);
                    } else {
                        System.out.println("Free Nodes are not available");
                        System.out.println("Waiting Data: "+ NodeDetails.WAIT_DATA);

                        while (!nodeDetailModel.isAnyNodeAvailableForAdding()) {
                            nodeDetailModel = distributionService.getAvailableNodesToAddValue(value);
                            if (nodeDetailModel.isAnyNodeAvailableForAdding()) {
                                NodeDetails.WAIT_DATA.remove();
                                System.out.println("Added after waiting Data: " + value);
                                distributionService.addValueToAvailableNodes(nodeDetailModel, value);
                                System.out.println("Waiting Data: " + NodeDetails.WAIT_DATA);

                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

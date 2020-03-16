package com.distribution.service;

import com.distribution.container.NodeDetails;
import com.distribution.model.NodeDetailModel;

import java.util.*;

public class NodeDistributionService {

    public void createNodesDetails(Integer limit) {
        NodeDetails.NODES_LIMIT.add(limit);
        NodeDetails.NODES_DATA.add(0);
    }

    public void addToNodes(Integer value) {
        NodeDetails.WAIT_DATA.add(value);
    }

    public void removeFromNode(int nodeNumber, Integer value) {
        Integer value1 = NodeDetails.NODES_DATA.get(nodeNumber);
        value1 = value1 - value;
        value1 = Math.max(value1, 0);
        NodeDetails.NODES_DATA.set(nodeNumber, value1);
        System.out.println("Nodes Details: " + NodeDetails.NODES_DATA);
    }

    public void addValueToAvailableNodes(NodeDetailModel nodeDetailModel, Integer value) {
        List<String> availableNodes = nodeDetailModel.getAvailableNodes();
        int remaining = 0;
        for (String nodeDataValue : availableNodes) {
            String[] nodeData = nodeDataValue.split("-");

            remaining = value - Integer.parseInt(nodeData[0]);
            remaining = Math.max(remaining, 0);

            Integer value1 = NodeDetails.NODES_DATA.get(Integer.parseInt(nodeData[1]));
            value1 = value1 + (value - remaining);
            NodeDetails.NODES_DATA.set(Integer.parseInt(nodeData[1]), value1);

            System.out.println("Nodes Details: " + NodeDetails.NODES_DATA);
            if (remaining == 0) {
                break;
            } else {
                value = remaining;
            }
        }
    }

    public NodeDetailModel getAvailableNodesToAddValue(int required) {
        List<String> availedNodes = new ArrayList<String>();

        int numberOfNodesAvail = NodeDetails.NODES_DATA.size();
        int requiredCount = 0;

        for (int i = 0; i < numberOfNodesAvail; i++) {
            Integer diff = NodeDetails.NODES_LIMIT.get(i) - NodeDetails.NODES_DATA.get(i);
            String formattedValue = String.format("%d-%d", diff, i);
            requiredCount = requiredCount + diff;
            availedNodes.add(formattedValue);
        }

        Collections.sort(availedNodes);

        NodeDetailModel nodeDetailModel = new NodeDetailModel();
        nodeDetailModel.setAvailableNodes(availedNodes);
        nodeDetailModel.setAnyNodeAvailableForAdding(required <= requiredCount);

        return nodeDetailModel;
    }
}

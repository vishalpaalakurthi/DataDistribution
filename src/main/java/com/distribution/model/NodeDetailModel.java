package com.distribution.model;

import java.util.List;

public class NodeDetailModel {
    List<String> availableNodes= null;
    boolean isAnyNodeAvailableForAdding= false;

    public List<String> getAvailableNodes() {
        return availableNodes;
    }

    public void setAvailableNodes(List<String> availableNodes) {
        this.availableNodes = availableNodes;
    }

    public boolean isAnyNodeAvailableForAdding() {
        return isAnyNodeAvailableForAdding;
    }

    public void setAnyNodeAvailableForAdding(boolean anyNodeAvailableForAdding) {
        isAnyNodeAvailableForAdding = anyNodeAvailableForAdding;
    }
}

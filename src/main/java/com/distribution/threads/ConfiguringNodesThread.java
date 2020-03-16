package com.distribution.threads;

import com.distribution.container.NodeDetails;
import com.distribution.service.NodeDistributionService;

import java.util.Scanner;

public class ConfiguringNodesThread extends Thread {

    @Override
    public void run() {
        System.out.println("Entered >>>>>>>>> configuringNodesThread");

        NodeDistributionService distributionService= new NodeDistributionService();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Number Nodes Required: ");
        int nodesRequired = sc.nextInt();
        for (int i= 0; i< nodesRequired; i++){

            System.out.println("limit for Node-"+(i)+":");
            Integer limit= sc.nextInt();
            distributionService.createNodesDetails(limit);
        }

        System.out.println("Nodes Details: "+ NodeDetails.NODES_DATA);

        int selection= 0;
        while (selection != 5 ) {
            System.out.println("Select Option: \n" +
                    "1: Add count to nodes \n" +
                    "2: Remove count from nodes \n" +
                    "3: Get Nodes Data \n" +
                    "4: Get Nodes Wait Data \n" +
                    "5: Exit");
            selection = sc.nextInt();
            switch (selection){
                case 1:
                    System.out.println("Enter Value To Add:");
                    Integer valueToAdd= sc.nextInt();
                    distributionService.addToNodes(valueToAdd);
                    break;
                case 2:
                    System.out.println("Enter Node Number for Add/Remove Data: ");
                    int nodeNumber= sc.nextInt();
                    while (nodeNumber > (NodeDetails.NODES_LIMIT.size() - 1)){
                        System.out.println("Enter Valid Node Number: ");
                        nodeNumber= sc.nextInt();
                    }
                    System.out.println("Enter Value To Remove:");
                    Integer valueToRemove= sc.nextInt();
                    distributionService.removeFromNode(nodeNumber, valueToRemove);
                    break;
                case 3:
                    System.out.println("Nodes Details: "+ NodeDetails.NODES_DATA);
                    break;
                case 4:
                    System.out.println("Waiting Data: "+ NodeDetails.WAIT_DATA);
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Wrong Selection, Choose Another Option");
                    break;
            }
        }
    }
}

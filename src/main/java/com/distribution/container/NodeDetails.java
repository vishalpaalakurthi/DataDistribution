package com.distribution.container;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NodeDetails {

    public static ExecutorService EXECUTOR= Executors.newFixedThreadPool(5);

    public static List<Integer> NODES_LIMIT = new ArrayList<Integer>();
    public static List<Integer> NODES_DATA = new ArrayList<Integer>();
    public static Queue<Integer> WAIT_DATA= new LinkedList<Integer>();
}

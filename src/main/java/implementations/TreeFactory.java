package implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String line : input) {
            int[] array = Arrays.stream(line.split("\\s+")).mapToInt(Integer::parseInt).toArray();
            addEdge(array[0], array[1]);
        }
        return getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Tree<Integer> tree : nodesByKeys.values()) {
            if (tree.getParent() == null) {
                return tree;
            }
        }
        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {
        Tree<Integer> parentTree = this.createNodeByKey(parent);
        Tree<Integer> childTree = this.createNodeByKey(child);
        parentTree.addChild(childTree);
        childTree.setParent(parentTree);
    }

}




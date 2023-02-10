package a_24_binary_tree;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreeBaseLinked { 
    TreeNode tree;
    public static void main(String[] args) {
        BinaryTreeBaseLinked binTree = new BinaryTreeBaseLinked();
        int[] data = new int[]{33,16,50,13,18,15,17,25,19,27,34,58,51,66,55};
        for (int i : data) {
            binTree.add(i);
        }
        // binTree.printIn();

        binTree.printLevel();
        System.out.println(binTree.find(16));
        System.out.println(binTree.find(1));
        binTree.remove(13);
        binTree.remove(18);
        binTree.remove(55);
        binTree.printLevel();
    }

    public void printLevel() {
        List<List<Integer>> list = new ArrayList<>();
        levelTraversal(list, tree, 0);
        for (List<Integer> l : list) {
            for (Integer i:l) {
                System.out.print(i + " ");
            }
            System.out.println();
        }
    }

    private void levelTraversal(List<List<Integer>> list, TreeNode node, int idx) {
        if (node == null) return;
        if (idx >= list.size()) list.add(new ArrayList<>());
        list.get(idx).add(node.data);
        levelTraversal(list, node.left, idx+1);
        levelTraversal(list, node.right, idx+1);
    }


    public TreeNode find(int data) {
        TreeNode p = tree;
        while (p != null) {
            if (p.data < data) {
                p = p.right;
            } else if (p.data > data){
                p = p.left;
            } else return p;
        }
        return null;
    }

    public void add(int data) {
        if (tree == null) {
            tree = new TreeNode(data);
            return;
        }
        TreeNode p = tree;
        while (p != null) {
            if (p.data < data) {
                if (p.right == null) {
                    p.right = new TreeNode(data);
                    return;
                }
                p = p.right;
            } else {
                if (p.left == null) {
                    p.left = new TreeNode(data);
                    return;
                }
                p = p.left;
            }
        }
    }

    public void remove(int data) {
        TreeNode p = tree;
        TreeNode pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (p.data > data) {
                p = p.left;
            } else p = p.right;
        }
        if (p == null) return;
        if (p.left != null && p.right != null) {
            TreeNode mp = p.right;
            TreeNode mpp = p;
            while (mp.left != null) {
                mpp = mp;
                mp = mp.left;
            }
            p.data = mp.data;
            p = mp;
            pp = mpp;
        }
        TreeNode child;
        if (p.left != null) child = p.left;
        else if (p.right != null) child = p.right;
        else child = null;

        if (pp == null) tree = null;
        else if (pp.left == p) pp.left = child;
        else pp.right = child;
    }

    class TreeNode {
        private int data;
        private TreeNode left;
        private TreeNode right;
        public TreeNode(int data) {
            this.data = data;
        }
        @Override
        public String toString() {
            return String.format("{%d}", data);
        }
    }
}

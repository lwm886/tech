package com.tech.data.simple.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author lw
 * @since 2024/2/28
 */
public class TreeSample {

    public static void main(String[] args) {
        int arr[]={1,2,3,4,5,6};
        TreeSample sample = new TreeSample();
        
        //构建TREE
        TreeNode tree = sample.buildTree(arr, 0);

        //层级遍历（按层级自上而下逐层遍历，同层自左向右遍历）
        sample.levelOrder(tree);
        System.out.println();

        //前序遍历（根节点、左子树、右子树）
        sample.preOrder(tree);
        System.out.println();
        
        //中序遍历（左子树、根节点、右子树）
        sample.inOrder(tree);
        System.out.println();
        
        //后序遍历（左子树、右子树、根节点）
        sample.postOrder(tree);
        System.out.println();
        
    }

    /**
     * 基于数组构建TREE
     * @param arr 数组
     * @param index 下标，构建树时设置为0
     * @return TreeNode
     */
    TreeNode buildTree(int[] arr, int index) {
        TreeNode treeNode = null;
        if (index < arr.length) {
            treeNode = new TreeNode(arr[index], null, null);
            treeNode.left=buildTree(arr,2*index+1);
            treeNode.right=buildTree(arr,2*index+2);
        }
        return treeNode;
    }

    /**
     * 前序遍历（根节点、左子树、右子树）
     * @param treeNode 根节点
     */
    void preOrder(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        System.out.print(treeNode.val+" ");
        preOrder(treeNode.left);
        preOrder(treeNode.right);
    }
    
    /**
     * 中序遍历（左子树、根节点、右子树）
     * @param treeNode 根节点
     */
    void inOrder(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        inOrder(treeNode.left);
        System.out.print(treeNode.val+ " ");
        inOrder(treeNode.right);
    }

    /**
     * 后序遍历（左子树、右子树、根节点）
     * @param treeNode 根节点
     */
    void postOrder(TreeNode treeNode){
        if(treeNode == null){
            return;
        }
        postOrder(treeNode.left);
        postOrder(treeNode.right);
        System.out.print(treeNode.val+ " ");
    }

    /**
     * 层级遍历（按层级自上而下逐层遍历，同层自左向右遍历）
     * @param treeNode
     */
    void levelOrder(TreeNode treeNode){
        if(treeNode==null){
            return;
        }
        Queue<TreeNode> queue=new LinkedList<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                System.out.print(node.val+" ");
                if(node.left!=null){
                    queue.offer(node.left);
                }
                if(node.right!=null){
                    queue.offer(node.right);
                }
            }
            
        }
        
        
        
        
    }
    
    
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "val=" + val +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
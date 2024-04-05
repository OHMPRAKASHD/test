class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    
    TreeNode(int x) {
        val = x;
        left = null;
        right = null;
    }
}

public class RecoverBST {
    TreeNode firstElement = null;
    TreeNode secondElement = null;
    TreeNode prevElement = new TreeNode(Integer.MIN_VALUE);

    public void recoverTree(TreeNode root) {
        // Morris Traversal
        TreeNode current = root;
        while (current != null) {
            if (current.left == null) {
                // visit current
                if (prevElement.val > current.val) {
                    if (firstElement == null) {
                        firstElement = prevElement;
                        secondElement = current;
                    } else {
                        secondElement = current;
                    }
                }
                prevElement = current;
                current = current.right;
            } else {
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }
                if (predecessor.right == null) {
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // visit current
                    if (prevElement.val > current.val) {
                        if (firstElement == null) {
                            firstElement = prevElement;
                            secondElement = current;
                        } else {
                            secondElement = current;
                        }
                    }
                    prevElement = current;
                    predecessor.right = null;
                    current = current.right;
                }
            }
        }
        // Swap the values of the two misplaced nodes
        if (firstElement != null && secondElement != null) {
            int temp = firstElement.val;
            firstElement.val = secondElement.val;
            secondElement.val = temp;
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        
        RecoverBST recoverBST = new RecoverBST();
        recoverBST.recoverTree(root);
        
        System.out.println("Recovery complete.");
    }
}

class AVLNode {
    int key;
    AVLNode left, right;
    int height = 1;

    AVLNode(int key) {
        this.key = key;
    }

    static int height(AVLNode n) {
        return n == null ? 0 : n.height;
    }

    static int balance(AVLNode n) {
        return n == null ? 0 : height(n.left) - height(n.right);
    }

    static void updateHeight(AVLNode n) {
        if (n != null) {
            n.height = 1 + Math.max(height(n.left), height(n.right));
        }
    }

    // TODO 1
    static AVLNode rotateRight(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    // TODO 2
    static AVLNode rotateLeft(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    // TODO 3
    static AVLNode insert(AVLNode node, int key) {

        if (node == null)
            return new AVLNode(key);

        if (key < node.key)
            node.left = insert(node.left, key);
        else if (key > node.key)
            node.right = insert(node.right, key);
        else
            return node;

        updateHeight(node);

        int bf = balance(node);

        // LL Case
        if (bf > 1 && key < node.left.key)
            return rotateRight(node);

        // RR Case
        if (bf < -1 && key > node.right.key)
            return rotateLeft(node);

        // LR Case
        if (bf > 1 && key > node.left.key) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        }

        // RL Case
        if (bf < -1 && key < node.right.key) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        }

        return node;
    }

    static void inorder(AVLNode root) {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.key + " ");
            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        int[] packages = {
            18, 28, 32, 38, 42, 48,
            52, 58, 62, 68, 72, 78
        };

        AVLNode root = null;

        for (int id : packages) {
            root = insert(root, id);
        }

        System.out.println("Inorder Traversal:");
        inorder(root);

        System.out.println("\nRoot Node: " + root.key);
        System.out.println("Tree Height: " + (height(root) - 1));
    }
}
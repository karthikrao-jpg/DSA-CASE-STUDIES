class BPlusNode {
    boolean isLeaf;
    int[] keys;
    BPlusNode[] children;
    BPlusNode next;

    BPlusNode(boolean isLeaf, int[] keys) {
        this.isLeaf = isLeaf;
        this.keys = keys;
    }
}

public class BPlusTreeRangeCount {

    static int rangeCount(BPlusNode root, int lo, int hi) {

        // Find first leaf containing lo
        BPlusNode node = root;

        while (!node.isLeaf) {
            int i = 0;

            while (i < node.keys.length &&
                   lo >= node.keys[i]) {
                i++;
            }

            node = node.children[i];
        }

        int count = 0;

        // Scan only relevant leaves
        while (node != null) {

            for (int key : node.keys) {

                // Stop immediately when keys exceed hi
                if (key > hi) {
                    return count;
                }

                if (key >= lo && key <= hi) {
                    count++;
                }
            }

            node = node.next;
        }

        return count;
    }

    public static void main(String[] args) {

        // Leaf Nodes
        BPlusNode leaf1 = new BPlusNode(true,
                new int[]{1000, 3000, 5000, 7000});

        BPlusNode leaf2 = new BPlusNode(true,
                new int[]{8000, 12000, 12300, 12900});

        BPlusNode leaf3 = new BPlusNode(true,
                new int[]{13500, 14100, 14700, 15400});

        BPlusNode leaf4 = new BPlusNode(true,
                new int[]{16200, 18000, 22000, 35000});

        // Leaf Chain
        leaf1.next = leaf2;
        leaf2.next = leaf3;
        leaf3.next = leaf4;

        // Root Node
        BPlusNode root = new BPlusNode(false,
                new int[]{8000, 12000});

        root.children = new BPlusNode[]{
                leaf1,
                leaf2,
                leaf3
        };

        int lo = 12000;
        int hi = 14800;

        int result = rangeCount(root, lo, hi);

        System.out.println(
                "Count in range [" +
                lo + ", " + hi +
                "] = " + result);
    }
}
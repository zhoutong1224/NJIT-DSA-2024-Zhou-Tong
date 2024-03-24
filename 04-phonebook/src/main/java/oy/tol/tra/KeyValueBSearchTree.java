package oy.tol.tra;

public class KeyValueBSearchTree<K extends Comparable<K>, V> implements Dictionary<K, V> {

    private TreeNode<K, V> root;
    private int count = 0;
    private int maxTreeDepth = 0;

    @Override
    public Type getType() {
        return Type.BST;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public String getStatus() {
        StringBuilder builder = new StringBuilder();
        builder.append("Tree has max depth of ").append(maxTreeDepth).append(".\n");
        builder.append("Longest collision chain in a tree node is ").append(TreeNode.longestCollisionChain).append("\n");
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        builder.append("Min path height to bottom: ").append(visitor.minHeight).append("\n");
        builder.append("Max path height to bottom: ").append(visitor.maxHeight).append("\n");
        builder.append("Ideal height if balanced: ").append(Math.ceil(Math.log(count))).append("\n");
        return builder.toString();
    }

    @Override
    public boolean add(K key, V value) throws IllegalArgumentException, OutOfMemoryError {
        if (key == null || value == null) {
            throw new IllegalArgumentException("The key or value should not be null!");
        }
        if (root == null) {
            root = new TreeNode<>(key, value);
            count++;
            return true;
        } else {
            int addPoint = root.insert(key, value, key.hashCode());
            if (addPoint > 0) {
                count++;
                return true;
            } else {
                return false;
            }
        }
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key could not be null!");
        }
        return root.find(key, key.hashCode());
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // 无需实现，树结构不需要容量调整
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        root.accept(visitor);
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // 无需实现，因为BST不像基于数组的结构那样使用额外的空间
    }
}
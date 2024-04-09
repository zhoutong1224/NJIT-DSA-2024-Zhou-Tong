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
        String status = "Tree has max depth of " + maxTreeDepth + ".\n";
        status += "Longest collision chain in a tree node is " + TreeNode.longestCollisionChain + "\n";
        TreeAnalyzerVisitor<K, V> visitor = new TreeAnalyzerVisitor<>();
        root.accept(visitor);
        status += "Min path height to bottom: " + visitor.minHeight + "\n";
        status += "Max path height to bottom: " + visitor.maxHeight + "\n";
        status += "Ideal height if balanced: " + Math.ceil(Math.log(count)) + "\n";
        return status;
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
        }
        int addPoint = root.insert(key, value, key.hashCode());
        if (addPoint > 0) {
            count++;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public V find(K key) throws IllegalArgumentException {
        if (key == null) {
            throw new IllegalArgumentException("The key could not be null!");
        }
        return root != null ? root.find(key, key.hashCode()) : null;
    }

    @Override
    public void ensureCapacity(int size) throws OutOfMemoryError {
        // Nothing to do here. Trees need no capacity.
    }

    @Override
    public Pair<K, V>[] toSortedArray() {
        TreeToArrayVisitor<K, V> visitor = new TreeToArrayVisitor<>(count);
        if (root != null) {
            root.accept(visitor);
        }
        Pair<K, V>[] sorted = visitor.getArray();
        Algorithms.fastSort(sorted);
        return sorted;
    }

    @Override
    public void compress() throws OutOfMemoryError {
        // Nothing to do here, since BST does not use extra space like array based structures.
    }
}
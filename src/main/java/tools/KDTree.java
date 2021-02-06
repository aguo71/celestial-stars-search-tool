package tools;

/**
 * Class representing a KDTree.
 * @param <T> object type implementing HasCoordinates
 */
public class KDTree<T extends HasCoordinates> {

  private T val;
  private KDTree<T> left;
  private KDTree<T> right;
  private int depth;

  /**
   * Constructor.
   * @param val current node's contents
   * @param left left KDsubtree
   * @param right right KDsubtree
   * @param depth current depth of node relative to root
   */
  public KDTree(T val, KDTree<T> left, KDTree<T> right, int depth) {
    this.val = val;
    this.left = left;
    this.right = right;
    this.depth = depth;
  }

  /**
   * Getter for val.
   * @return val
   */
  public T getVal() {
    return this.val;
  }

  /**
   * Getter for left KDsubtree.
   * @return left subtree
   */
  public KDTree<T> getLeft() {
    return left;
  }

  /**
   * Getter for right KDsubtree.
   * @return right subtree
   */
  public KDTree<T> getRight() {
    return right;
  }

  /**
   * Getter for depth of current node.
   * @return depth of node
   */
  public int getDepth() {
    return depth;
  }

  /**
   * Shallow copies input tree into existing KDtree.
   * @param tree KDtree to copy
   */
  public void copy(KDTree<T> tree) {
    this.val = tree.getVal();
    this.depth = tree.getDepth();
    if (tree.getLeft() != null) {
      this.left = new KDTree<T>(null, null, null, 0);
      left.copy(tree.getLeft());
    }
    if (tree.getRight() != null) {
      this.right = new KDTree<T>(null, null, null, 0);
      right.copy(tree.getRight());
    }
  }

}

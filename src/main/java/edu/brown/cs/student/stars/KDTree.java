package edu.brown.cs.student.stars;

public class KDTree<T extends HasCoordinates> {

  private T val;
  private KDTree<T> left;
  private KDTree<T> right;
  private int depth;

  public KDTree(T val, KDTree<T> left, KDTree<T> right, int depth) {
    this.val = val;
    this.left = left;
    this.right = right;
    this.depth = depth;
  }

  public T getVal() {
    return this.val;
  }

  public KDTree<T> getLeft() {
    return left;
  }

  public KDTree<T> getRight() {
    return right;
  }

  public int getDepth() {
    return depth;
  }

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

package tree;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author changzer
 * @date 2022/11/7
 * @apiNote
 */
class SearchTreeNode<T extends Comparable<T>> {
    private T value;
    private SearchTreeNode<T> left;
    private SearchTreeNode<T> right;

    public SearchTreeNode(T value) {
        this.value = value;
    }

    public SearchTreeNode() {
    }

    public T getValue() {
        return value;
    }

    public SearchTreeNode(T value, SearchTreeNode<T> left, SearchTreeNode<T> right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public SearchTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(SearchTreeNode<T> left) {
        this.left = left;
    }

    public SearchTreeNode<T> getRight() {
        return right;
    }

    public void setRight(SearchTreeNode<T> right) {
        this.right = right;
    }


    @Override
    public String toString() {
        return "SearchTreeNode{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
    }

}

public class SearchTree<T extends Comparable<T>> {
    private SearchTreeNode<T> root;
    private AtomicLong size = new AtomicLong(0);

    public SearchTree() {
        this.root = new SearchTreeNode<T>();
    }

    public SearchTreeNode<T> getRoot() {
        return root.getLeft();
    }

    public long getSize() {
        return size.get();
    }

    public T addNode(T value) {
        SearchTreeNode<T> node = new SearchTreeNode<T>(value);
        return addNode(node);
    }

    public T addNode(SearchTreeNode<T> node) {
        //init node
        node.setLeft(null);
        node.setRight(null);

        //Determine whether the header node is empty
        if (root.getLeft() == null) {
            //node become root
            root.setLeft(node);
        } else {
            //find insert point
            SearchTreeNode<T> x = findParentNode(node);
            int cmp = x.getValue().compareTo(node.getValue());

            //value exists,ignore this node
            if(cmp==0){
                return x.getValue();
            }

            if(cmp>0){
                x.setLeft(node);
            }else{
                x.setRight(node);
            }
            size.incrementAndGet();
        }
        return node.getValue();
    }

    private SearchTreeNode<T> findParentNode(SearchTreeNode<T> node) {
        SearchTreeNode<T> parent = getRoot();
        SearchTreeNode<T> cur = parent;

        while (cur != null) {
            int cmp = cur.getValue().compareTo(node.getValue());
            if (cmp > 0) {
                parent = cur;
                cur = cur.getLeft();
            }else if (cmp < 0) {
                parent = cur;
                cur = cur.getRight();
            }else {
                return cur;
            }
        }
        return parent;
    }
}
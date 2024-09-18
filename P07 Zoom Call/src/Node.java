// you do not need a file header, since you are not modifying this class
/**
 * A class that represents a generic node in a linked list
 * @author Andy Kuemmel, CS300
 * @param <T> the generic type of the data held by the Node
 */
public class Node<T> {
    private T data;	  // the data object stored by Nodes
    private Node<T> next; // a reference to the next node in the sequence

    /**
     * constructs a Node with a next field as input
     * @param data the data object stored by this node
     * @param next a reference to the next Node after this node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * constructs a Node with a next field of null
     * @param data the data object stored by this node
     */
    public Node(T data) {
        this(data, null);
    }

    /**
     * @return the value of the data object
     */
    public T getData() {
        return data;
    }

    /**
     * @param data the new value of the data object
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * @return a reference to next node object after this node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * @param next a reference to the node object that comes after this
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }

}


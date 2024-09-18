//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Zoom Call - ZoomLinkedList
// Course:   CS 300 Summer 2024
//
// Author:   Winston Chan
// Email:    wchan35@wisc.edu
// Lecturer: (Andy Kuemmel)
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
//////////////////////// ASSISTANCE/HELP CITATIONS ////////////////////////////
//
// Persons:         NONE
// Online Sources:  ZyBooks Ch.9
//
///////////////////////////////////////////////////////////////////////////////

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class represents a linked list used in the Zoom Call.
 * @param <T> represents the Type of the elements of the list
 * @author Winston Chan
 */
public class ZoomLinkedList<T> implements ListADT<T>, Iterable<T> {
    private Node<T> head;       // the head node of the linked list

    private Node<T> tail;       // the tail node of the linked list

    private int size;           // the size of the linked list

    /**
     * This is the constructor for the linked list.
     */
    public ZoomLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    @Override
    /**
     * This method adds an element at the end of a linked list.
     * @param element the object to be added
     */
    public void addAtEnd(T element) {
        Node<T> newNode = new Node<>(element);

        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /**
     * This method adds an element at the index of a linked list.
     * @param index the index where the object is added
     * @param element the object to be added
     */
    @Override
    public void add(int index, T element) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0) {
            newNode.setNext(head);
            head = newNode;
            if (size == 0) {
                tail = newNode;
            }
        } else if (index == size - 1) {
            tail.setNext(newNode);
            tail = newNode;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
        size++;
    }

    @Override
    /**
     * This method removes an element at the index of a linked list.
     * @param index the index where the object is removed
     */
    public T remove(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T removedData;
        if (index == 0) {
            removedData = head.getData();
            head = head.getNext();
            if (head == null) {
                tail = null;
            }
        } else if (index == size - 1) {
            Node<T> current = head;
            while (current.getNext() != tail) {
                current = current.getNext();
            }
            removedData = tail.getData();
            tail = current;
            tail.setNext(null);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Node<T> nodeToRemove = current.getNext();
            removedData = nodeToRemove.getData();
            current.setNext(nodeToRemove.getNext());
        }
        size--;
        return removedData;
    }

    @Override
    /**
     * This method returns the size of the linked list.
     */
    public int size() {
        return size;
    }

    @Override
    /**
     * This method removes an element from the front of the linked list.
     */
    public T removeFromFront() throws NoSuchElementException {
        if (head == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        T data = head.getData();
        head = head.getNext();
        if (head == null) {
            tail = null;
        }
        size--;
        return data;
    }

    @Override
    /**
     * This method gets an element at the index of a linked list.
     * @param index the index where the object is obtained
     */
    public T get(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index " + index + " is not valid\n");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getData();
    }

    @Override
    /**
     * This method gets iterates through each element through the linked list.
     */
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }

    /**
     * prints out a representation of the list using sample run output as a guide
     */
    public void print() {
        StringBuilder builder = new StringBuilder("CS 300 Main Room: \nSize: ");
        builder.append(size);
        builder.append(" Contents: ");
        Node <T> current = head;
        while (current != null) {
            builder.append(current.getData());
            builder.append(", ");
            current = current.getNext();
        }
        System.out.println(builder.toString());
    }
}
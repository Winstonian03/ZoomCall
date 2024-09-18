//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Zoom Call - LinkedListIterator
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
 * This class represents the linked list iterator used in the Zoom Call.
 * @param <T> represents the Type of the elements of the list
 * @author Winston Chan
 */
public class LinkedListIterator<T> implements Iterator<T> {
    private Node<T> current;        // the current node in the linked list

    /**
     * Constructs an iterator starting at the given head node.
     * Initializes the iterator to start from the head of the linked list.
     *
     * @param head the head node of the linked list
     */
    public LinkedListIterator(Node<T> head) {
        this.current = head;
    }

    @Override
    /**
     * Checks if there are more elements to iterate over.
     * This method returns true if the current node is not null,
     * indicating that there are more elements to iterate through.
     *
     * @return true if there are more elements, false otherwise
     */
    public boolean hasNext() {
        return current != null;
    }

    @Override
    /**
     * Returns the next element in the iteration.
     * This method retrieves the data from the current node, advances to the next node,
     * and returns the data. It throws NoSuchElementException if no more elements are available.
     *
     * @return the data of the next node in the iteration
     * @throws NoSuchElementException if there are no more elements to iterate over
     */
    public T next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException("No more elements in the list.");
        }
        T data = current.getData();
        current = current.getNext();
        return data;
    }

    @Override
    /**
     * Removes the last element returned by this iterator from the list.
     * This implementation of remove is empty because removing elements
     * is not supported in this iterator.
     */
    public void remove() { }
}// class

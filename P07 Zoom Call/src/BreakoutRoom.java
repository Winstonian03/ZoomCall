//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Zoom Call - BreakoutRoom
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

/**
 * A class that represents one Breakout room.
 * @author Andy Kuemmel
 * @see also ZoomLinkedList, ZoomParticipant
 */
public class BreakoutRoom {
    private ZoomLinkedList<ZoomParticipant> roomList;       // the list of participants in the call
    private String name;                                    // the name of the participant + status

    /**
     * constructor
     * @param name
     */
    public BreakoutRoom(String name) {// TODO: COMPLETE THIS METHOD
        this.roomList = new ZoomLinkedList<>();
        this.name = name;
    }

    /**
     * adds participant to this room's roomList and unmutes
     * @param participant
     */
    public void addToRoom(ZoomParticipant participant) {// TODO: COMPLETE THIS METHOD
        participant.setMuted(false);
        roomList.addAtEnd(participant);
    }

    /**
     * removes the participant at index from this room
     * @param index
     * @return the participant removed
     * @throws IllegalArgumentException if index is out of bounds
     */
    public ZoomParticipant removeFromRoom(int index) throws IllegalArgumentException {
        if (index < 0 || index >= roomList.size()) {
            throw new IllegalArgumentException("Index is out of bounds: " + index);
        }
        return roomList.remove(index);
    }

    /**
     * @return the size of the room list
     */
    public int getSize() {
        return roomList.size();
    }

    /**
     * prints out the info in roomList
     */
    public void printBreakoutRoom() {
        StringBuilder builder = new StringBuilder();
        for (ZoomParticipant participant : roomList) {
            builder.append(participant.getName());
            builder.append(", ");
        }
        System.out.println(name + "\tSize: " + roomList.size() + " Contents: " + builder.toString());
    }
}
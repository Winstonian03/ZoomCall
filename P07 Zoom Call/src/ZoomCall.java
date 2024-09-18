//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Zoom Call - ZoomCall
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
 * This class represents the Zoom Call, giving calling its proper methods.
 * @author Winston Chan
 */
public class ZoomCall {
    private String callName;		                        // the name of the call
    private ZoomLinkedList<ZoomParticipant> mainRoomList;   // the list of participants

    private boolean inBreakoutMode;                         // status of breakout rooms

    private BreakoutRoom [] breakoutRooms;                  // array of breakout rooms

    /**
     * This is the constructor for the Zoom Call.
     */
    public ZoomCall(String callName) {
        this.callName = callName;
        this.mainRoomList = new ZoomLinkedList<>();
    }

    /**
     * @return the number of participants in mainRoomList (do not include moderator)
     */
    public int getMainRoomSize() {
        int size = 0;
        Iterator<ZoomParticipant> iterator = mainRoomList.iterator();
        while(iterator.hasNext()) {
            ZoomParticipant participant = iterator.next();
            if (!"moderator".equalsIgnoreCase(participant.getName())) {
                size++;
            }
        }
        return size;
    }

    /**
     * instantiates a ZoomParticipant, muted, and adds at end of mainRoomList
     * @param name
     */
    public void addToCall(String name) {
        ZoomParticipant participant = new ZoomParticipant(name, true);
        mainRoomList.addAtEnd(participant);
    }

    /**
     * overloaded method to call when using main room
     * if the call is in breakout room, return null
     * @param index
     * @return the ZoomParticipant at this index in mainRoomList
     * @throws IllegalArgumentException if index is out of range
     */
    public ZoomParticipant dropFromCall(int index) throws IllegalArgumentException {
        if(this.mainRoomList.size() < index || index < 0) {
            throw new IllegalArgumentException("index " + index + " is not valid\n");
        } else {
            return this.mainRoomList.remove(index);
        }
    }

    /**
     * sets the ZoomParticipant at index in the main room to muted
     * @param index
     * @throws IllegalArgumentException if index is out of range
     */
    public void mute(int index) throws IllegalArgumentException {
        if(!mainRoomList.get(index).isMuted()) {
            mainRoomList.get(index).setMuted(true);
        }
        if(index < 0 || index >= getMainRoomSize()) {
            throw new IllegalArgumentException("index out of range");
        }
    }

    /**
     * This method uses an iterator to mute all participants in the main room
     */
    public void muteAll() {
        Iterator<ZoomParticipant> iterator = mainRoomList.iterator();
        while (iterator.hasNext()) {
            ZoomParticipant participant = iterator.next();
            if (!participant.isMuted()) {
                participant.setMuted(true);
            }
        }
    }

    /**
     * This method uses an iterator to unmute all participants in the main room
     */
    public void unMuteAll() {
        Iterator<ZoomParticipant> iterator = mainRoomList.iterator();
        while(iterator.hasNext()) {
            ZoomParticipant participant = iterator.next();
            if(participant.isMuted()) {
                participant.setMuted(false);
            }
        }
    }

    /**
     * if inBreakoutMode, do nothing, otherwise change the name
     * @param index the index of the participant in the mainRoom
     * @param newName the new name for this participant
     * @throws IllegalArgumentException if index is out of range
     */
    public void changeName(int index, String newName) throws IllegalArgumentException {
        if ("breakout room".equalsIgnoreCase(callName)) {
            return;
        } else if (!callName.equalsIgnoreCase("breakout room")) {
            mainRoomList.get(index).setName(newName);
        } else {
            throw new IllegalArgumentException("Illegal input argument");
        }
    }

    /**
     * prints the participants in the main room
     */
    public void printMainRoom() {
        this.mainRoomList.print();
    }

    /**
     * starts breakout rooms and places participants one at a time into a room
     * example with 3 rooms: room 0, room 1, room 2, room 0, room 1, room 2, .. . . .
     * sets inBreakoutMode to true
     * when added to a breakout room, unmute a participant and remove it from the main room
     * @param numRooms
     * @throws IllegalArgumentException if numRooms is <= 0
     */
    public void startBreakoutRooms(int numRooms) throws IllegalArgumentException {
        if (numRooms <= 0) {
            throw new IllegalArgumentException("Number of rooms must be greater than 0.");
        }
        this.breakoutRooms = new BreakoutRoom[numRooms];
        for (int i = 0; i < numRooms; i++) {
            breakoutRooms[i] = new BreakoutRoom("Room: " + (i));
        }
        Iterator<ZoomParticipant> iterator = mainRoomList.iterator();
        int roomIndex = 0;
        while (iterator.hasNext()) {
            ZoomParticipant participant = iterator.next();
            participant.setMuted(false);
            breakoutRooms[roomIndex].addToRoom(participant);
            iterator.remove();
            roomIndex = (roomIndex + 1) % numRooms;
        }
        inBreakoutMode = true;
    }


    /**
     * if there are no breakout rooms, return 0
     * @return the number of current breakout rooms
     */
    public int getNumBreakoutRooms() {
        return breakoutRooms == null ? 0 : breakoutRooms.length;
    }

    /**
     * sets the value of inBreakoutMode to false
     * removes participants from each room, one room at a time,
     * mutes each participant, and adds them to main room
     * sets the array this.breakoutRooms to null
     */
    public void endBreakoutRooms() {
        if (breakoutRooms != null) {

            for (BreakoutRoom room : breakoutRooms) {

                while (room.getSize() > 0) {
                    ZoomParticipant participant = room.removeFromRoom(0);
                    participant.setMuted(true);
                }
            }
            this.breakoutRooms = null;
            inBreakoutMode = false;
        }
    }

    /**
     * overloaded method to call when using breakout rooms
     * if the call is not in breakout mode, return null
     * @param room the room number
     * @param index the index of the person in the room
     * @return the ZoomParticipant at this index in mainRoomList
     * @throws IllegalArgumentException if index is out of range OR room is out of range
     */
    public ZoomParticipant dropFromCall(int room, int index) throws IllegalArgumentException{
        if (!inBreakoutMode || room < 0 || room >= breakoutRooms.length) {
            throw new IllegalArgumentException("room " + index + " is not valid");
        }
        if (index < 0 || index >= breakoutRooms[room].getSize()) {
            throw new IllegalArgumentException("room " + index + " is not valid");
        }
        return breakoutRooms[room].removeFromRoom(index);
    }

    /**
     * moves a participant from one room to another -  if not in breakoutMode, do nothing
     * @param roomNumber the original room
     * @param indexNumber the index of this participant in the breakout room
     * @param newRoomNumber the new room
     * @throws IndexOutOfBoundsException if any parameter is out of bounds
     */
    public void changeRooms(int roomNumber, int indexNumber, int newRoomNumber) throws IndexOutOfBoundsException {
        if (!inBreakoutMode) {
            return;
        }
        if (roomNumber < 0 || roomNumber > breakoutRooms.length ||
                newRoomNumber < 0 || newRoomNumber > breakoutRooms.length) {
            throw new IndexOutOfBoundsException("Parameters are out of bounds.");
        }
        ZoomParticipant participant = breakoutRooms[roomNumber].removeFromRoom(indexNumber);
        breakoutRooms[newRoomNumber].addToRoom(participant);
    }

    /**
     * prints out each room and the participants.
     */
    public void printBreakoutRooms() {
        if (breakoutRooms == null) {
            System.out.println("No breakout rooms available.");
            return;
        }
        System.out.println("\n" + this.callName + " Breakout Rooms:");
        for (BreakoutRoom room : breakoutRooms) {
            room.printBreakoutRoom();
        }
    }
}

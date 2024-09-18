//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    P07 Zoom Call - ZoomParticipant
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

/**
 * class that represents one Zoom Participant with fields for name and muted
 * @author Andy Kuemmel, CS 300 and Winston Chan
 */
public class ZoomParticipant {
    private String name;        // the name of the Zoom Participant
    private boolean muted;      // the muted status of the Zoom Participant

    /**
     * constructor, name only which calls the other constructor to set muted to true
     *
     * @param name the name of this participant
     */
    public ZoomParticipant(String name) {
        this.name = name;
        this.muted = true;
    }

    /**
     * constructor with name and muted property
     *
     * @param name
     * @param muted
     */
    public ZoomParticipant(String name, boolean muted) {
        this.name = name;
        this.muted = muted;
    }

    /**
     * @return the value of muted
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * @param muted the new value of muted
     */
    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    /**
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * allows participants to change their name
     *
     * @param name the new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    /**
     * returns Name, or Name(muted) based on muted property
     * @return name + "(muted)" if this Participant is currently muted
     */
    public String toString() {
        if(muted) {
            return name + "(muted)";
        }
        return name;
    }
}
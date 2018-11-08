/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Arsal
 */
public class Note {
    private int noteID;
    private String datecreated;
    private String contents;
    
    public Note() {
	noteID = 0;
	datecreated = "";
	contents = "";
    }
    
    public Note(int noteID, String datecreated, String contents) {
	this.noteID = noteID;
	this.datecreated = datecreated;
	this.contents = contents;
    }
    
    public int getNoteID() {
	return noteID;
    }

    public void setNoteID(int noteID) {
	this.noteID = noteID;
    }

    public String getDatecreated() {
	return datecreated;
    }

    public void setDatecreated(String datecreated) {
	this.datecreated = datecreated;
    }

    public String getContents() {
	return contents;
    }

    public void setContents(String contents) {
	this.contents = contents;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Note;

/**
 *
 * @author Arsal
 */
public class NoteDB {
    private Connection conn;
   
    public NoteDB() {
	String dbURL = "jdbc:mysql://localhost:3306/notesdb";
	String username = "root";
	String password = "password";
	
	try {
	    conn = DriverManager.getConnection(dbURL, username, password);
	    
	} catch (SQLException ex) {
	    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }
    
    public Note getNote(int id) {
	try {
	    Statement stmt = conn.createStatement();
	    stmt.execute("GET * FROM notes WHERE noteid = " + id);
	    ResultSet res = stmt.getResultSet();
	    if(res.next()) {
		return (new Note(Integer.parseInt((String)res.getObject(1)), (String)res.getObject(2), (String)res.getObject(3)));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
	}
	return null;
    }
    
    public int insert(Note note) {
	try {
	    Statement stmt = conn.createStatement();
	    stmt.execute("INSERT INTO notes"
		    + " VALUES(" + note.getNoteID() + ", " + note.getDatecreated() + note.getContents() + ");");
	} catch (SQLException ex) {
	    Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
	    return -1;
	}
	return 1;
    }
    
    public int update(Note note) {
	try {	
	    Statement stmt = conn.createStatement();
	    stmt.executeUpdate("INSERT INTO notes (noteid, datecreated, contents)"
		    + " VALUES(" + note.getNoteID() + ", " + note.getDatecreated() + ", " + note.getContents() + ")"
			    + "WHERE noteid = " + note.getNoteID());
	} catch (SQLException ex) {
	    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	    return -1;
	}
	return 1;
    }
    
    public ArrayList<Note> getAll() {
	ArrayList<Note> notes = new ArrayList();
	
	try {
	    Statement stmt = conn.createStatement();
	    stmt.execute("SELECT * FROM notes");
	    ResultSet res = stmt.getResultSet();
	     
	    while(res.next()) {
		notes.add(new Note(Integer.parseInt((String)res.getObject(1)), (String)res.getObject(2), (String)res.getObject(3)));
	    }
	} catch (SQLException ex) {
	    Logger.getLogger(NoteDB.class.getName()).log(Level.SEVERE, null, ex);
	}
	
	return notes;
    }
    
    public void delete(Note note) {
	try {
	    Statement stmt = conn.createStatement();
	    stmt.execute("DELETE notes"
		    + "WHERE noteid = " + note.getNoteID() + ";");
	} catch (SQLException ex) {
	    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
}

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Note;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Arsal
 */
public class DBUtil {
    private Connection conn;
   
    public DBUtil() {
	String dbURL = "jdbc:mysql://localhost:3306/notesdb";
	String username = "root";
	String password = "password";
	
	try {
	    conn = DriverManager.getConnection(dbURL, username, password);
	    
	} catch (SQLException ex) {
	    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
	
    }
    
    public void updateNote(Note note) {
	try {	
	    Statement stmt = conn.createStatement();
	    stmt.executeUpdate("INSERT INTO notes (noteid, datecreated, contents)"
		    + " VALUES(" + note.getNoteID() + ", " + note.getDatecreated() + ", " + note.getContents() + ")"
			    + "WHERE noteid = " + note.getNoteID() + ";");
	} catch (SQLException ex) {
	    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    public void removeNote(Note note) {
	try {
	    Statement stmt = conn.createStatement();
	    stmt.execute("DELETE notes"
		    + "WHERE noteid = " + note.getNoteID() + ";");
	} catch (SQLException ex) {
	    Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
	}
    }
    
    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("NotesPU");

    public static EntityManagerFactory getEmFactory() {
        return emf;
    }
}

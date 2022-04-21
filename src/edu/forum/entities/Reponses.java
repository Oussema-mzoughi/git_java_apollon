/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.forum.entities;

import java.util.Date;

/**
 *
 * @author Fakher
 */
public class Reponses {
    private int id;
    private String message_r; 
    private Date date_r;
    private int question_id;
    private int user_id;

    public Reponses() {
    }

    public Reponses(int id, String message_r, Date date_r, int question_id, int user_id) {
        this.id = id;
        this.message_r = message_r;
        this.date_r = date_r;
        this.question_id = question_id;
        this.user_id = user_id;
    }

    public Reponses(String message_r, Date date_r, int question_id, int user_id) {
        this.message_r = message_r;
        this.date_r = date_r;
        this.question_id = question_id;
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage_r() {
        return message_r;
    }

    public void setMessage_r(String message_r) {
        this.message_r = message_r;
    }

    public Date getDate_r() {
        return date_r;
    }

    public void setDate_r(Date date_r) {
        this.date_r = date_r;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Reponses{" + "id=" + id + ", message_r=" + message_r + ", date_r=" + date_r + ", question_id=" + question_id + ", user_id=" + user_id + '}';
    }

    public Reponses(String message_r) {
        this.message_r = message_r;
    }
    
    
    
    
    
    
}

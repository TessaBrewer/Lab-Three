package app;

import java.io.Serializable;

public class MyLocation implements Serializable {
  private int id;
  private double xcoord;
  private double ycoord;
  private String shortNote;
  private String comments;
  
  public int getId() {
    return id;
  }
  
  public void setId(int id) {
    this.id = id;
  }
  
  public double getXcoord() {
    return xcoord;
  }
  
  public void setXcoord(double xcoord) {
    this.xcoord = xcoord;
  }
  
  public double getYcoord() {
    return ycoord;
  }
  
  public void setYcoord(double ycoord) {
    this.ycoord = ycoord;
  }
  
  public String getShortNote() {
    return shortNote;
  }
  
  public void setShortNote(String shortNote) {
    this.shortNote = shortNote;
  }
  
  public String getComments() {
    return comments;
  }
  
  public void setComments(String comments) {
    this.comments = comments;
  }
  
  public String toString() {
    return id + ", " + xcoord + ", " + ycoord + ", " + shortNote + ", " + comments;
  }
}
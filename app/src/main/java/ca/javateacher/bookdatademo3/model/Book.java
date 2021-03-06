/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3.model;

import java.util.Date;

public class Book {

  public static final int FORMAT_PAPERBACK = 0;
  public static final int FORMAT_HARDCOVER = 1;
  private long mRowId = 0; // rowId generated by the database
  private String mTitle = "Book Title";
  private String mAuthor = "Book Author";
  private Date mDate = new Date();
  private int mLanguage = 0;
  private int mFormat = FORMAT_PAPERBACK;
  private boolean mUsed = false;

  public long getRowId() {
    return mRowId;
  }

  public void setRowId(Long rowId) {
    mRowId = rowId;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    this.mTitle = title;
  }

  public String getAuthor() {
    return mAuthor;
  }

  public void setAuthor(String author) {
    this.mAuthor = author;
  }

  public Date getDate() {
    return mDate;
  }

  public void setDate(Date date) {
    this.mDate = date;
  }

  public int getLanguage() {
    return mLanguage;
  }

  public void setLanguage(int language) {
    this.mLanguage = language;
  }

  public int getFormat() {
    return mFormat;
  }

  public void setFormat(int format) {
    this.mFormat = format;
  }

  public boolean isUsed() {
    return mUsed;
  }

  public void setUsed(boolean used) {
    this.mUsed = used;
  }
}

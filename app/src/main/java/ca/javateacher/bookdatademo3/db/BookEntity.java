package ca.javateacher.bookdatademo3.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import java.util.Date;

@Entity(tableName="books")
public class BookEntity {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name="id")
  private long mId = 0;

  @ColumnInfo(name="title")
  private String mTitle;

  @ColumnInfo(name="author")
  private String mAuthor;

  @ColumnInfo(name="date")
  private Date mDate;

  @ColumnInfo(name="language")
  private int mLanguage;

  @ColumnInfo(name="format")
  private int mFormat;

  @ColumnInfo(name="used")
  private boolean mUsed;

  public BookEntity() {
  }

  public long getId() {
    return mId;
  }

  public void setId(long id) {
    mId = id;
  }

  public String getTitle() {
    return mTitle;
  }

  public void setTitle(String title) {
    mTitle = title;
  }

  public String getAuthor() {
    return mAuthor;
  }

  public void setAuthor(String author) {
    mAuthor = author;
  }

  public Date getDate() {
    return mDate;
  }

  public void setDate(Date date) {
    mDate = date;
  }

  public int getLanguage() {
    return mLanguage;
  }

  public void setLanguage(int language) {
    mLanguage = language;
  }

  public int getFormat() {
    return mFormat;
  }

  public void setFormat(int format) {
    mFormat = format;
  }

  public boolean isUsed() {
    return mUsed;
  }

  public void setUsed(boolean used) {
    mUsed = used;
  }
}

package ca.javateacher.bookdatademo3.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  long insertBook(BookEntity bookEntity);

  @Query("DELETE FROM books")
  void deleteAllBooks();

  @Query("DELETE FROM books WHERE id = :id")
  void deleteBookById(long id);

  @Delete
  void deleteBook(BookEntity bookEntity);

  @Query("SELECT * from books ORDER BY title ASC")
  LiveData<List<BookEntity>> getBookListData();

  @Query("SELECT * from books WHERE id = :id")
  LiveData<BookEntity> getBookDataById(long id);

  @Update
  void updateBook(BookEntity bookEntity);

}

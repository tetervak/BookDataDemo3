package ca.javateacher.bookdatademo3.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {BookEntity.class}, version = 1, exportSchema = false)
@TypeConverters({BookTypeConverters.class})
public abstract class BookDatabase extends RoomDatabase {

  public abstract BookDao getBookDao();
}

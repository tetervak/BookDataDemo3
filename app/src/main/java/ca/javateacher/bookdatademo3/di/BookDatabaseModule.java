package ca.javateacher.bookdatademo3.di;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import ca.javateacher.bookdatademo3.db.BookDao;
import ca.javateacher.bookdatademo3.db.BookDatabase;
import dagger.Module;
import dagger.Provides;

@Module
public class BookDatabaseModule {

  @Singleton
  @Provides
  public BookDatabase getBookDatabase(Context appContext){
    return Room.databaseBuilder(appContext,
      BookDatabase.class, "book_database")
      // Wipes and rebuilds instead of migrating if no Migration object.
      .fallbackToDestructiveMigration()
      //.addCallback(sRoomDatabaseCallback)
      .build();
  }

  @Singleton
  @Provides
  public BookDao getBookDao(BookDatabase bookDatabase){
    return bookDatabase.getBookDao();
  }

}

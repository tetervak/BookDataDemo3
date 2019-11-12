package ca.javateacher.bookdatademo3.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import javax.inject.Inject;
import ca.javateacher.bookdatademo3.BookDataApplication;
import ca.javateacher.bookdatademo3.model.Book;
import ca.javateacher.bookdatademo3.repository.BookRepository;

public class BookDetailsViewModel extends AndroidViewModel {

  // no, it cannot be private
  @SuppressWarnings("WeakerAccess")
  @Inject
  public BookRepository mRepository;

  public BookDetailsViewModel(@NonNull Application application) {
    super(application);
    ((BookDataApplication)application)
      .getApplicationComponent()
      .inject(this);
  }

  public LiveData<Book> getBookData(long bookId){
    return mRepository.getBookDataById(bookId);
  }

  public void saveBook(Book book){
    mRepository.saveBook(book);
  }

  public void deleteBook(Book book){
    mRepository.deleteBook(book);
  }
}

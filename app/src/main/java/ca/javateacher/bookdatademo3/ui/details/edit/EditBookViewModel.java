package ca.javateacher.bookdatademo3.ui.details.edit;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.Date;

import javax.inject.Inject;

import ca.javateacher.bookdatademo3.BookDataApplication;
import ca.javateacher.bookdatademo3.model.Book;
import ca.javateacher.bookdatademo3.repository.BookRepository;

public class EditBookViewModel extends AndroidViewModel {

  // no, it cannot be private
  @SuppressWarnings("WeakerAccess")
  @Inject
  public BookRepository mRepository;

  public EditBookViewModel(@NonNull Application application) {
    super(application);

    ((BookDataApplication)application)
      .getApplicationComponent()
      .inject(this);
  }

  public LiveData<Book> getBookDataById(long bookId){
    return mRepository.getBookDataById(bookId);
  }

  public void updateBook(Book book){
    mRepository.updateBook(book);
  }

  public void deleteBook(Book book){
    mRepository.deleteBook(book);
  }
}

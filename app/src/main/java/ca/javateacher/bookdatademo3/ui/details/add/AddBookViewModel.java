package ca.javateacher.bookdatademo3.ui.details.add;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import javax.inject.Inject;

import ca.javateacher.bookdatademo3.BookDataApplication;
import ca.javateacher.bookdatademo3.model.Book;
import ca.javateacher.bookdatademo3.repository.BookRepository;

public class AddBookViewModel extends AndroidViewModel {

  // no, it cannot be private
  @SuppressWarnings("WeakerAccess")
  @Inject
  public BookRepository mRepository;

  public AddBookViewModel(@NonNull Application application) {
    super(application);
    ((BookDataApplication)application)
      .getApplicationComponent()
      .inject(this);
  }

  public void insertBook(Book book){
    mRepository.insertBook(book);
  }
}

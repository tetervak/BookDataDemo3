package ca.javateacher.bookdatademo3.ui.list;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;
import javax.inject.Inject;

import ca.javateacher.bookdatademo3.BookDataApplication;
import ca.javateacher.bookdatademo3.model.Book;
import ca.javateacher.bookdatademo3.repository.BookRepository;

public class BookListViewModel extends AndroidViewModel {

  // no, it cannot be private
  @SuppressWarnings("WeakerAccess")
  @Inject
  public BookRepository mRepository;

  public BookListViewModel(@NonNull Application application) {
    super(application);
    ((BookDataApplication)application)
      .getApplicationComponent()
      .inject(this);
  }

  public LiveData<List<Book>> getBookListData(){
    return mRepository.getBookListData();
  }

}

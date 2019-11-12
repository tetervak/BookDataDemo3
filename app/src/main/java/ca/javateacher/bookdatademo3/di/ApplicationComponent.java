package ca.javateacher.bookdatademo3.di;

import android.content.Context;

import javax.inject.Singleton;

import ca.javateacher.bookdatademo3.ui.details.add.AddBookViewModel;
import ca.javateacher.bookdatademo3.ui.details.edit.EditBookViewModel;
import ca.javateacher.bookdatademo3.ui.details.view.ViewBookViewModel;
import ca.javateacher.bookdatademo3.ui.list.BookListViewModel;
import dagger.BindsInstance;
import dagger.Component;

// Definition of the Application  Dagger graph
@Singleton
@Component(modules = {BookDatabaseModule.class})
public interface ApplicationComponent {

  // Factory to create instances of the ApplicationComponent
  @Component.Factory
  interface Factory {
    ApplicationComponent create(@BindsInstance Context context);
  }

  void inject(BookListViewModel bookListViewModel);
  void inject(AddBookViewModel addBookViewModel);
  void inject(EditBookViewModel editBookViewModel);
  void inject(ViewBookViewModel viewBookViewModel);
}

package ca.javateacher.bookdatademo3.di;

import android.content.Context;

import javax.inject.Singleton;

import ca.javateacher.bookdatademo3.viewmodel.BookDetailsViewModel;
import ca.javateacher.bookdatademo3.viewmodel.BookListViewModel;
import dagger.BindsInstance;
import dagger.Component;

// Definition of the Application  Dagger graph
@Singleton
@Component(
  modules = {BookDatabaseModule.class})
public interface ApplicationComponent {

  // Factory to create instances of the ApplicationComponent
  @Component.Factory
  interface Factory {
    ApplicationComponent create(@BindsInstance Context context);
  }

  void inject(BookListViewModel flowerListViewModel);
  void inject(BookDetailsViewModel flowerDetailsViewModel);
}

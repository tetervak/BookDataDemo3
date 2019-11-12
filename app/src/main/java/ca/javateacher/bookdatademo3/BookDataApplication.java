package ca.javateacher.bookdatademo3;

import android.app.Application;

import ca.javateacher.bookdatademo3.di.ApplicationComponent;
import ca.javateacher.bookdatademo3.di.DaggerApplicationComponent;

public class BookDataApplication extends Application {

  // Reference to the application graph that is used across the whole app
  private ApplicationComponent mApplicationComponent
    = DaggerApplicationComponent.factory().create(this);

  public ApplicationComponent getApplicationComponent(){
    return mApplicationComponent;
  }

}

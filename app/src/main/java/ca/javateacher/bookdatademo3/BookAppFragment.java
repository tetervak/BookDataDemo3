package ca.javateacher.bookdatademo3;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

abstract public class BookAppFragment extends Fragment {

  private BookAppNavigator mBookAppNavigator;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // notify that the fragment has own menu
    setHasOptionsMenu(true);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    mBookAppNavigator = new BookAppNavigator(this);
  }

  public BookAppNavigator getNavigator() {
    return mBookAppNavigator;
  }
}

package ca.javateacher.bookdatademo3.ui.details.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import ca.javateacher.bookdatademo3.BookAppFragment;
import ca.javateacher.bookdatademo3.R;
import ca.javateacher.bookdatademo3.databinding.ViewBookBinding;
import ca.javateacher.bookdatademo3.ui.dialogs.ConfirmDeleteFragment;

public class ViewBookFragment extends BookAppFragment {

  private static final String TAG = "ViewBookFragment";

  //request codes for the dialogs
  private static final int DELETE_REQUEST_CODE = 2;

  private ViewBookPresenter mPresenter;
  private Long mBookId;

  public static final String ARG_BOOK_ID = "book_id";

  public ViewBookFragment() {
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    Bundle arguments = getArguments();
    if (arguments != null && arguments.containsKey(ARG_BOOK_ID)) {
      // In a real-world scenario, use a Loader
      // to load content from a content provider.
      mBookId = arguments.getLong(ARG_BOOK_ID);
    }
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    ViewBookBinding binding = ViewBookBinding.inflate(getLayoutInflater());
    mPresenter = new ViewBookPresenter(this, binding);
    return binding.getRoot();
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_view_book, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()) {

      case R.id.menu_edit:
        mPresenter.editBook();
        return true;

      case R.id.menu_delete:
        showDeleteBookDialog();
        return true;

      default:
        return super.onOptionsItemSelected(item);
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    ViewBookViewModel viewModel
      = ViewModelProviders.of(this).get(ViewBookViewModel.class);
    mPresenter.init(viewModel, mBookId);
  }

  private void showDeleteBookDialog() {
    ConfirmDeleteFragment.show(this, DELETE_REQUEST_CODE);
  }

  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch(requestCode){
      case DELETE_REQUEST_CODE:{
        if(resultCode == Activity.RESULT_OK){
          mPresenter.deleteBook();
        }
        break;
      }
    }
  }
}

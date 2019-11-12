package ca.javateacher.bookdatademo3;

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

import java.util.Date;

import ca.javateacher.bookdatademo3.databinding.EditBookBinding;
import ca.javateacher.bookdatademo3.viewmodel.BookDetailsViewModel;

public class EditBookFragment extends BookAppFragment {
  private static final String TAG = "EditBookFragment";

  //request codes for the dialogs
  private static final int DATE_REQUEST_CODE = 1;
  private static final int DELETE_REQUEST_CODE = 2;

  private EditBookPresenter mPresenter;
  private long mBookId;

  static final String ARG_BOOK_ID = "book_id";

  public EditBookFragment() {
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
    EditBookBinding binding = EditBookBinding.inflate(getLayoutInflater());
    mPresenter = new EditBookPresenter(this, binding);
    return binding.getRoot();
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_edit_book, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()) {

      case R.id.menu_save:
        mPresenter.saveBook();
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
    BookDetailsViewModel viewModel
      = ViewModelProviders.of(this).get(BookDetailsViewModel.class);
    mPresenter.init(viewModel, mBookId);
  }

  void showDatePickerDialog(Date date) {
    DatePickerFragment fragment =
      DatePickerFragment.newInstance(date);
    fragment.setTargetFragment(this, DATE_REQUEST_CODE);
    fragment.show(getFragmentManager(), DatePickerFragment.TAG);
  }

  private void showDeleteBookDialog() {
    ConfirmDeleteFragment fragment =
      ConfirmDeleteFragment.newInstance();
    fragment.setTargetFragment(this, DELETE_REQUEST_CODE);
    fragment.show(getFragmentManager(), ConfirmDeleteFragment.TAG);
  }

  public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    switch(requestCode){
      case DATE_REQUEST_CODE:{
        if(resultCode == Activity.RESULT_OK){
          Date date =
            (Date) data.getSerializableExtra(DatePickerFragment.DATE);
          mPresenter.setDate(date);
        }
        break;
      }
      case DELETE_REQUEST_CODE:{
        if(resultCode == Activity.RESULT_OK){
          mPresenter.deleteBook();
        }
        break;
      }
    }
  }
}

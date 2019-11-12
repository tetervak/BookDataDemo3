package ca.javateacher.bookdatademo3.ui.details.add;

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

import ca.javateacher.bookdatademo3.BookAppFragment;
import ca.javateacher.bookdatademo3.ui.dialogs.DatePickerFragment;
import ca.javateacher.bookdatademo3.R;
import ca.javateacher.bookdatademo3.databinding.AddBookBinding;

public class AddBookFragment extends BookAppFragment {
  private static final String TAG = "AddBookFragment";

  //request codes for the dialogs
  private static final int DATE_REQUEST_CODE = 1;

  private AddBookPresenter mPresenter;

  public AddBookFragment() {
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    AddBookBinding binding = AddBookBinding.inflate(getLayoutInflater());
    mPresenter = new AddBookPresenter(this, binding);
    return binding.getRoot();
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_add_book, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch(item.getItemId()) {
      case R.id.menu_save:{
        mPresenter.saveBook();
        return true;
      }
      default:{
        return super.onOptionsItemSelected(item);
      }
    }
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    AddBookViewModel viewModel
      = ViewModelProviders.of(this).get(AddBookViewModel.class);
    mPresenter.init(viewModel);
  }

  public void showDatePickerDialog(Date date) {
    DatePickerFragment fragment =
      DatePickerFragment.newInstance(date);
    fragment.setTargetFragment(this, DATE_REQUEST_CODE);
    fragment.show(getFragmentManager(), DatePickerFragment.TAG);
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
    }
  }
}

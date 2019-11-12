package ca.javateacher.bookdatademo3.ui.details.add;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.util.Date;

import ca.javateacher.bookdatademo3.R;
import ca.javateacher.bookdatademo3.databinding.AddBookBinding;
import ca.javateacher.bookdatademo3.model.Book;

class AddBookPresenter {

  private static final String TAG = "AddBookPresenter";

  private AddBookFragment mFragment;
  private Context mFragmentContext;
  private AddBookBinding mBinding;
  private AddBookViewModel mViewModel;
  private Book mBook;
  private Date mDate;


  public AddBookPresenter(AddBookFragment fragment, AddBookBinding binding) {
    mFragment = fragment;
    mFragmentContext = fragment.getContext();
    mBinding = binding;
  }

  public void init(AddBookViewModel viewModel) {
    mViewModel = viewModel;
    mBook = new Book();
    setInputsFromBook();
    setupListeners();
  }

  // saves book information to the database
  void saveBook(){
    if(validateInputs()){
      hideKeyboard();
      setBookFromInputs();
      mViewModel.insertBook(mBook);
      mFragment.getNavigator().toBookList();
    }
  }

  // fill the input values based on the book object
  private void setInputsFromBook() {
    setTitleInput();
    setAuthorInput();
    setDateInput();
    setLanguageInput();
    setFormatInput();
    setUsedInput();
  }

  // get the input values and set them to the book object
  private void setBookFromInputs() {
    setBookTitle();
    setBookAuthor();
    setBookDate();
    setBookLanguage();
    setBookFormat();
    setBookUsed();
  }

  private boolean validateInputs(){
    return validateTitle();
  }

  private boolean validateTitle() {
    String title = mBinding.titleEdit.getText().toString();
    if(title.trim().isEmpty()){
      mBinding.titleEdit.setError(
        mFragmentContext.getString(R.string.cannot_be_empty));
      return false;
    }else{
      return true;
    }
  }

  private void setBookDate() {
    mBook.setDate(mDate);
  }

  private void setBookUsed() {
    mBook.setUsed(mBinding.usedCheckbox.isChecked());
  }

  private void setBookFormat() {
    switch(mBinding.formatGroup.getCheckedRadioButtonId()){
      case R.id.paperback_button: {
        mBook.setFormat(Book.FORMAT_PAPERBACK);
        break;
      }
      case R.id.hardcover_button: {
        mBook.setFormat(Book.FORMAT_HARDCOVER);
        break;
      }
      default:{
        Log.e(TAG, "setBookFormat: unexpected format radio button");
      }
    }
  }

  private void setFormatInput(){
    switch(mBook.getFormat()){
      case Book.FORMAT_HARDCOVER:{
        mBinding.formatGroup.check(R.id.hardcover_button);
        break;
      }
      case Book.FORMAT_PAPERBACK:{
        mBinding.formatGroup.check(R.id.paperback_button);
        break;
      }
      default:{
        Log.e(TAG, "setFormatInput: unexpected book format value");
      }
    }
  }

  private void setBookLanguage() {
    mBook.setLanguage(mBinding.languageSpinner.getSelectedItemPosition());
  }

  private void setBookAuthor() {
    mBook.setAuthor(mBinding.authorEdit.getText().toString());
  }

  private void setBookTitle() {
    mBook.setTitle(mBinding.titleEdit.getText().toString());
  }

  void setDate(Date date) {
    mDate = date;
    mBinding.dateButton.setText(DateFormat.getLongDateFormat(mFragmentContext).format(date));
  }

  private void setUsedInput() {
    mBinding.usedCheckbox.setChecked(mBook.isUsed());
  }

  private void setLanguageInput() {
    mBinding.languageSpinner.setSelection(mBook.getLanguage());
  }

  private void setDateInput() {
    mDate = mBook.getDate();
    mBinding.dateButton.setText(
      DateFormat.getLongDateFormat(mFragmentContext).format(mDate));
  }

  private void setAuthorInput() {
    mBinding.authorEdit.setText(mBook.getAuthor());
  }

  private void setTitleInput() {
    mBinding.titleEdit.setText(mBook.getTitle());
  }

  private void setupListeners() {
    mBinding.dateButton.setOnClickListener(
      v -> mFragment.showDatePickerDialog(mBook.getDate()));
    mBinding.fab.setOnClickListener(v->saveBook());
  }

  private void hideKeyboard(){
    InputMethodManager imm
      = (InputMethodManager) mFragmentContext.getSystemService(Context.INPUT_METHOD_SERVICE);
    View view = mBinding.getRoot();
    if(imm != null) {
      imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
  }
}

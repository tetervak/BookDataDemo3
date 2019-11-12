package ca.javateacher.bookdatademo3.ui.details.view;

import android.content.Context;
import android.text.format.DateFormat;
import android.util.Log;

import androidx.lifecycle.LiveData;

import ca.javateacher.bookdatademo3.R;
import ca.javateacher.bookdatademo3.databinding.ViewBookBinding;
import ca.javateacher.bookdatademo3.model.Book;

public class ViewBookPresenter {

  private static final String TAG = "ViewBookPresenter";

  private final ViewBookFragment mFragment;
  private final Context mFragmentContext;
  private final ViewBookBinding mBinding;
  private ViewBookViewModel mViewModel;
  private long mBookId;
  private Book mBook;

  public ViewBookPresenter(ViewBookFragment fragment, ViewBookBinding binding) {
    mFragment = fragment;
    mFragmentContext = fragment.getContext();
    mBinding = binding;
  }

  public void editBook() {
    mFragment.getNavigator().viewBookToEditBook(mBookId);
  }

  public void init(ViewBookViewModel viewModel, long bookId) {
    mViewModel = viewModel;
    mBookId = bookId;
    mBook = new Book();
    LiveData<Book> bookData = mViewModel.getBookData(mBookId);
    bookData.observe(mFragment, book -> {
      if(book != null){
        mBook = book;
        setOutputsFromBook();
      }
    });
    setOutputsFromBook();
    setupListeners();
  }

  private void setupListeners() {
    mBinding.fab.setOnClickListener(v -> editBook());
  }

  private void setOutputsFromBook() {
    setTitleOutput();
    setAuthorOutput();
    setDateOutput();
    setLanguageOutput();
    setFormatOutput();
    setUsedOutput();
  }

  private void setUsedOutput() {
    if(mBook.isUsed()){
      mBinding.usedOutput.setText(R.string.used_book);
    }else{
      mBinding.usedOutput.setText(R.string.new_book);
    }
  }

  private void setFormatOutput() {
    switch(mBook.getFormat()){
      case Book.FORMAT_HARDCOVER:{
        mBinding.formatOutput.setText(R.string.hardcover_label);
        break;
      }
      case Book.FORMAT_PAPERBACK:{
        mBinding.formatOutput.setText(R.string.paperback_label);
        break;
      }
      default:{
        Log.e(TAG, "setFormatInput: unexpected book format value");
      }
    }
  }

  private void setLanguageOutput() {
    String[] languages
      = mFragmentContext.getResources().getStringArray(R.array.language_list);
    mBinding.languageOutput.setText(languages[mBook.getLanguage()]);
  }

  private void setDateOutput() {
    mBinding.dateOutput.setText(
      DateFormat.getLongDateFormat(mFragmentContext).format(mBook.getDate()));
  }

  private void setAuthorOutput() {
    mBinding.authorOutput.setText(mBook.getAuthor());
  }

  private void setTitleOutput() {
    mBinding.titleOutput.setText(mBook.getTitle());
  }

  public void deleteBook() {
    mViewModel.deleteBook(mBook);
    mFragment.getNavigator().toBookList();
  }
}

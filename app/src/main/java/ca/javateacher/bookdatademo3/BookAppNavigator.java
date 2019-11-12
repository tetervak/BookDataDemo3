package ca.javateacher.bookdatademo3;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import ca.javateacher.bookdatademo3.ui.details.edit.EditBookFragment;
import ca.javateacher.bookdatademo3.ui.details.view.ViewBookFragment;

public class BookAppNavigator {

  private NavController mNavController;

  public BookAppNavigator(NavController navController){
    mNavController = navController;
  }

  public BookAppNavigator(Fragment fragment){
    mNavController
      = Navigation.findNavController(fragment.getView());
  }

  public BookAppNavigator(AppCompatActivity activity, int resId){
    mNavController
      = Navigation.findNavController(activity, resId);
  }

  public void toBookList(){
    mNavController.navigate(R.id.action_to_BookList);
  }

  public void bookListToNewBook(){
    mNavController.navigate(R.id.action_BookList_to_NewBook);
  }

  public void bookListToViewBook(long bookId){
    Bundle args = new Bundle();
    args.putLong(ViewBookFragment.ARG_BOOK_ID, bookId);
    mNavController.navigate(R.id.action_BookList_to_ViewBook, args);
  }

  public void viewBookToEditBook(long bookId){
    Bundle args = new Bundle();
    args.putLong(EditBookFragment.ARG_BOOK_ID, bookId);
    mNavController.navigate(R.id.action_ViewBook_to_EditBook, args);
  }
}

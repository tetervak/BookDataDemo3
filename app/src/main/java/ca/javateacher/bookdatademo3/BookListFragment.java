/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import ca.javateacher.bookdatademo3.viewmodel.BookListViewModel;

public class BookListFragment extends BookAppFragment {

  private static final String TAG = "BookListFragment";

  private BookListPresenter mPresenter;

  public BookListFragment() {
    // Required empty public constructor
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // notify that the fragment has own menu
    setHasOptionsMenu(true);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_book_list, container, false);
    FloatingActionButton fab = view.findViewById(R.id.fab);
    fab.setOnClickListener(v -> addBook());

    RecyclerView recyclerView = view.findViewById(R.id.book_list);
    assert recyclerView != null;
    mPresenter = new BookListPresenter(this, recyclerView);
    return view;
  }

  private void addBook() {
    getNavigator().bookListToNewBook();
  }

  @Override
  public void onActivityCreated(@Nullable Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);
    BookListViewModel viewModel
      = ViewModelProviders.of(this).get(BookListViewModel.class);
    mPresenter.init(viewModel);
  }

  @Override
  public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
    super.onCreateOptionsMenu(menu, inflater);
    inflater.inflate(R.menu.menu_book_list, menu);
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.menu_add) {
      addBook();
      return true;
    }
    return super.onOptionsItemSelected(item);
  }
}

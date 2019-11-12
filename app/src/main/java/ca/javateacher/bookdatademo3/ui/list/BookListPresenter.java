/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3.ui.list;

import android.content.Context;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import ca.javateacher.bookdatademo3.model.Book;

class BookListPresenter{

  private final BookListFragment mFragment;
  private final BookListAdapter mAdapter;

  BookListPresenter(BookListFragment fragment, RecyclerView recyclerView) {
    mFragment = fragment;

    // make the dividers between the books in the list
    Context context = fragment.getContext();
    assert context != null;
    LinearLayoutManager layoutManager = new LinearLayoutManager(context);
    recyclerView.setLayoutManager(layoutManager);
    DividerItemDecoration divider =
        new DividerItemDecoration(context, layoutManager.getOrientation());
    recyclerView.addItemDecoration(divider);

    mAdapter = new BookListAdapter(fragment);
    recyclerView.setAdapter(mAdapter);
  }

  void init(BookListViewModel viewModel) {
    LiveData<List<Book>> bookListData = viewModel.getBookListData();
    bookListData.observe(mFragment, mAdapter::setBookList);
  }
}

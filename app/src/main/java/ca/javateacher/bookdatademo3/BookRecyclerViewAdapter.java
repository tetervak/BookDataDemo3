/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import java.util.Locale;

import ca.javateacher.bookdatademo3.databinding.ListItemBinding;
import ca.javateacher.bookdatademo3.model.Book;

class BookRecyclerViewAdapter
    extends RecyclerView.Adapter<BookRecyclerViewAdapter.ViewHolder> {

  private final BookListFragment mFragment;
  private List<Book> mBookList;

  BookRecyclerViewAdapter(BookListFragment fragment) {
    mFragment = fragment;
  }

  @Override
  @NonNull
  public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ListItemBinding binding = ListItemBinding.inflate(
        LayoutInflater.from(parent.getContext()), parent, false);
    return new ViewHolder(binding);
  }

  @Override
  public void onBindViewHolder(final ViewHolder holder, int position) {
    Book book = mBookList.get(position);
    holder.mBinding.setCount(
        String.format(Locale.getDefault(),"%d.",position + 1));
    holder.mBinding.setBook(book);
    holder.itemView.setTag(book.getRowId());
    holder.itemView.setOnClickListener(this::selectItem);
  }

  private void selectItem(View view) {
    mFragment.getNavigator().bookListToViewBook((Long)view.getTag());
  }

  @Override
  public int getItemCount() {
    return (mBookList != null) ? mBookList.size() : 0;
  }

  public void setBookList(List<Book> bookList) {
    mBookList = bookList;
    notifyDataSetChanged();
  }

  class ViewHolder extends RecyclerView.ViewHolder {

    final ListItemBinding mBinding;

    ViewHolder(ListItemBinding binding) {
      super(binding.getRoot());
      mBinding = binding;
    }
  }
}

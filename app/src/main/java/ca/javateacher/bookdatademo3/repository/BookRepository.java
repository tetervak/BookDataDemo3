/* Alex Tetervak, Sheridan College, Ontario */

package ca.javateacher.bookdatademo3.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

import ca.javateacher.bookdatademo3.db.BookDao;
import ca.javateacher.bookdatademo3.db.BookEntity;
import ca.javateacher.bookdatademo3.model.Book;

@Singleton
public class BookRepository {

  private BookDao mBookDao;

  // no, it cannot be private
  @SuppressWarnings("WeakerAccess")
  @Inject
  public BookRepository(BookDao bookDao) {
    mBookDao = bookDao;
  }

  public LiveData<List<Book>> getBookListData(){
    return Transformations.map(mBookDao.getBookListData(),this::entities2Books);
  }

  private List<Book> entities2Books(List<BookEntity> entities){
    List<Book> books = new ArrayList<>();
    for(BookEntity entity: entities){
      books.add(entity2Book(entity));
    }
    return books;
  }

  public LiveData<Book> getBookDataById(long bookId){
    return Transformations.map(mBookDao.getBookDataById(bookId),this::entity2Book);
  }

  private Book entity2Book(BookEntity entity) {
    Book book = new Book();
    book.setRowId(entity.getId());
    book.setTitle(entity.getTitle());
    book.setAuthor(entity.getAuthor());
    book.setDate(entity.getDate());
    book.setFormat(entity.getFormat());
    book.setLanguage(entity.getLanguage());
    book.setUsed(entity.isUsed());
    return book;
  }

  public void saveBook(Book book) {
    if (book.getRowId() == null) {
      Thread thread = new Thread(() -> mBookDao.insertBook(book2Entity(book)));
      thread.start();
    } else {
      Thread thread = new Thread(() -> mBookDao.updateBook(book2Entity(book)));
      thread.start();
    }
  }

  private BookEntity book2Entity(Book book){
    BookEntity entity = new BookEntity();
    entity.setId((book.getRowId() == null) ? 0 : book.getRowId());
    entity.setTitle(book.getTitle());
    entity.setAuthor(book.getAuthor());
    entity.setDate(book.getDate());
    entity.setFormat(book.getFormat());
    entity.setLanguage(book.getLanguage());
    entity.setUsed(book.isUsed());
    return entity;
  }

  public void deleteBook(Book book) {
    if (book.getRowId() != null) {
      Thread thread = new Thread(() -> mBookDao.deleteBookById(book.getRowId()));
      thread.start();
    }
  }

}

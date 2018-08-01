package pl.jstk.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import pl.jstk.to.BookAndInfoTo;
import pl.jstk.to.BookTo;

public interface BookService {

	List<BookTo> findAllBooks();

	List<BookTo> findBooksByTitle(String title);

	List<BookTo> findBooksByAuthor(String author);

	BookTo getOneById(Long id);

	BookTo saveBook(BookTo book);

	@PreAuthorize("hasRole('ADMIN')")
	void deleteBook(Long id);

	BookAndInfoTo findBookByManyParametersWithInformation(String author, String title);
}

package pl.jstk.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.jstk.entity.BookEntity;
import pl.jstk.mapper.BookMapper;
import pl.jstk.model.Information;
import pl.jstk.repository.BookRepository;
import pl.jstk.service.BookService;
import pl.jstk.to.BookAndInfoTo;
import pl.jstk.to.BookTo;

@Service
@Transactional(readOnly = true)
public class BookServiceImpl implements BookService {

	private BookRepository bookRepository;

	@Autowired
	public BookServiceImpl(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	@Override
	public List<BookTo> findAllBooks() {
		return BookMapper.map2To(bookRepository.findAll());
	}

	@Override
	public List<BookTo> findBooksByTitle(String title) {
		return BookMapper.map2To(bookRepository.findBookByTitle(title));
	}

	@Override
	public List<BookTo> findBooksByAuthor(String author) {
		return BookMapper.map2To(bookRepository.findBookByAuthor(author));
	}

	@Override
	@Transactional
	public BookTo saveBook(BookTo book) {
		BookEntity entity = BookMapper.map(book);
		entity = bookRepository.save(entity);
		return BookMapper.map(entity);
	}

	@Override
	@Transactional
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);

	}

	@Override
	public BookTo getOneById(Long id) {

		return BookMapper.map(bookRepository.getOne(id));
	}

	@Override
	public BookAndInfoTo findBookByManyParametersWithInformation(String author, String title) {

		List<BookTo> listBookTo = null;

		if ("".equals(title) && !("".equals(author))) {

			listBookTo = findBooksByAuthor(author);

		} else if ("".equals(author) && !("".equals(title))) {

			listBookTo = findBooksByTitle(title);

		} else if (!"".equals(author) && !("".equals(title))) {

			List<BookEntity> filteredList = bookRepository.findBookByTitle(title);
			filteredList.retainAll(bookRepository.findBookByAuthor(author));
			listBookTo = BookMapper.map2To(filteredList);

		}

		String books = (listBookTo.size() == 1) ? " book" : " books";
		Information info = new Information("Found " + listBookTo.size() + books + " match to : " + title + " " + author,
				!listBookTo.isEmpty());

		return new BookAndInfoTo(info, listBookTo);
	}

}

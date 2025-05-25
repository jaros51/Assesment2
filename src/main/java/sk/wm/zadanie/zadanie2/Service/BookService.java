package sk.wm.zadanie.zadanie2.Service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import sk.wm.zadanie.zadanie2.DAO.BookEntity;
import sk.wm.zadanie.zadanie2.DTO.BookDto;
import sk.wm.zadanie.zadanie2.Repository.BookRepository;
//import sk.wm.zadanie.zadanie2.Mapping.BookMapper;
import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getBooks() {
        // Logic to get books
        List<BookEntity> books = this.bookRepository.findAll();
        List<BookDto> booksCopy = new ArrayList<>();

        for(BookEntity book : books ){
            ModelMapper modelMapper = new ModelMapper();
            BookDto bookDto = modelMapper.map(book, BookDto.class);
            booksCopy.add(bookDto);
        }
        return booksCopy;
    }

//    public BookDto getBookById(long id) {
//        // Logic to get book by id
//        BookEntity book = this.bookRepository.findById(id).orElse(null);
//        if (book != null) {
//            ModelMapper modelMapper = new ModelMapper();
//            return modelMapper.map(book, BookDto.class);
//        }
//        return null; // or throw an exception
//    }

}

package com.nikron.springboot.librarybook.mapper;

import com.nikron.springboot.librarybook.dto.BookDTO;
import com.nikron.springboot.librarybook.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookMapper {
    @NonNull private final AuthorMapper authorMapper;
    @NonNull private final GenreMapper genreMapper;
    public BookDTO bookCreateDTO(Book book){
        BookDTO dtoBook = new BookDTO(
                book.getBookName(),
                authorMapper.authorDTO(book.getAuthor()),
                genreMapper.genreDTO(book.getGenre()),
                book.getCreateDate()
         );
        dtoBook.setId(book.getId());
        return dtoBook;
    }

    public Book dtoToBook(BookDTO bookDTO){
        return new Book(
                bookDTO.getBookName(),
                authorMapper.dtoToAuthor(bookDTO.getAuthor()),
                genreMapper.dtoToGenre(bookDTO.getGenre()),
                bookDTO.getCreateDate()
        );
    }
}

package com.nikron.springboot.librarybook.mapper;

import com.nikron.springboot.librarybook.dto.BookCreateDTO;
import com.nikron.springboot.librarybook.entity.Book;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BookMapper {
    @NonNull private final AuthorMapper authorMapper;
    @NonNull private final GenreMapper genreMapper;
    public BookCreateDTO bookCreateDTO(Book book){
        BookCreateDTO dtoBook = new BookCreateDTO(
                book.getBookName(),
                authorMapper.authorDTO(book.getAuthor()),
                genreMapper.genreDTO(book.getGenre()),
                book.getCreateDate()
         );
        dtoBook.setId(book.getId());
        return dtoBook;
    }

    public Book dtoToBook(BookCreateDTO bookCreateDTO){
        return new Book(
                bookCreateDTO.getBookName(),
                authorMapper.dtoToAuthor(bookCreateDTO.getAuthor()),
                genreMapper.dtoToGenre(bookCreateDTO.getGenre()),
                bookCreateDTO.getCreateDate()
        );
    }
}

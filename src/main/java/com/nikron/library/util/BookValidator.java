package com.nikron.library.util;

import com.nikron.library.dao.BookDAO;
import com.nikron.library.model.Book;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class BookValidator implements Validator {
    public BookValidator(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    private final BookDAO bookDAO;

    @Override
    public boolean supports(Class<?> clazz) {
        return Book.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Book book = (Book) target;

        if (bookDAO.show(book).isPresent() && book.getId() != bookDAO.show(book).get().getId()){
            errors.rejectValue("name", "", "Name and author already taken.");
        }
    }
}

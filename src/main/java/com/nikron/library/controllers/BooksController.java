package com.nikron.library.controllers;

import com.nikron.library.dao.BookDAO;
import com.nikron.library.dao.PersonDAO;
import com.nikron.library.model.Book;
import com.nikron.library.model.Person;
import com.nikron.library.util.BookValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookValidator bookValidator;

    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO, BookValidator bookValidator) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
        this.bookValidator = bookValidator;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, @ModelAttribute("person") Person person, Model model){
        Book book = bookDAO.show(id);
        model.addAttribute("book", book);
        if (book.getUserTakeBook() != null){
            model.addAttribute("take", true);
            model.addAttribute("personBookTake", personDAO.show(book.getUserTakeBook()));
        } else {
            model.addAttribute("people", personDAO.index());
            model.addAttribute("take", false);
        }
        return "books/show";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id){
        bookDAO.deleteBook(id);
        return "redirect:/books/index";
    }

    @GetMapping("/new")
    public String formCreateBook(@ModelAttribute("book") Book book, Model model){
        model.addAttribute("book", book);
        return "/books/create";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) return "/books/create";
        bookDAO.createBook(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, @ModelAttribute("book") Book book, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }

    @PatchMapping("/{id}")
    public String updateBook(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        bookValidator.validate(book, bindingResult);
        if (bindingResult.hasErrors()) return "books/edit";
        bookDAO.updateBook(book);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/take")
    public String takeBook(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        System.out.println("Book id: " + id + " Person id: " + person.getId());
        bookDAO.updateBook(id, person.getId());
        return "redirect:/books";
    }

    @PatchMapping("/{id}/untake")
    public String untakeBook(@PathVariable("id") int id){
        bookDAO.updateBook(id, null);
        return "redirect:/books";
    }
}

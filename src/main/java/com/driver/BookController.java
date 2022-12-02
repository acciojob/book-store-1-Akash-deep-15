package com.driver;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    private int id = 1;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        book.setId(this.id);
        this.id++;
        bookList.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // get request /get-book-by-id/{id}
    // pass id as path variable
    // getBookById()

    @GetMapping("/get-book-by-id/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {

        Book ans = null;
        for(Book B: bookList) {
            if(B.getId() == id) {
                ans = B;
            }
        }
        return new ResponseEntity(ans,HttpStatus.OK);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()

    @DeleteMapping("/delete-book-by-id/{id}")
    public ResponseEntity deleteId(@PathVariable("id") int id) {
        for(Book B: bookList) {
            if(B.getId() == id) {
                bookList.remove(B);
            }
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    // get request /get-all-books
    // getAllBooks()

    @GetMapping("/get-all-books")
    public ResponseEntity<List<Book>> getAll() {
        return new ResponseEntity(bookList, HttpStatus.ACCEPTED);
    }

    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-all-books")
    public ResponseEntity deleteAll() {
        for(Book B: bookList) {
            if(bookList.contains(B))
            bookList.remove(B);
        }
        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-author")
    public ResponseEntity<List<Book>> byAuthor(@RequestParam("author") String name) {
        List<Book> ans= new ArrayList<>();

        for(Book B: bookList) {
            if(B.getAuthor() == name) {
                ans.add(B);
            }
        }

        return new ResponseEntity(ans, HttpStatus.CREATED);
    }

    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<List<Book>> bygenre(@RequestParam("genre") String name) {
        List<Book> ans= new ArrayList<>();

        for(Book B: bookList) {
            if(B.getGenre() == name) {
                ans.add(B);
            }
        }

        return new ResponseEntity(ans, HttpStatus.CREATED);
    }
}

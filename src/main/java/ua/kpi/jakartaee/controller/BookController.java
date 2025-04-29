package ua.kpi.jakartaee.controller;

import jakarta.ejb.EJB;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import ua.kpi.jakartaee.dto.BookDto;
import ua.kpi.jakartaee.dto.BookSearchQuery;
import ua.kpi.jakartaee.exceptions.BookAlreadyExistsException;
import ua.kpi.jakartaee.exceptions.BookNotFoundException;
import ua.kpi.jakartaee.service.BookService;

import java.util.List;
import java.util.Map;

@Path("/library/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookController {
    @EJB
    private BookService bookService;

    @POST
    public Response addBook(BookDto bookDto) {
        try {
            bookService.addBook(bookDto);
            return Response.status(Response.Status.CREATED)
                    .entity(Map.of(
                            "success", true,
                            "message", "Book added successfully"
                    ))
                    .build();
        } catch (BookAlreadyExistsException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(Map.of(
                            "success", false,
                            "message", e.getMessage()
                    ))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "success", false,
                            "message", "Failed to add book: " + e.getMessage()
                    ))
                    .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@PathParam("id") String id) {
        try {
            bookService.deleteBookById(id);
            return Response.status(Response.Status.OK)
                    .entity(Map.of(
                            "success", true,
                            "message", "Book deleted successfully"
                    ))
                    .build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of(
                            "success", false,
                            "message", e.getMessage()
                    ))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "success", false,
                            "message", "Failed to delete book: " + e.getMessage()
                    ))
                    .build();
        }
    }

    @GET
    public Response getBooks(
            @BeanParam BookSearchQuery bookSearchQuery,
            @DefaultValue("1") @QueryParam("page") int pageNumber,
            @DefaultValue("10") @QueryParam("size") int pageSize
    ) {
        try {
            List<BookDto> books = bookService.getBooksWithPaginationAndFiltration(bookSearchQuery, pageNumber, pageSize);
            return Response.status(Response.Status.OK)
                    .entity(Map.of(
                            "success", true,
                            "result", books
                    )).build();
        } catch (Exception e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "success", false,
                            "message", "Failed to extract books: " + e.getMessage()
                    ))
                    .build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@PathParam("id") String id, BookDto bookDto) {
        try {
            bookDto.setBookId(id);
            bookService.updateBook(bookDto);
            return Response.status(Response.Status.OK)
                    .entity(Map.of(
                            "success", true,
                            "message", "Book updated successfully"
                    ))
                    .build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of(
                            "success", false,
                            "message", e.getMessage()
                    ))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "success", false,
                            "message", "Failed to update book: " + e.getMessage()
                    ))
                    .build();
        }
    }

    @PATCH
    @Path("/{id}")
    public Response updateBookPartially(@PathParam("id") String id, BookDto bookDto) {
        try {
            bookDto.setBookId(id);
            bookService.updateBookPartially(bookDto);
            return Response.status(Response.Status.OK)
                    .entity(Map.of(
                            "success", true,
                            "message", "Book partially updated successfully"
                    ))
                    .build();
        } catch (BookNotFoundException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(Map.of(
                            "success", false,
                            "message", e.getMessage()
                    ))
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(Map.of(
                            "success", false,
                            "message", "Failed to partially update book: " + e.getMessage()
                    ))
                    .build();
        }
    }
}

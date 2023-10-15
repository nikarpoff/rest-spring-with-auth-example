package students.marks.front.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import students.marks.db.exception.DatabaseException;
import students.marks.db.service.MarkService;
import students.marks.db.service.StudentService;
import students.marks.front.exception.ForbiddenException;
import students.marks.model.Mark;
import students.marks.model.MarkTable;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/marks_table")
public class MarksApiController {

    final MarkService markService;

    final StudentService studentService;

    public MarksApiController(MarkService markService, StudentService studentService) {
        this.markService = markService;
        this.studentService = studentService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of marks returned", content = @Content(mediaType = "application/json", schema = @Schema(implementation = MarkTable.class))),

            @ApiResponse(responseCode = "404", description = "Marks of all students on one subject subject not found") })
    @RequestMapping(value = "/marks",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<List<MarkTable>> getMarks() {
        try {
            return new ResponseEntity<List<MarkTable>>(markService.findAllStudentsWithMarksOnOneSubject(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<List<MarkTable>>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),

            @ApiResponse(responseCode = "400", description = "Invalid student's name or number of laboratory works or mark"),

            @ApiResponse(responseCode = "404", description = "Student or number of laboratory work not found") })
    @RequestMapping(value = "/api/marks",
            consumes = { "application/json" },
            method = RequestMethod.PUT)
    public ResponseEntity<Mark> updateMark(@NotNull @RequestBody Mark body, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            return new ResponseEntity<>(markService.add(body), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

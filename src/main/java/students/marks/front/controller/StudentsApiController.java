package students.marks.front.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import students.marks.db.exception.DatabaseException;
import students.marks.db.service.StudentService;
import students.marks.front.exception.ForbiddenException;
import students.marks.model.Student;

import java.security.Principal;

@RestController
@RequestMapping("/marks_table/api")
public class StudentsApiController {

    final StudentService studentService;

    public StudentsApiController(StudentService studentService) {
        this.studentService = studentService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successful operation"),

            @ApiResponse(responseCode = "400", description = "Invalid student's name"),

            @ApiResponse(responseCode = "404", description = "Student not found"),

            @ApiResponse(responseCode = "409", description = "Student already exists") })
    @RequestMapping(value = "/students",
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<Student> addStudent(@NotNull @RequestBody Student body, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            return new ResponseEntity<>(studentService.add(body), HttpStatus.OK);
        } catch (DatabaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),

            @ApiResponse(responseCode = "404", description = "Student not found") })
    @RequestMapping(value = "/students",
            method = RequestMethod.DELETE)
    public ResponseEntity<Student> deleteStudent(@NotNull @RequestParam(value = "student_id", required = true) Long studentId, Principal principal) {
        if (principal == null) {
            throw new ForbiddenException();
        }

        try {
            studentService.delete(studentId);
        } catch (DatabaseException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

}

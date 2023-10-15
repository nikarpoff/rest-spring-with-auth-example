package students.marks.db.exception;

public class DatabaseException extends Exception {


    public DatabaseException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "DatabaseException{" + super.getMessage() + "}";
    }

}

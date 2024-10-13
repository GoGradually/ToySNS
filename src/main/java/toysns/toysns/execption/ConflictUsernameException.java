package toysns.toysns.execption;

public class ConflictUsernameException extends IllegalStateException{
    public ConflictUsernameException(String s) {
        super(s);
    }

    public ConflictUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictUsernameException(Throwable cause) {
        super(cause);
    }

    public ConflictUsernameException() {
    }
}

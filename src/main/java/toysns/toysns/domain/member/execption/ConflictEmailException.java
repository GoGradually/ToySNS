package toysns.toysns.domain.member.execption;

public class ConflictEmailException extends IllegalStateException{
    public ConflictEmailException(String s) {
        super(s);
    }

    public ConflictEmailException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConflictEmailException(Throwable cause) {
        super(cause);
    }

    public ConflictEmailException() {
    }
}

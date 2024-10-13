package toysns.toysns.execption;

public class MemberNotFoundException extends IllegalStateException{
    public MemberNotFoundException() {
    }

    public MemberNotFoundException(String s) {
        super(s);
    }

    public MemberNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MemberNotFoundException(Throwable cause) {
        super(cause);
    }
}

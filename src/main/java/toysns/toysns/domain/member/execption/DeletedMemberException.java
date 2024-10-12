package toysns.toysns.domain.member.execption;

public class DeletedMemberException extends IllegalStateException{
    public DeletedMemberException() {
    }

    public DeletedMemberException(String s) {
        super(s);
    }

    public DeletedMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeletedMemberException(Throwable cause) {
        super(cause);
    }
}

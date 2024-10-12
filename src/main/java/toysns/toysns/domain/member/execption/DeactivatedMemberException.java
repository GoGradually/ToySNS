package toysns.toysns.domain.member.execption;

public class DeactivatedMemberException extends IllegalStateException{
    public DeactivatedMemberException() {
    }

    public DeactivatedMemberException(String s) {
        super(s);
    }

    public DeactivatedMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeactivatedMemberException(Throwable cause) {
        super(cause);
    }
}

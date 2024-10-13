package toysns.toysns.execption.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import toysns.toysns.execption.*;

@ControllerAdvice
public class MemberControllerAdvice {

    @ExceptionHandler(ConflictEmailException.class)
    public ResponseEntity<String> conflictEmailException(ConflictEmailException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This email is already exist");
    }

    @ExceptionHandler(ConflictUsernameException.class)
    public ResponseEntity<String> conflictUsernameException(ConflictUsernameException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This username is already exist");
    }

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> memberNotFoundException(MemberNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Not Found");
    }
    @ExceptionHandler(DeactivatedMemberException.class)
    public ResponseEntity<String> deactivatedMemberException(DeactivatedMemberException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Not Found");
    }
    @ExceptionHandler(DeletedMemberException.class)
    public ResponseEntity<String> deletedMemberException(DeletedMemberException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Member Not Found");
    }
}

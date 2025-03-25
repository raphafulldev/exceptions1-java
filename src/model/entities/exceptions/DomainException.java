package model.entities.exceptions;

import java.io.Serial;
import java.io.Serializable;

public class DomainException extends RuntimeException {


    @Serial
    private static final long serialVersionUID = -19801366197770851L;

    public DomainException (String msg){
        super(msg);
    }
}

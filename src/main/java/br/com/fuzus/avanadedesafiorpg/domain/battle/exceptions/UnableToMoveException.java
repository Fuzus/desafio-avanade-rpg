package br.com.fuzus.avanadedesafiorpg.domain.battle.exceptions;

public class UnableToMoveException extends RuntimeException{

    public UnableToMoveException(String message) {
        super(message);
    }

}

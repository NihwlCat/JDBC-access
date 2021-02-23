package db;

public class DbIntegrityException extends RuntimeException {

    public DbIntegrityException (String m){
        super(m);
    }

}

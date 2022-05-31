package exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException(String name) {
        super("Genre name not found : " + name);
    }

}
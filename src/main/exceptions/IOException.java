package main.exceptions;

/**
 * Created by earayu on 2016/7/7.
 */
public class IOException extends RuntimeException
{
    static final long serialVersionUID = -7034897190745766939L;


    public IOException() {
        super();
    }


    public IOException(String message) {
        super(message);
    }


    public IOException(String message, Throwable cause) {
        super(message, cause);
    }


    public IOException(Throwable cause) {
        super(cause);
    }

}

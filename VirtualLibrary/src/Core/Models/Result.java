/**
 * Created by Andrii Yeremenko on 11/7/23.
 */

package Core.Models;

/**
 * Result class. Stores data, message and success. Used for returning values with message and success.
 * @param <T>
 */
public class Result<T>{

    private T data;
    private String message;
    private final boolean success;

    /**
     * Constructor. On creation sets success to passed value
     * @param success
     */
    public Result(boolean success){
        this.success = success;
    }

    /**
     * Constructor. On creation sets data and success to passed values
     * @param data
     * @param success
     */
    public Result(T data, boolean success){
        this.data = data;
        this.success = success;
    }

    /**
     * Constructor. On creation sets message and success to passed values
     * @param message
     * @param success
     */
    public Result(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    /**
     * Constructor. On creation sets data, message and success to passed values
     * @param data
     * @param message
     * @param success
     */
    public Result(T data, String message, boolean success){
        this.data = data;
        this.message = message;
        this.success = success;
    }

    /**
     * Getter for data.
     * @return T Data
     */
    public T getData(){
        return data;
    }

    /**
     * Getter for message
     * @return String Message
     */
    public String getMessage(){
        return message;
    }

    /**
     * Getter for success
     * @return boolean Success
     */
    public boolean getSuccess(){
        return success;
    }
}

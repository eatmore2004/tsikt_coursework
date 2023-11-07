package Core.Models;

public class Result<T>{
    private T data;
    private String message;
    private final boolean success;

    public Result(boolean success){
        this.success = success;
    }

    public Result(T data, boolean success){
        this.data = data;
        this.success = success;
    }

    public Result(String message, boolean success){
        this.message = message;
        this.success = success;
    }

    public Result(T data, String message, boolean success){
        this.data = data;
        this.message = message;
        this.success = success;
    }

    public T getData(){
        return data;
    }

    public String getMessage(){
        return message;
    }

    public boolean getSuccess(){
        return success;
    }
}

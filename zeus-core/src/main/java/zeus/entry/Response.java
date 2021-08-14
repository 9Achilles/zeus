package zeus.entry;

public class Response<T> {

    private String code;

    private String message;

    private T data;

    public static Response success(String message) {
        return new Response("200", message, null);
    }

    public static <T> Response success(String message, T data) {
        return new Response("200", message, data);
    }


    public static Response error(String code, String message) {
        return new Response(code, message, null);
    }

    public Response(String code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}

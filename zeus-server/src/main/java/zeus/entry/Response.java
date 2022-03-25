//package zeus.entry;
//
//public class Response<T> {
//
//    private int code;
//
//    private String message;
//
//    private T data;
//
//    public static Response success(String message) {
//        return new Response(200, message, null);
//    }
//
//    public static <T> Response success(String message, T data) {
//        return new Response(200, message, data);
//    }
//
//    public static <T> Response success(T data) {
//        return Response.success("success",data);
//    }
//
//
//    public static Response error(int code, String message) {
//        return new Response(code, message, null);
//    }
//
//    public Response(int code, String message, T data) {
//        this.code = code;
//        this.message = message;
//        this.data = data;
//    }
//
//    public int getCode() {
//        return code;
//    }
//
//    public void setCode(int code) {
//        this.code = code;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
//
//    public T getData() {
//        return data;
//    }
//
//    public void setData(T data) {
//        this.data = data;
//    }
//}

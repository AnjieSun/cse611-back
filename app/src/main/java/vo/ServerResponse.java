package vo;


public class ServerResponse<T> {

    private int status; // 0: success, 1:fail
    private T data;
    private String msg;

    public ServerResponse() {}

    public void setStatus(int status) {
        this.status = status;
    }

    public void setData(T data) {
        this.data = data;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public T getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }
}

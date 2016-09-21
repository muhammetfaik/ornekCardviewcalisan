package info.androidhive.recyclerview;

/**
 * Created by Onur Yenigün on 5.4.2015.
 */
public class GeneralResponse<T> {

    private String status;
    private T data;

    public T getData() { return data; }

    public void setData(T data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}

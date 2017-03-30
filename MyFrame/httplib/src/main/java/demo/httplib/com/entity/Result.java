package demo.httplib.com.entity;

import java.io.Serializable;

/**
 * Created by color on 17/3/21.
 */

public class Result implements Serializable {

    /**
     * err : 0
     * msg : ok
     */

    private int err;
    private String msg;

    public int getErr() {
        return err;
    }

    public void setErr(int err) {
        this.err = err;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}

package demo.httplib.com.entity;

import java.io.Serializable;

/**
 * 实体类的基类
 * Created by color on 17/3/21.
 */

public class BaseEntity<T> implements Serializable {
    private Result result;

    private Content<T> content;

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }

    public Content<T> getContent() {
        return content;
    }

    public void setContent(Content<T> content) {
        this.content = content;
    }
}

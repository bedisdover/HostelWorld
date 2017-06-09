package cn.edu.nju.config;

/**
 * Created by song on 17-2-19.
 *
 * 交互信息
 */
public class MsgInfo {

    /**
     * 消息状态
     */
    private boolean status;

    /**
     * 消息内容
     */
    private String info;

    /**
     * 消息附加对象
     */
    private Object object;

    public MsgInfo(boolean status, String info) {
        this.status = status;
        this.info = info;
    }

    public MsgInfo(boolean status, String info, Object object) {
        this.status = status;
        this.info = info;
        this.object = object;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    @Override
    public String toString() {
        return "MsgInfo{" +
                "status=" + status +
                ", info='" + info + '\'' +
                ", object=" + object +
                '}';
    }
}

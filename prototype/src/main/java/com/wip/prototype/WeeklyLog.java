package com.wip.prototype;

import java.io.*;

/**
 * 具体原型类(ConcretePrototype)
 *
 * @author KyrieCao
 * @date 2019/1/23 21:42
 */
public class WeeklyLog implements Serializable {
    private String name;
    private String date;
    private String content;

    /**
     * 为了简化设计和实现，假设一份工作周报中只有一个附件对象，实际情况中可以包含多个附件，可以通过List等集合对象来实现
     */
    private Attachment attachment;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    public Attachment getAttachment() {
        return attachment;
    }

    /**
     * 克隆方法clone()，此处使用Java语言提供的克隆机制
     * @return weeklyLog
     */
    @Override
    public WeeklyLog clone() {
        Object obj = null;
        try {
            obj = super.clone();
            return (WeeklyLog) obj;
        } catch (CloneNotSupportedException e) {
            System.out.println("不支持复制！");
            return null;
        }
    }

    /**
     * 使用序列化技术实现深克隆
     * @return
     */
    public WeeklyLog deepClone() throws IOException, ClassNotFoundException {
        // 将对象写入流中
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(bao);
        oos.writeObject(this);

        // 将对象从流中取出
        ByteArrayInputStream bis = new ByteArrayInputStream(bao.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bis);
        return (WeeklyLog) ois.readObject();
    }

}

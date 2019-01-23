package com.wip.prototype;


/**
 * 客户类(Client)
 * @author KyrieCao
 * @date 2019/1/23 21:49
 */
public class Client {
    public static void main(String[] args) {
        // 创建原型对象
        WeeklyLog logPreviousOld,logPreviousNew = null;
        // 创建原型对象
        logPreviousOld = new WeeklyLog();
        // 创建附件对象
        Attachment attachment = new Attachment();
        // 将附件添加到周报中
        logPreviousOld.setAttachment(attachment);
        // 调用克隆方法创建克隆对象
        try {
            logPreviousNew = logPreviousOld.deepClone();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 比较周报
        System.out.println("周报是否相同：" + (logPreviousOld == logPreviousNew));
        // 比较附件
        System.out.println("附件是否相同：" + (logPreviousOld.getAttachment() == logPreviousNew.getAttachment()));
    }
}

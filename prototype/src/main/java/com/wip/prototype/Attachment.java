package com.wip.prototype;

import java.io.Serializable;

/**
 * 周报附件类
 * @author KyrieCao
 * @date 2019/1/23 22:07
 */
public class Attachment implements Serializable {
    /**
     * 附件名
     */
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void download() {
        System.out.println("下载附件，文件名为：" + name);
    }
}

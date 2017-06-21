package com.example;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyClass {
    public static void main(String[] args) throws Exception {
        //创建一个实体 版本 默认包名 你自己的app
        Schema schema = new Schema(1, "com.sfsj.asus.dao");
        addNote(schema);
        //导出路径
        new DaoGenerator().generateAll(schema, "/Users/asus/Desktop/Hard Android/MyApp/dao/src/main/java-gen");
    }

    private static void addNote(Schema schema) {
        //一个实体类关联一个表 这里创建表名是note 对应大写的NOTE
        //在刚才的包下创建表 自动转换成大写 包括三部分 文字 评论 和日期
        Entity note = schema.addEntity("Note");
        note.addIdProperty();
        note.addStringProperty("text").notNull();
        note.addStringProperty("comment");
        note.addDateProperty("date");
    }
}

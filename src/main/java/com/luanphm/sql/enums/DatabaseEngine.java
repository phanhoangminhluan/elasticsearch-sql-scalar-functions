package com.luanphm.sql.enums;

/**
 * @author Minh-Luan H. Phan
 * Created on: 2021.10.26 13:45
 */
public enum DatabaseEngine {

    CDP("cdp");

    public final String name;


    DatabaseEngine(String name) {
        this.name = name;
    }
}

package org.liuwy.ioc.overview.domain;

import org.liuwy.ioc.overview.annotation.Super;

/**
 * 管理员
 * 
 * @author liuwy
 * @version 1.0
 * @date 2021/7/26 21:58
 */
@Super
public class Admin extends User {
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}

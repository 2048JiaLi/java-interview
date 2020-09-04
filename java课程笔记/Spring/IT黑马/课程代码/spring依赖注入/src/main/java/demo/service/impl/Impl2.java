package demo.service.impl;

import demo.service.AccountService;

import java.util.Date;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/29 9:49
 */
public class Impl2 implements AccountService {

    private String name;
    private Integer age;
    private Date birthday;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println(this.toString() + "\t" + name+ "\t" + age+ "\t" + birthday);
    }
}

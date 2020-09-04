package demo.service.impl;

import demo.service.AccountService;

import java.util.Date;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/28 9:26
 */
public class AccountServiceImpl implements AccountService {

    /**
     * 如果是经常变化的数据，并不适用于注入方式
     */
    private String name;
    private Integer age;
    private Date birthday;

    public AccountServiceImpl(String name, Integer age, Date birthday) {
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public void saveAccount() {
        System.out.println(this.toString() + "\t" + name+ "\t" + age+ "\t" + birthday);
    }
}

package demo.dao.impl;

import demo.dao.AccountDao;
import org.springframework.stereotype.Repository;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/28 9:28
 */
@Repository("accountDao")
public class AccountDaoImpl implements AccountDao {
    public void saveAccount() {
        System.out.println("保存用户");
    }
}

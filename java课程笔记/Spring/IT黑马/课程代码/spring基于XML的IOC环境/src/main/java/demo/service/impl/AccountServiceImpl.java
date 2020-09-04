package demo.service.impl;

import demo.dao.AccountDao;
import demo.dao.impl.AccountDaoImpl;
import demo.service.AccountService;

/**
 * @author Li W
 * @version 1.8
 * @date 2020/8/28 9:26
 */
public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();

    public AccountServiceImpl() {

    }

    public void saveAccount() {
        accountDao.saveAccount();
    }
}

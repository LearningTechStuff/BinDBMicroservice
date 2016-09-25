package org.soaring.bindb.data;

import org.soaring.bindb.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * Created by andersonkmi on 7/23/2016.
 */
@Repository
public class CreditCardBinRepository extends JdbcDaoSupport {
    @Resource
    private RedisService redisService;

    @Autowired
    public CreditCardBinRepository(DataSource dataSource) {
        setDataSource(dataSource);
    }

    public void save(CreditCardBin creditCardBin) {

    }

    public void update(CreditCardBin creditCardBin) {

    }
}

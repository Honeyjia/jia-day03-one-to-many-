package com.jiacool;

import com.jiacool.dao.CstDao;
import com.jiacool.dao.LnDao;
import com.jiacool.domain.Customer;
import com.jiacool.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {
    @Autowired
    private CstDao cstDao;
    @Autowired
    private LnDao lnDao;

    @Test
    @Transactional //在java代码中 解决no session 问题
    @Rollback(false)
    public void test(){
        //1.查询1号客户
        Customer customer = cstDao.findOne(1l);
        //2.对象导航查询，此客户下的所有关联 联系人   一方 查询 多方
        /**
         * 对象导航查询：
         *      默认使用的是 延迟加载 的形式查询
         *          调用get方法并不会立即发送查询，而是在使用关联对象的时候才会查询
         * 修改配置：将延迟加载改为立即加载
         *      fetch：需要配置到多表映射关系的注解上  Customer
         */
        Set<LinkMan> list = customer.getLinkMans();

        for (LinkMan linkMan : list) {
            System.out.println(linkMan);
        }
    }

    @Test
    @Transactional //在java代码中 解决no session 问题
    @Rollback(false)
    public void test2(){
        /**
         * 从联系人对象导航 查询 它的所属客户  多方查询一方
         *         * moren： 立即加载
         *修改：fetch：需要配置到多表映射关系的注解上 LinkMan
         */
        LinkMan linkMan = lnDao.findOne(2l);
        Customer customer = linkMan.getCustomer();
        System.out.println(customer);
    }
}

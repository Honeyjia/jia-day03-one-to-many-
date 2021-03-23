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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {
    @Autowired
    private CstDao cstDao;

    @Autowired
    private LnDao lnDao;

    @Test
    @Transactional  //配置事务
    @Rollback(false)  //不自动回滚
    public void save(){
        //创建一个客户 和 一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度网盘");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("闲鱼网站");

        //配置 客户 到 联系人 的关系（一对多）
        /*
         从客户的角度上：发送了两条 insert 语句，发送了一条更新语句 更新数据库（更新外键）
         由于我们配置了客户到联系人的关系，客户可以对外键进行维护
         */
        customer.getLinkMans().add(linkMan);
        cstDao.save(customer);
        lnDao.save(linkMan);

    }

    @Test
    @Transactional  //配置事务
    @Rollback(false)  //不自动回滚
    public void save1(){
        //创建一个客户 和 一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度网盘");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("闲鱼网站");

        //从客户 到 联系人 （多对一）
        // 只发送了两条 insert 语句
        // 由于我们配置了联系人到客户的关系，客户可以对外键进行维护
        linkMan.setCustomer(customer);
        cstDao.save(customer);
        lnDao.save(linkMan);
    }

    @Test
    @Transactional  //配置事务
    @Rollback(false)  //不自动回滚
    public void save2(){
        //创建一个客户 和 一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度网盘");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("闲鱼网站");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);
        cstDao.save(customer);
        lnDao.save(linkMan);

    }

    //测试：级联添加
    //保存一个客户的同时，保存客户的所有联系人，需要在操作主体的实体类上，配置casacde属性
    @Test
    @Transactional  //配置事务
    @Rollback(false)  //不自动回滚
    public void save3(){
        //创建一个客户 和 一个联系人
        Customer customer = new Customer();
        customer.setCustName("百度网盘");

        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("闲鱼网站");

        linkMan.setCustomer(customer);
        customer.getLinkMans().add(linkMan);

        cstDao.save(customer);
    }

    //测试： 级联删除   ,删除1号客户的同时，删除1号客户的所有联系人
    @Test
    @Transactional  //配置事务
    @Rollback(false)  //不自动回滚
    public void delete(){
        //查询1号客户
        Customer customer = cstDao.findOne(1l);
        //删除1号客户
        cstDao.delete(customer);
    }
}

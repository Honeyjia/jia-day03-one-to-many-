package com.jiacool.dao;

import com.jiacool.domain.LinkMan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

//联系人的接口
public interface LnDao extends JpaRepository<LinkMan, Long>, JpaSpecificationExecutor<LinkMan> {

}

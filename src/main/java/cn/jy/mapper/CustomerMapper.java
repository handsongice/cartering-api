package cn.jy.mapper;

import cn.jy.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Customer record);

    int insertSelective(Customer record);

    Customer selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Customer record);

    int updateByPrimaryKeyWithBLOBs(Customer record);

    int updateByPrimaryKey(Customer record);

    Customer findByParams(Customer record);
}
package cn.jy.service.impl;

import cn.jy.constent.Constent;
import cn.jy.dto.ResultMap;
import cn.jy.entity.Customer;
import cn.jy.mapper.CustomerMapper;
import cn.jy.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerMapper customerMapper;

    @Override
    public Customer getCustomer(String phone) throws Exception {
        Customer customer = new Customer();
        customer.setPhone(phone);
        return customerMapper.findByParams(customer);
    }

    @Override
    public List<Customer> getCustomerList(Map<String, Object> params) throws Exception {
        return null;
    }

    @Override
    public ResultMap addCustomer(Customer customer) throws Exception {
        //用户名验证
        Customer input = new Customer();
        input.setPhone(customer.getPhone());
        Customer _customer = customerMapper.findByParams(input);
        if(_customer != null && _customer.getId() != null) {
            throw new RuntimeException("手机号重复！");
        }
        //设置创建时间
        customer.setCreateTime(new Date());
        int dbResult = customerMapper.insertSelective(customer);
        if(dbResult <=0){
            throw new RuntimeException(Constent.DB_INSERT_FAILURE);
        }
        return ResultMap.success(Constent.DB_INSERT_SUCCESS);
    }

    @Override
    public ResultMap updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public ResultMap updateMyPassword(Customer customer) {
        return null;
    }

    @Override
    public Customer getCustomerById(Long id) {
        return null;
    }

    @Override
    public ResultMap delCustomer(Long id) {
        return null;
    }
}

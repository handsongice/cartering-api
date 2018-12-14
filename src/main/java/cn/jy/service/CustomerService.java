package cn.jy.service;

import cn.jy.dto.ResultMap;
import cn.jy.entity.Customer;

import java.util.List;
import java.util.Map;

/**
 * @Author: jason ji
 * @Date: 2018/10/26 15:01
 */
public interface CustomerService {
    /**
     * 会员信息
     * @param phone
     * @return
     * @throws Exception
     */
    Customer getCustomer(String phone) throws Exception;
    /**
     * 列表
     * @param params
     * @return
     * @throws Exception
     */
    List<Customer> getCustomerList(Map<String, Object> params) throws Exception;

    /**
     * 插入数据
     * @param customer
     * @return
     */
    ResultMap addCustomer(Customer customer) throws Exception;

    /**
     * 更新数据
     * @param customer
     * @return
     */
    ResultMap updateCustomer(Customer customer);


    /**
     * 更新密码
     * @param customer
     * @return
     */
    ResultMap updateMyPassword(Customer customer);
    /**
     * 获取信息
     * @param id
     * @return
     */
    Customer getCustomerById(Long id);

    /**
     * 逻辑删除
     * @param id
     * @return
     */
    ResultMap delCustomer(Long id);
}

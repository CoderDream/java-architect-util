package com.shanghai.controller;

import com.shanghai.bean.BankAccount;
import com.shanghai.dao.BankAccountDao;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lijm on 2017-11-06.
 */
@RestController
@RequestMapping(value = "/userBank")
@Api(description = "银行用户")
public class BankAccountController{

    @Resource
    BankAccountDao accountDao;

    @ApiOperation(value = "查找银行用户")
    @RequestMapping(value = "/findById" , method = RequestMethod.GET)
    public Object findById(@RequestParam(value = "id",required = false) Long id){

        Iterable<BankAccount> bList;
        if(id!=null){
            BankAccount user = accountDao.findById(id);
            if(user == null){
                return "该数据项不存在";
            }else{
                return "cardNo:" + user.getCardno() + " , name:" + user.getName()+",money:"+user.getMoney();
            }
        }else{
            bList = accountDao.findAll();

        }
        return bList;
    }

    @ApiOperation(value = "姓名查询银行用户信息")
    @RequestMapping(value = "findName",method = RequestMethod.GET)
    public List<BankAccount> findName(@RequestParam(value = "name")String name){

        List<BankAccount> bList = accountDao.findByNameLike(name);
        return bList;
    }

    @ApiOperation(value = "查询银行用户数据")
    @RequestMapping(value = "findIds",method = RequestMethod.GET)
    public List<BankAccount> findIds(@RequestParam(value = "id")Integer id){

        List<BankAccount> bList = accountDao.findOrderById(id);
        return bList;
    }

    @ApiOperation(value = "新增银行数据")
    @RequestMapping(value = "saveBankAccount",method = RequestMethod.POST)
    public String addBankAccount(@RequestParam(value ="cardNo") String cardNo, @RequestParam(value = "name") String name,
                                 @RequestParam(value = "money")BigDecimal money){
        BankAccount account = new BankAccount();
        account.setMoney(money);
        account.setName(name);
        account.setCardno(cardNo);
        accountDao.insertBank_account(cardNo,name,money);
        //accountDao.save(account);
        return " Add success!";//
    }

    @ApiOperation(value = "修改银行数据")
    @RequestMapping(value = "updateBankAccount",method = RequestMethod.POST)
    public String updateBankAccount(@RequestParam(value ="id") Long id, @RequestParam(value = "name") String name){

        accountDao.updateOne(id,name);
        return " update success!";
    }

    @ApiOperation(value = "删除银行数据")
    @RequestMapping(value = "delAccountId",method = RequestMethod.POST)
    public String delBankAccount(@RequestParam(value = "id") Long id){

       BankAccount user = accountDao.findById(id);
       if(user==null){
            return "该数据项不存在!";
       }else{
          // accountDao.delete(id);//jar自带的删除方法
           accountDao.deteteBankAccountById(id);//源生的删除方法
       }
        return "success!";
   }
}
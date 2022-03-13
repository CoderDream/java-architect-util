package com.shanghai.dao;

import com.shanghai.bean.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by lijm on 2017-11-06.
 */
public interface BankAccountDao extends CrudRepository<BankAccount, Long> {

    //根据编号查询
    public BankAccount findById(Long id);


    @Query(value = "select * from t_bank_account where name like %?1%",nativeQuery = true)
    @Modifying
    public List<BankAccount> findByNameLike(String name);//NotLike --- 等价于 SQL 中的 "not like"，比如 findByNameNotLike(String name)；

    public Page<BankAccount> findAll(Pageable pageable);

    //联合查询数据
    @Query(value = "SELECT b.id,a.name as name,b.name,a.age,b.cardno,b.money from user a LEFT JOIN t_bank_account b on a.id=b.id and b.id=?1",nativeQuery = true)
    @Modifying
    public List<BankAccount> findOrderById(Integer id);

    //源生sql插入
    @Query(value = "insert into t_bank_account(cardno,name,money) value(?1,?2,?3)",nativeQuery = true)
    @Modifying
    @Transactional
    public void insertBank_account(String cardNo, String name, BigDecimal money);

    //源生sql修改
    @Query(value = "update t_bank_account set name = ?2 where id=?1",nativeQuery = true)
    @Modifying
    @Transactional
    public  void updateOne(Long id,String name);

    //源生sql 删除
    @Query(value = "delete from t_bank_account where id = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    public void deteteBankAccountById(Long id);
}

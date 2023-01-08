package com.demo.merchant.domain.service;


import com.demo.merchant.domain.config.CacheComponent;
import com.demo.merchant.domain.entity.User;
import com.demo.merchant.domain.repository.UserRepository;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.Constant;
import com.demo.merchant.object.UserQo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(User user){
        //删除缓存
        if(CommonUtils.isNotNull(user.getId())){
            String key = user.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_USER_ID, key);//删除原有缓存
        }
        userRepository.save(user);
        //保存缓存
        if(CommonUtils.isNotNull(user.getId())){
            String key = user.getId().toString();
            cacheComponent.put(Constant.MERCHANT_CENTER_USER_ID, key, user, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.MERCHANT_CENTER_USER_ID, id.toString());
        userRepository.delete(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public  User findOne(Long id){
        User user = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.MERCHANT_CENTER_USER_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            user = userRepository.findById(id);
            if (user != null)
                cacheComponent.put(Constant.MERCHANT_CENTER_USER_ID, id.toString(), user, 12);
        } else {
            user = (User) object;
        }
        return  user;
    }

    public List<User> findByRoleId(Long roleId){
        return userRepository.findByRoleId(roleId);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }


    public Page<User> findAll(UserQo userQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(userQo.getPage(), userQo.getSize(), sort);

        return userRepository.findAll(new Specification<User>(){
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(userQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + userQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(userQo.getMerchant())){
                    predicatesList.add(criteriaBuilder.equal(root.get("merchant"), userQo.getMerchant().getId()));
                }
                if(CommonUtils.isNotNull(userQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), userQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }

}

package com.demo.merchant.domain.service;

import com.demo.merchant.domain.config.CacheComponent;
import com.demo.merchant.domain.entity.Role;
import com.demo.merchant.domain.repository.RoleRepository;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.Constant;
import com.demo.merchant.object.RoleQo;
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
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Role role){
        //删除缓存
        if(CommonUtils.isNotNull(role.getId())){
            String key = role.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_ROLE_ID, key);//删除原有缓存
        }
        roleRepository.save(role);
        //保存缓存
        if(CommonUtils.isNotNull(role.getId())){
            String key = role.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_ROLE_ID, key);//删除原有缓存
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.MERCHANT_CENTER_ROLE_ID, id.toString());

        roleRepository.delete(id);
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findOne(Long id){
        Role role = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.MERCHANT_CENTER_ROLE_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            role = roleRepository.findOne(id);
            if (role != null)
                cacheComponent.put(Constant.MERCHANT_CENTER_ROLE_ID, id.toString(), role, 12);
        } else {
            role = (Role) object;
        }
        return  role;
    }

    public List<Role> findByResourceId(Long resourceId){
        return roleRepository.findByResourceId(resourceId);
    }

    public Page<Role> findAll(RoleQo roleQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(roleQo.getPage(), roleQo.getSize(), sort);

        return roleRepository.findAll(new Specification<Role>(){
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(roleQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + roleQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(roleQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), roleQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

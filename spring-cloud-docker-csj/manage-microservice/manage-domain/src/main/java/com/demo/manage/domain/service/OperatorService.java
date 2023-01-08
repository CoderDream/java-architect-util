package com.demo.manage.domain.service;

import com.demo.manage.domain.config.CacheComponent;
import com.demo.manage.domain.entity.Operators;
import com.demo.manage.domain.repository.OperatorRepository;
import com.demo.manage.domain.util.CommonUtils;
import com.demo.manage.domain.util.Constant;
import com.demo.manage.object.OperatorsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OperatorService {
    @Autowired
    private OperatorRepository operatorRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Operators operators){
        //删除缓存
        if(!StringUtils.isEmpty(operators.getId())){
            String key = operators.getId().toString();
            cacheComponent.remove(Constant.BOSS_BACKEND_OPERATOR_ID, key);//删除原有缓存
        }
        operatorRepository.save(operators);
        //保存缓存
        if(!StringUtils.isEmpty(operators.getId())){
            String key = operators.getId().toString();
            cacheComponent.put(Constant.BOSS_BACKEND_OPERATOR_ID, key, operators, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.BOSS_BACKEND_OPERATOR_ID, id.toString());
        operatorRepository.delete(id);
    }

    public List<Operators> findAll(){
        return operatorRepository.findAll();
    }

    public Operators findOne(Long id){
        Operators operators = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.BOSS_BACKEND_OPERATOR_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            operators = operatorRepository.findById(id);
            if (operators != null)
                cacheComponent.put(Constant.BOSS_BACKEND_OPERATOR_ID, id.toString(), operators, 12);
        } else {
            operators = (Operators) object;
        }
        return operators;
    }

    public Operators findByName(String name){
        return operatorRepository.findByName(name);
    }


    public List<Operators> findByPartId(Long partId){
        return operatorRepository.findByPartId(partId);
    }


    public Page<Operators> findAll(OperatorsVo operatorsVo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(operatorsVo.getPage(), operatorsVo.getSize(), sort);

        return operatorRepository.findAll(new Specification<Operators>(){
            @Override
            public Predicate toPredicate(Root<Operators> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(operatorsVo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + operatorsVo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(operatorsVo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), operatorsVo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }

}

package com.demo.merchant.domain.service;

import com.demo.merchant.domain.config.CacheComponent;
import com.demo.merchant.domain.entity.Kind;
import com.demo.merchant.domain.repository.KindRepository;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.Constant;
import com.demo.merchant.object.KindQo;
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
public class KindService {
    @Autowired
    private KindRepository kindRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Kind kind){
        //删除缓存
        if(CommonUtils.isNotNull(kind.getId())){
            String key = kind.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_KIND_ID, key);//删除原有缓存
        }
        kindRepository.save(kind);
        //保存缓存
        if(CommonUtils.isNotNull(kind.getId())){
            String key = kind.getId().toString();
            cacheComponent.put(Constant.MERCHANT_CENTER_KIND_ID, key, kind, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.MERCHANT_CENTER_KIND_ID, id.toString());

        kindRepository.delete(id);
    }

    public List<Kind> findAll(){
        return kindRepository.findAll();
    }

    public  Kind findOne(Long id){
        Kind kind = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.MERCHANT_CENTER_KIND_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            kind = kindRepository.findOne(id);
            if (kind != null)
                cacheComponent.put(Constant.MERCHANT_CENTER_KIND_ID, id.toString(), kind, 12);
        } else {
            kind = (Kind) object;
        }
        return  kind;
    }
    

    public Page<Kind> findAll(KindQo kindQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(kindQo.getPage(), kindQo.getSize(), sort);

        return kindRepository.findAll(new Specification<Kind>(){
            @Override
            public Predicate toPredicate(Root<Kind> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(kindQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + kindQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(kindQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), kindQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

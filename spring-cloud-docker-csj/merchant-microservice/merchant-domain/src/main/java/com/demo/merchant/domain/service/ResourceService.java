package com.demo.merchant.domain.service;

import com.demo.merchant.domain.config.CacheComponent;
import com.demo.merchant.domain.entity.Resource;
import com.demo.merchant.domain.repository.ResourceRepository;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.Constant;
import com.demo.merchant.object.ResourceQo;
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
public class ResourceService {
    @Autowired
    private ResourceRepository resourceRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Resource resource){
        //删除缓存
        if(CommonUtils.isNotNull(resource.getId())){
            String key = resource.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_RESOURCE_ID, key);//删除原有缓存
        }
        resourceRepository.save(resource);
        //保存缓存
        if(CommonUtils.isNotNull(resource.getId())){
            String key = resource.getId().toString();
            cacheComponent.put(Constant.MERCHANT_CENTER_RESOURCE_ID, key, resource, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.MERCHANT_CENTER_RESOURCE_ID, id.toString());

        resourceRepository.delete(id);
    }

    public List<Resource> findAll(){
        return resourceRepository.findAll();
    }

    public  Resource findOne(Long id){
        Resource resource = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.MERCHANT_CENTER_RESOURCE_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            resource = resourceRepository.findOne(id);
            if (resource != null)
                cacheComponent.put(Constant.MERCHANT_CENTER_RESOURCE_ID, id.toString(), resource, 12);
        } else {
            resource = (Resource) object;
        }
        return  resource;
    }
    

    public Page<Resource> findAll(ResourceQo resourceQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(resourceQo.getPage(), resourceQo.getSize(), sort);

        return resourceRepository.findAll(new Specification<Resource>(){
            @Override
            public Predicate toPredicate(Root<Resource> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(resourceQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + resourceQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(resourceQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), resourceQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

package com.demo.merchant.domain.service;

import com.demo.merchant.domain.config.CacheComponent;
import com.demo.merchant.domain.entity.Model;
import com.demo.merchant.domain.repository.ModelRepository;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.Constant;
import com.demo.merchant.object.ModelQo;
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
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Model model){
        //删除缓存
        if(!StringUtils.isEmpty(model.getId())){
            String key = model.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_MODEL_ID, key);//删除原有缓存
        }
        modelRepository.save(model);
        //保存缓存
        if(!StringUtils.isEmpty(model.getId())){
            String key = model.getId().toString();
            cacheComponent.put(Constant.MERCHANT_CENTER_MODEL_ID, key, model, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.MERCHANT_CENTER_MODEL_ID, id.toString());

        modelRepository.delete(id);
    }

    public List<Model> findAll(){
        return modelRepository.findAll();
    }

    public  Model findOne(Long id){
        Model model = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.MERCHANT_CENTER_MODEL_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            model = modelRepository.findOne(id);
            if (model != null)
                cacheComponent.put(Constant.MERCHANT_CENTER_MODEL_ID, id.toString(), model, 12);
        } else {
            model = (Model) object;
        }
        return  model;
    }
    

    public Page<Model> findAll(ModelQo modelQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(modelQo.getPage(), modelQo.getSize(), sort);

        return modelRepository.findAll(new Specification<Model>(){
            @Override
            public Predicate toPredicate(Root<Model> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(modelQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + modelQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(modelQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), modelQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

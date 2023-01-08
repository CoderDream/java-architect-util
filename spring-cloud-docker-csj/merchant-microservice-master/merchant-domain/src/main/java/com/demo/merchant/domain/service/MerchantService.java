package com.demo.merchant.domain.service;

import com.demo.merchant.domain.config.CacheComponent;
import com.demo.merchant.domain.entity.Merchant;
import com.demo.merchant.domain.repository.MerchantRepository;
import com.demo.merchant.domain.util.CommonUtils;
import com.demo.merchant.domain.util.Constant;
import com.demo.merchant.object.MerchantQo;
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
public class MerchantService {
    @Autowired
    private MerchantRepository merchantRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Merchant merchant){
        //删除缓存
        if(!StringUtils.isEmpty(merchant.getId())){
            String key = merchant.getId().toString();
            cacheComponent.remove(Constant.MERCHANT_CENTER_MERCHANT_ID, key);//删除原有缓存
        }
        merchantRepository.save(merchant);
        //保存缓存
        if(!StringUtils.isEmpty(merchant.getId())){
            String key = merchant.getId().toString();
            cacheComponent.put(Constant.MERCHANT_CENTER_MERCHANT_ID, key, merchant, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.MERCHANT_CENTER_MERCHANT_ID, id.toString());

        merchantRepository.delete(id);
    }

    public List<Merchant> findAll(){
        return merchantRepository.findAll();
    }

    public  Merchant findOne(Long id){
        Merchant merchant = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.MERCHANT_CENTER_MERCHANT_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            merchant = merchantRepository.findOne(id);
            if (merchant != null)
                cacheComponent.put(Constant.MERCHANT_CENTER_MERCHANT_ID, id.toString(), merchant, 12);
        } else {
            merchant = (Merchant) object;
        }
        return  merchant;
    }
    

    public Page<Merchant> findAll(MerchantQo merchantQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(merchantQo.getPage(), merchantQo.getSize(), sort);

        return merchantRepository.findAll(new Specification<Merchant>(){
            @Override
            public Predicate toPredicate(Root<Merchant> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(merchantQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + merchantQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(merchantQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), merchantQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

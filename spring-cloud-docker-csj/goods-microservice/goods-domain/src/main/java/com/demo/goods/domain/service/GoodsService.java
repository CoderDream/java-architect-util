package com.demo.goods.domain.service;


import com.demo.goods.domain.entity.Goods;
import com.demo.goods.domain.repository.GoodsRepository;
import com.demo.goods.domain.util.CommonUtils;
import com.demo.goods.object.GoodsQo;
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
public class GoodsService {
    @Autowired
    private GoodsRepository goodsRepository;

    public Goods findOne(Long id){
        return goodsRepository.findOne(id);
    }

    public void save(Goods goods){
        goodsRepository.save(goods);
    }

    public void delete(Long id){
        goodsRepository.delete(id);
    }


    public Page<Goods> findAll(GoodsQo GoodsQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = new PageRequest(GoodsQo.getPage(), GoodsQo.getSize(), sort);

        return goodsRepository.findAll(new Specification<Goods>(){
            @Override
            public Predicate toPredicate(Root<Goods> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(GoodsQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + GoodsQo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(GoodsQo.getMerchantid())){
                    predicatesList.add(criteriaBuilder.equal(root.get("merchantid"), GoodsQo.getMerchantid()));
                }
                if(CommonUtils.isNotNull(GoodsQo.getSortsid())){
                    predicatesList.add(criteriaBuilder.equal(root.get("sortsid"), GoodsQo.getSortsid()));
                }
                if(CommonUtils.isNotNull(GoodsQo.getSubsid())){
                    predicatesList.add(criteriaBuilder.equal(root.get("subsid"), GoodsQo.getSubsid()));
                }
                if(CommonUtils.isNotNull(GoodsQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), GoodsQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
}

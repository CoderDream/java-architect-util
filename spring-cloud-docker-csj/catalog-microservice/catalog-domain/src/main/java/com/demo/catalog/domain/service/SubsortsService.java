package com.demo.catalog.domain.service;


import com.demo.catalog.domain.entity.Subsorts;
import com.demo.catalog.domain.repository.SubsortsRepository;
import com.demo.catalog.domain.util.CommonUtils;
import com.demo.catalog.object.SubsortsQo;
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

/**
 * Created by apple on 2017/9/26.
 */
@Service
@Transactional
public class SubsortsService {
    @Autowired
    private SubsortsRepository subsortsRepository;

    public Subsorts findOne(Long id){
        return subsortsRepository.getOne(id);
    }

    public Subsorts findByName(String name){
        return subsortsRepository.findByName(name);
    }

    public void save(Subsorts subsorts){
        subsortsRepository.save(subsorts);
    }

    public void delete(Long id){
        subsortsRepository.delete(id);
    }


    public Page<Subsorts> findAll(SubsortsQo subsortsQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = new PageRequest(subsortsQo.getPage(), subsortsQo.getSize(), sort);

        return subsortsRepository.findAll(new Specification<Subsorts>(){
            @Override
            public Predicate toPredicate(Root<Subsorts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(subsortsQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + subsortsQo.getName()+ "%"));
                }
                if(CommonUtils.isNotNull(subsortsQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), subsortsQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }

    public Iterable<Subsorts> findAll(){
        return subsortsRepository.findAll();
    }
}

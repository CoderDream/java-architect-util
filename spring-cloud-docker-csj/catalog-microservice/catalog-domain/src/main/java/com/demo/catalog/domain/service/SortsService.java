package com.demo.catalog.domain.service;

import com.demo.catalog.domain.entity.Sorts;
import com.demo.catalog.domain.repository.SortsRepository;
import com.demo.catalog.domain.util.CommonUtils;
import com.demo.catalog.object.SortsQo;
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
public class SortsService {
    @Autowired
    private SortsRepository sortsRepository;

    public Sorts findOne(Long id){
        return sortsRepository.getOne(id);
    }

    public Sorts findByName(String name){
        return sortsRepository.findByName(name);
    }

    public void save(Sorts sorts){
        sortsRepository.save(sorts);
    }

    public void delete(Long id){
        sortsRepository.delete(id);
    }


    public Page<Sorts> findAll(SortsQo sortsQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = new PageRequest(sortsQo.getPage(), sortsQo.getSize(), sort);

        return sortsRepository.findAll(new Specification<Sorts>(){
            @Override
            public Predicate toPredicate(Root<Sorts> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(sortsQo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + sortsQo.getName()+ "%"));
                }
                if(CommonUtils.isNotNull(sortsQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), sortsQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }

    public Iterable<Sorts> findAll(){
        return sortsRepository.findAll();
    }

    public Sorts findBySubsortsId(Long subid){
        return sortsRepository.findBySubsortsId(subid);
    }
}

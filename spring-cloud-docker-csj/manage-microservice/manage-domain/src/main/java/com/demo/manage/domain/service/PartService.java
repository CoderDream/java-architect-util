package com.demo.manage.domain.service;

import com.demo.manage.domain.config.CacheComponent;
import com.demo.manage.domain.entity.Part;
import com.demo.manage.domain.repository.PartRepository;
import com.demo.manage.domain.util.CommonUtils;
import com.demo.manage.domain.util.Constant;
import com.demo.manage.object.PartVo;
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
public class PartService {
    @Autowired
    private PartRepository partRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Part part){
        //删除缓存
        if(!StringUtils.isEmpty(part.getId())){
            String key = part.getId().toString();
            cacheComponent.remove(Constant.BOSS_BACKEND_PART_ID, key);//删除原有缓存
        }
        partRepository.save(part);
        //保存缓存
        if(!StringUtils.isEmpty(part.getId())){
            String key = part.getId().toString();
            cacheComponent.put(Constant.BOSS_BACKEND_PART_ID, key, part, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.BOSS_BACKEND_PART_ID, id.toString());
        partRepository.delete(id);
    }

    public List<Part> findAll(){
        return partRepository.findAll();
    }

    public Part findOne(Long id){
        Part part = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.BOSS_BACKEND_PART_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            part = partRepository.findOne(id);
            if (part != null)
                cacheComponent.put(Constant.BOSS_BACKEND_PART_ID, id.toString(), part, 12);
        } else {
            part = (Part) object;
        }
        return  part;
    }


    public Page<Part> findAll(PartVo partVo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(partVo.getPage(), partVo.getSize(), sort);

        return partRepository.findAll(new Specification<Part>(){
            @Override
            public Predicate toPredicate(Root<Part> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(partVo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + partVo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(partVo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), partVo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

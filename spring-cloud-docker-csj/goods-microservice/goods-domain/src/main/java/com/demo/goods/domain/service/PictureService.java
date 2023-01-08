package com.demo.goods.domain.service;


import com.demo.goods.domain.entity.Picture;
import com.demo.goods.domain.repository.PictureRepository;
import com.demo.goods.domain.util.CommonUtils;
import com.demo.goods.object.PictureQo;
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
public class PictureService {
    @Autowired
    private PictureRepository pictureRepository;

    public Picture findOne(Long id){
        return pictureRepository.findOne(id);
    }

    public void save(Picture picture){
        pictureRepository.save(picture);
    }

    public void delete(Long id){
        pictureRepository.delete(id);
    }

    public void deleteByFileName(String fileName){
        Picture picture = pictureRepository.findByFileName(fileName);
        if(picture != null) pictureRepository.delete(picture);
    }


    public Page<Picture> findAll(PictureQo pictureQo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable = new PageRequest(pictureQo.getPage(), pictureQo.getSize(), sort);

        return pictureRepository.findAll(new Specification<Picture>(){
            @Override
            public Predicate toPredicate(Root<Picture> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(pictureQo.getMerchantid())){
                    predicatesList.add(criteriaBuilder.equal(root.get("merchantid"), pictureQo.getMerchantid()));
                }
                if(CommonUtils.isNotNull(pictureQo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), pictureQo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
}

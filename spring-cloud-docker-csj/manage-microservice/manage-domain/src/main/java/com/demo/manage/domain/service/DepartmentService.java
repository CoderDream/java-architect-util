package com.demo.manage.domain.service;

import com.demo.manage.domain.config.CacheComponent;
import com.demo.manage.domain.entity.Department;
import com.demo.manage.domain.repository.DepartmentRepository;
import com.demo.manage.domain.util.CommonUtils;
import com.demo.manage.domain.util.Constant;
import com.demo.manage.object.DepartmentVo;
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
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CacheComponent cacheComponent;

    public void save(Department department){
        //删除缓存
        if(!StringUtils.isEmpty(department.getId())){
            String key = department.getId().toString();
            cacheComponent.remove(Constant.BOSS_BACKEND_DEPARTMENT_ID, key);//删除原有缓存
        }
        departmentRepository.save(department);
        //保存缓存
        if(!StringUtils.isEmpty(department.getId())){
            String key = department.getId().toString();
            cacheComponent.put(Constant.BOSS_BACKEND_DEPARTMENT_ID, key, department, 12);//增加缓存，保存12秒
        }
    }

    public void delete(Long id){
        //删除缓存
        cacheComponent.remove(Constant.BOSS_BACKEND_DEPARTMENT_ID, id.toString());
        departmentRepository.delete(id);
    }

    public List<Department> findAll(){
        return departmentRepository.findAll();
    }

    public Department findOne(Long id){
        Department department = null;
        //使用缓存
        Object object = cacheComponent.get(Constant.BOSS_BACKEND_DEPARTMENT_ID, id.toString());
        if (CommonUtils.isNull(object)) {
            department = departmentRepository.findOne(id);
            if (department != null)
                cacheComponent.put(Constant.BOSS_BACKEND_DEPARTMENT_ID, id.toString(), department, 12);
        } else {
            department = (Department) object;
        }
        return  department;
    }

    public Page<Department> findAll(DepartmentVo departmentVo){
        Sort sort = new Sort(Sort.Direction.DESC, "created");
        Pageable pageable  = new PageRequest(departmentVo.getPage(), departmentVo.getSize(), sort);

        return departmentRepository.findAll(new Specification<Department>(){
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicatesList = new ArrayList<Predicate>();

                if(CommonUtils.isNotNull(departmentVo.getName())){
                    predicatesList.add(criteriaBuilder.like(root.get("name"), "%" + departmentVo.getName() + "%"));
                }
                if(CommonUtils.isNotNull(departmentVo.getCreated())){
                    predicatesList.add(criteriaBuilder.greaterThan(root.get("created"), departmentVo.getCreated()));
                }

                query.where(predicatesList.toArray(new Predicate[predicatesList.size()]));

                return query.getRestriction();
            }
        }, pageable);
    }
    
}

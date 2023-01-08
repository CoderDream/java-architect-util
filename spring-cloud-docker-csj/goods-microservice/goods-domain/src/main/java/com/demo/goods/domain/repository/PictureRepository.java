package com.demo.goods.domain.repository;


import com.demo.goods.domain.entity.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long>, JpaSpecificationExecutor<Picture> {
    Picture findByFileName(String fileName);
}

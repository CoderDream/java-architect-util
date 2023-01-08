package com.demo.order.domain.repository;



import com.demo.order.domain.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long> {

}

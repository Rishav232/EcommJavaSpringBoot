package com.practice.ecommproject.Repository;

import com.practice.ecommproject.Models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {

    @Query("SELECT c.name from Category c join Product p on p.category.Id=c.Id where p.Id=:pId")
    String findCategoryFromProductId(Long pId);

    Page<Product> findProductByName(String query , Pageable pageable);
}

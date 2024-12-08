package com.practice.ecommproject.Service;

import com.practice.ecommproject.DTO.SortingOrder;
import com.practice.ecommproject.DTO.SortingOrderType;
import com.practice.ecommproject.Models.Product;
import com.practice.ecommproject.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchServiceImpl implements ISearchService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Page<Product> searchProduct(String query, int pageNumber, int pageSize, List<SortingOrder> sortingOrders) {

        Sort sort = null;

        if(!sortingOrders.isEmpty())
        {
            if(sortingOrders.get(0).getSortingOrderType().equals(SortingOrderType.ASC))
            {
                sort = Sort.by(sortingOrders.get(0).getSortByVariable());
            }
            else
            {
                sort = Sort.by(sortingOrders.get(0).getSortByVariable()).descending();
            }

            for(int i=1;i< sortingOrders.size();i++)
            {
                if(sortingOrders.get(i).getSortingOrderType().equals(SortingOrderType.ASC))
                {
                    sort.and(Sort.by(sortingOrders.get(i).getSortByVariable()));
                }
                else
                {
                    sort.and(Sort.by(sortingOrders.get(i).getSortByVariable()).descending());
                }
            }

        }
        return productRepository.findProductByName(query, PageRequest.of(pageNumber,pageSize,sort));
    }
}

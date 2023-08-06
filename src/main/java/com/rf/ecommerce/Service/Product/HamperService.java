package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Entity.Product.Hamper;
import com.rf.ecommerce.Repository.Product.HamperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HamperService {
    private HamperRepository hamperRepository;
    private List<Hamper> hampers=new ArrayList<>();
@Autowired
    public HamperService(HamperRepository hamperRepository) {
        this.hamperRepository = hamperRepository;
    }
    public List<Hamper> getAllHampers(){
    return  hampers;
    }
    public boolean existsById(Long id){
    return hamperRepository.existsById(id);
    }
    public Hamper findById(Long id){
    return hamperRepository.findById(id).orElseThrow();
    }
    public void save(Hamper hamper){
    hamperRepository.save(hamper);
    }
    public void delete(Long id){
    hamperRepository.deleteById(id);
    }
}

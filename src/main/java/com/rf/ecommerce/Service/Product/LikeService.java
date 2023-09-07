package com.rf.ecommerce.Service.Product;

import com.rf.ecommerce.Dto.DtoConvert;
import com.rf.ecommerce.Dto.Product.LikeDto;
import com.rf.ecommerce.Entity.Product.Like;
import com.rf.ecommerce.Entity.Product.Product;
import com.rf.ecommerce.Entity.User.User;
import com.rf.ecommerce.Repository.Product.LikeRepository;
import com.rf.ecommerce.Service.User.UserService;
import com.rf.ecommerce.error.ApiError;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LikeService {
    @Autowired
    LikeRepository likeRepository;
    @Autowired
    ProductService productService;
    @Autowired
    UserService userService;
    private final DtoConvert dtoConvert;
    public void save(Like like){likeRepository.save(like);}
    public void delete(Long id){likeRepository.deleteById(id);}
    public Like findByProduct(Product product){return likeRepository.findByProduct(product);}

    public ResponseEntity<?> plusLike(Long productId,String email) {
        if(!productService.existsById(productId)){
     return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(sendError("Ürün Bulunamadi"));
        }
        Product product=productService.findById(productId);
        Like like= findByProduct(product);
        User user=userService.findByEmail(email);
        like.setUser(user);
        like.setCount(like.getCount()+1);
        save(like);
        return ResponseEntity.ok().body(product.getId()+"idli ürünün like sayısı: "+ like.getCount());
    }
    public ResponseEntity<?> minusLike(Long productId) {
        if(!productService.existsById(productId)){
            return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(sendError("Ürün Bulunamadı"));
        }
        Product product=productService.findById(productId);
        Like like= findByProduct(product);
        like.setCount(like.getCount()-1);
        like.setUser(null);
        save(like);
        return ResponseEntity.ok().body(product.getId()+"idli ürünün like sayısı: "+ like.getCount());
    }

    public List<Like> getAllLikes(){
        return likeRepository.findAll();
    }

    public List<LikeDto> getLikeList(String email) {
        List<Like >likeList=new ArrayList<>();
        for(Like likeDto : getAllLikes()){
            if(likeDto.getUser()!=null){
                if(likeDto.getUser().getEmail().equals(email)){
                    likeList.add(likeDto);
                }
            }

        }
        return likeList.stream().map(x->dtoConvert.likeConvert(x)).collect(Collectors.toList());
    }
    private ApiError sendError(String message){
        return new ApiError(404,message,"api/like");
    }
}

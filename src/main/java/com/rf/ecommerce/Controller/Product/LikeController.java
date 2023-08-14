package com.rf.ecommerce.Controller.Product;

import com.rf.ecommerce.Dto.Product.LikeDto;
import com.rf.ecommerce.Service.Product.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LikeController {
    private final LikeService likeService;

    // buraki işlem frontend tarafında şu şekil yapılacak bir kere tıklanmışsa plusLike iki defa tıklanmışsa minusLike çalışacak

    // like atma
    @PostMapping("/plusLike/{productId}/{email}")
    private ResponseEntity<?> plusLike(@PathVariable Long productId, @PathVariable String email){
        return likeService.plusLike(productId, email);
    }
    // like geri alma
    @PostMapping("/minusLike/{productId}/{email}")
    private ResponseEntity<?> minusLike(@PathVariable Long productId, @PathVariable String email){
        return likeService.minusLike(productId, email);
    }
    // bir kullanıcının like attığı ürünleri getiren Liste
    @GetMapping("/getLikeList/{email}")
    public List<LikeDto> getLikeList(@PathVariable String email){
        return likeService.getLikeList(email);
    }




}

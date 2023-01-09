package com.example.picallti.service;

import com.example.picallti.exception.FavorisNotFoundException;
import com.example.picallti.model.Favoris;
import com.example.picallti.model.User;
import com.example.picallti.repository.FavorisRepository;
import com.example.picallti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FavorisService  {
    private final FavorisRepository favorisRepository;
    private final UserRepository userRepository;

    @Autowired
    public FavorisService(FavorisRepository favorisRepository , UserRepository userRepository) {
        this.favorisRepository = favorisRepository;
        this.userRepository = userRepository;
    }
    public Favoris addFavoris(Favoris favoris){
        return favorisRepository.save(favoris);
    }
    public List<Favoris> findAllFavoris(){
        return favorisRepository.findAll();
    }
    public Favoris findFavorisById(int id){
        return favorisRepository.findById(id).orElseThrow(() -> new FavorisNotFoundException("Favoris "+id+" not found !"));
    }
    public Optional<List<Favoris>> findByUser(int id){
        User user = userRepository.findById(id).get();
        if(user != null){
            return favorisRepository.findByUser(user);
        }
        else{
            return null;
        }
    }
    public Favoris updateFavoris(Favoris favoris){
        Favoris favoris1 = favorisRepository.findById(favoris.getId()).get();
        if(favoris1 != null){
            favoris1.setUser(favoris1.getUser());
            favoris1.setLocalDateTime(favoris1.getlocaLDate());
            favoris1.setOffre(favoris1.getOffre());
            return favorisRepository.save(favoris1);
        }
        else{
            return null;
        }
    }
    public void deleteFavoris(Favoris favoris){
        List<Favoris> favByUser = favorisRepository.findByUser(favoris.getUser()).get();
        System.out.println(favByUser);
        favByUser.forEach(favoris1 -> {
            if (favoris1.getOffre().getId() == favoris.getOffre().getId()){
                favorisRepository.deleteById(favoris1.getId());
            }
        });

    }



    public boolean chechIfExist(Favoris favoris){
        if(favorisRepository.existsByUser(favoris.getUser()) && favorisRepository.existsByOffre(favoris.getOffre())){
            return  true;
        }
        return false;
    }

}

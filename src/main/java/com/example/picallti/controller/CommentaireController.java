package com.example.picallti.controller;

import com.example.picallti.model.Commentaire;
import com.example.picallti.repository.OffreRepository;
import com.example.picallti.repository.UserRepository;
import com.example.picallti.service.CommentaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@CrossOrigin
@RequestMapping("/commentaires/")
public class CommentaireController {
    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    CommentaireService commentaireService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public void addCommentaire(@RequestBody Commentaire commentaire){
        commentaireService.addCommentaire(commentaire);
    }

    @RequestMapping("getAll")
    public Collection<Commentaire> getAllCommentaires(){
        return commentaireService.getAllCommentaires();
    }

    @RequestMapping("getAllByUser")
    public Collection<Commentaire> getAllCommentairesByUser(@RequestParam int id){
        return commentaireService.getCommentairesByUser(id);
    }
    @RequestMapping("getAllByOffre")
    public Collection<Commentaire> getAllCommentairesByOffre(@RequestParam int id){
        return commentaireService.getCommentairesByOffre(id);
    }

    @RequestMapping("getById")
    public Commentaire getCommentaireById(@RequestParam int id){
        return commentaireService.getCommentaireById(id);
    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public Commentaire updateCommentaire(@RequestBody Commentaire commentaire){
       commentaireService.updateCommentaire(commentaire);
        return commentaire;
    }

    @RequestMapping("remove")
    public void removeCommentaireById(@RequestParam int id)
    {
        commentaireService.removeCommentaireById(id);
    }
}

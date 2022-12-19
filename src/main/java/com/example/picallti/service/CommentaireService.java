package com.example.picallti.service;

import com.example.picallti.model.Commentaire;
import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.repository.CommentaireRepository;
import com.example.picallti.repository.OffreRepository;
import com.example.picallti.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class CommentaireService {
    @Autowired
    private CommentaireRepository commentaireRepository;
    @Autowired
    private OffreRepository offreRepository;
    @Autowired
    private UserRepository userRepository;

    // New Comment :
    public void addCommentaire(Commentaire commentaire){
        commentaireRepository.save(commentaire);
    }

    // Update Comment :
    public void updateCommentaire(Commentaire commentaire) {
        Commentaire comment = commentaireRepository.findById(commentaire.getId()).get();
        if (comment != null) {
            comment.setCommentaire(commentaire.getCommentaire());
            comment.setUser(commentaire.getUser());
            comment.setOffre(commentaire.getOffre());
            comment.setTime(commentaire.getTime());
            comment.setLocalDateTime(commentaire.getLocalDateTime());
            commentaireRepository.save(comment);

        }

    }

    // Fetch Comment :
    public Collection<Commentaire> getAllCommentaires() {

        return commentaireRepository.findAll();
    }

    public Commentaire getCommentaireById(int id) {
        return commentaireRepository.findById(id).get();
    }

    public Collection<Commentaire> getCommentairesByUser(int id) {
        User user = userRepository.findById(id).get();
        if (user != null) {
            return commentaireRepository.findCommentairesByUser(user);
        }
        return null;
    }
    public Collection<Commentaire> getCommentairesByOffre(int id) {
        Offre offre = offreRepository.findById(id).get();
        if (offre != null) {
            return commentaireRepository.findCommentairesByOffre(offre);
        }
        return null;
    }

    // Delete Comment :
    public void removeCommentaireById(int id){commentaireRepository.deleteById(id);
    }

}

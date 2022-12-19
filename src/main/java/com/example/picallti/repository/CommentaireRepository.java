package com.example.picallti.repository;

import com.example.picallti.model.Commentaire;
import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
@Repository
public interface CommentaireRepository extends JpaRepository<Commentaire, Integer> {
    public Collection<Commentaire> findCommentairesByUser(User user);
    public Collection<Commentaire> findCommentairesByOffre(Offre offre);

}

package com.example.picallti;

import com.example.picallti.model.Commentaire;
import com.example.picallti.model.Offre;
import com.example.picallti.model.User;
import com.example.picallti.repository.CommentaireRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PicalltiApplication {
	public static void main(String[] args) {

		SpringApplication. run(PicalltiApplication.class, args);
	}

}

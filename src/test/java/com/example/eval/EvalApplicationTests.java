package com.example.eval;

import com.example.eval.model.Convention;
import com.example.eval.model.Entreprise;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class EvalApplicationTests {


	@Autowired
	private WebApplicationContext context;
	private MockMvc mvc;


	@Autowired
	private ObjectMapper mapper;

	@BeforeEach
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

	@Test
	@WithMockUser(username = "entreprise1@gmail.com", roles={"ENTREPRISE"})
	void supprimerUneConventionEnTantQueEntreprise_reponse403forbidden() throws Exception{

		mvc.perform(delete("/convention/1"))
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "entreprise1@gmail.com", roles = {"ENTREPRISE"})
	void ajouterUneConventionEnTantQueEntreprise_reponse403forbidden() throws Exception {

		Convention convention = new Convention();
		convention.setNom("Convention 6");
		convention.setSalarie_maximum(10);
		convention.setSubvention(1000.0F);

		String json = mapper.writeValueAsString(convention);

		mvc.perform(post("/convention")
						.contentType(MediaType.APPLICATION_JSON) // Spécifie le type de contenu
						.content(json)) // Ajoute le JSON à la requête
				.andExpect(status().isForbidden()); // Vérifie que le statut est 403 Forbidden
	}

	@Test
	@WithMockUser(username = "entreprise1@gmail.com", roles = {"ENTREPRISE"})
	void ajouterUneEntrepriseEnTantQueEntreprise_reponse403forbidden() throws Exception {

		Entreprise entreprise = new Entreprise();
		entreprise.setNom("Convention 6");

		String json = mapper.writeValueAsString(entreprise);

		mvc.perform(post("/entreprise")
						.contentType(MediaType.APPLICATION_JSON) // Spécifie le type de contenu
						.content(json)) // Ajoute le JSON à la requête
				.andExpect(status().isForbidden()); // Vérifie que le statut est 403 Forbidden
	}



}

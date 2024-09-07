package org.example.service;


import org.example.entity.Recette;
import org.example.entity.Ingredient;
import org.example.repository.RecetteRepository;
import org.example.repository.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class RecetteService {
    @Autowired
    private RecetteRepository recetteRepository;

    @Autowired
    private IngredientRepository ingredientRepository;

    // Créer une recette
    @Transactional
    public Recette creerRecette(Recette recette) {
        return recetteRepository.save(recette);
    }

    // Ajouter un ingrédient à une recette
    @Transactional
    public Recette ajouterIngredientALaRecette(Long recetteId, Long ingredientId) {
        Optional<Recette> recetteOpt = recetteRepository.findById(recetteId);
        Optional<Ingredient> ingredientOpt = ingredientRepository.findById(ingredientId);

        if (recetteOpt.isPresent() && ingredientOpt.isPresent()) {
            Recette recette = recetteOpt.get();
            Ingredient ingredient = ingredientOpt.get();

            recette.getIngredients().add(ingredient);
            return recetteRepository.save(recette);
        } else {
            throw new RuntimeException("Recette ou Ingrédient non trouvé");
        }
    }

    // Récupérer toutes les recettes
    @Transactional(readOnly = true)
    public List<Recette> obtenirToutesLesRecettes() {
        return recetteRepository.findAll();
    }

    // Récupérer une recette par ID
    @Transactional(readOnly = true)
    public Optional<Recette> obtenirRecetteParId(Long id) {
        return recetteRepository.findById(id);
    }

    // Supprimer une recette
    @Transactional
    public void supprimerRecette(Long id) {
        recetteRepository.deleteById(id);
    }

    // Créer un ingrédient
    @Transactional
    public Ingredient creerIngredient(Ingredient ingredient) {
        return ingredientRepository.save(ingredient);
    }

    // Récupérer tous les ingrédients
    @Transactional(readOnly = true)
    public List<Ingredient> obtenirTousLesIngredients() {
        return ingredientRepository.findAll();
    }

    // Supprimer un ingrédient
    @Transactional
    public void supprimerIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }
}

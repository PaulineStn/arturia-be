package org.example.controller;

import org.example.entity.Recette;
import org.example.entity.Ingredient;
import org.example.service.RecetteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/recettes")
public class RecetteController {
    @Autowired
    private RecetteService recetteService;

    // Créer une nouvelle recette
    @PostMapping
    public ResponseEntity<Recette> creerRecette(@RequestBody Recette recette) {
        Recette nouvelleRecette = recetteService.creerRecette(recette);
        return ResponseEntity.ok(nouvelleRecette);
    }

    // Ajouter un ingrédient à une recette
    @PostMapping("/{recetteId}/ingredients/{ingredientId}")
    public ResponseEntity<Recette> ajouterIngredientALaRecette(@PathVariable Long recetteId, @PathVariable Long ingredientId) {
        Recette recette = recetteService.ajouterIngredientALaRecette(recetteId, ingredientId);
        return ResponseEntity.ok(recette);
    }

    // Obtenir toutes les recettes
    @GetMapping
    public ResponseEntity<List<Recette>> obtenirToutesLesRecettes() {
        List<Recette> recettes = recetteService.obtenirToutesLesRecettes();
        return ResponseEntity.ok(recettes);
    }

    // Obtenir une recette par ID
    @GetMapping("/{id}")
    public ResponseEntity<Recette> obtenirRecetteParId(@PathVariable Long id) {
        Optional<Recette> recetteOpt = recetteService.obtenirRecetteParId(id);
        return recetteOpt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Supprimer une recette
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> supprimerRecette(@PathVariable Long id) {
        recetteService.supprimerRecette(id);
        return ResponseEntity.noContent().build();
    }

    // Créer un ingrédient
    @PostMapping("/ingredients")
    public ResponseEntity<Ingredient> creerIngredient(@RequestBody Ingredient ingredient) {
        Ingredient nouvelIngredient = recetteService.creerIngredient(ingredient);
        return ResponseEntity.ok(nouvelIngredient);
    }

    // Obtenir tous les ingrédients
    @GetMapping("/ingredients")
    public ResponseEntity<List<Ingredient>> obtenirTousLesIngredients() {
        List<Ingredient> ingredients = recetteService.obtenirTousLesIngredients();
        return ResponseEntity.ok(ingredients);
    }

    // Supprimer un ingrédient
    @DeleteMapping("/ingredients/{id}")
    public ResponseEntity<Void> supprimerIngredient(@PathVariable Long id) {
        recetteService.supprimerIngredient(id);
        return ResponseEntity.noContent().build();
    }
}

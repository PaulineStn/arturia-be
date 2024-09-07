package org.example.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "recettes")
public class Recette {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recetteId;

    @Column(nullable = false)
    private String nom;

    @Column
    private String description;

    @Column(name = "temps_preparation")
    private Integer tempsPreparation;

    @Column(name = "temps_cuisson")
    private Integer tempsCuisson;

    // Relation Many-to-Many avec la table Ingredient
    @ManyToMany
    @JoinTable(
            name = "recette_ingredients",
            joinColumns = @JoinColumn(name = "recette_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;

    // Getters et setters
    public Long getRecetteId() {
        return recetteId;
    }

    public void setRecetteId(Long recetteId) {
        this.recetteId = recetteId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getTempsPreparation() {
        return tempsPreparation;
    }

    public void setTempsPreparation(Integer tempsPreparation) {
        this.tempsPreparation = tempsPreparation;
    }

    public Integer getTempsCuisson() {
        return tempsCuisson;
    }

    public void setTempsCuisson(Integer tempsCuisson) {
        this.tempsCuisson = tempsCuisson;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}

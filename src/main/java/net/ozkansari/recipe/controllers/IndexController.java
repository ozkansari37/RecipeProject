package net.ozkansari.recipe.controllers;

import net.ozkansari.recipe.domain.Category;
import net.ozkansari.recipe.domain.UnitOfMeasure;
import net.ozkansari.recipe.repositories.CategoryRepository;
import net.ozkansari.recipe.repositories.UnitOfMeasureRepository;
import net.ozkansari.recipe.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}

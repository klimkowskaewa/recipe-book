package pl.edu.wszib.jwd.recipebook.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.edu.wszib.jwd.recipebook.data.repositories.RecipeRepository;
import pl.edu.wszib.jwd.recipebook.data.entities.Category;
import pl.edu.wszib.jwd.recipebook.data.entities.Recipe;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeRepository repository;

    @GetMapping
    public String listEdit(Model model, @RequestParam(required = false) Integer editedId){
        List<Recipe> recipesList = repository.findAll();
        Recipe newRecipe = new Recipe();
        Category[] categories = Category.values();

        model.addAttribute("recipes", recipesList);
        model.addAttribute("newRecipe", newRecipe);
        model.addAttribute("categories", categories);
        model.addAttribute("editedId", editedId);

        if(editedId != null) {
            model.addAttribute("editRecipe", repository.findById(editedId).get());
        }
        return "recipeListPage";
    }

    @GetMapping("/create")
    public String create (Model model){
        Recipe newRecipe = new Recipe();
        Category[] categories = Category.values();

        model.addAttribute("newRecipe", newRecipe);
        model.addAttribute("categories", categories);
        return "addRecipePage";
    }

    @PostMapping("/create")
    public String createAction(Recipe newRecipe, @RequestParam("file") MultipartFile file) throws IOException {
        newRecipe.setId(null);
        newRecipe.setPicture(file.getBytes());
        newRecipe.setMimeType(file.getContentType());
        repository.save(newRecipe);
        return "redirect:/recipes";
    }

    @GetMapping("/recipe/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> content (@PathVariable Integer id){
        Recipe recipe = repository.findById(id).get();

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(recipe.getMimeType() == null ? MediaType.IMAGE_JPEG : MediaType.parseMediaType(recipe.getMimeType()))
                .body(recipe.getPicture());
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        repository.deleteById(id);
        return "redirect:/recipes";
    }

    @PostMapping("/edit/{id}")
    public String edit(Recipe recipe, @PathVariable Integer id, @RequestParam("file") MultipartFile file) throws IOException  {
        Recipe existing = repository.findById(id).get();
        existing.setCategory(recipe.getCategory());
        existing.setIngredients(recipe.getIngredients());
        existing.setDescription(recipe.getDescription());
        existing.setDirections(recipe.getDirections());
        existing.setPicture(file.getBytes());
        existing.setMimeType(file.getContentType());
        repository.save(existing);
        return "redirect:/recipes";
    }
}
package pl.edu.wszib.jwd.recipebook.data.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.edu.wszib.jwd.recipebook.data.entities.Category;
import pl.edu.wszib.jwd.recipebook.data.entities.Recipe;

import java.util.List;
import java.util.Set;

public interface RecipeRepository extends JpaRepository<Recipe, Integer> {

    List<Recipe> findAllByCategory(Category category);
    @Query("select p.category from Recipe p")
    Set<Category> categories();

}

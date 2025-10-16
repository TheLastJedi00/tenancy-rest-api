//package ThinkDesk.Application.Controllers;
//
//import ThinkDesk.Application.DTOs.CategoryDto;
//import ThinkDesk.Domain.Models.Category;
//import ThinkDesk.Domain.Services.CategoryService;
//import jakarta.validation.Valid;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/categories")
//public class CategoryController {
//    private final CategoryService categoryService;
//
//    public CategoryController(CategoryService categoryService) {
//        this.categoryService = categoryService;
//    }
//
//
//    @PostMapping
//    public ResponseEntity<Category> create(@RequestBody @Valid CategoryDto data) {
//        return ResponseEntity.ok(categoryService.create(data));
//    }
//    @GetMapping
//    public ResponseEntity<List<Category>> getAll() {
//        return ResponseEntity.ok(categoryService.getAll());
//    }
//    @GetMapping("/{id}")
//    public ResponseEntity<Category> getById(@PathVariable Long id) {
//        return ResponseEntity.ok(categoryService.getById(id));
//    }
//    @PutMapping("/{id}")
//    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody @Valid CategoryDto data) {
//        return ResponseEntity.ok(categoryService.update(id, data));
//    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id) {
//        categoryService.delete(id);
//        return ResponseEntity.noContent().build();
//    }
//
//}

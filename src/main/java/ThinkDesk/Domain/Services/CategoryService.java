package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.CategoryDto;
import ThinkDesk.Domain.Models.Category;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Repositories.CategoryRepository;
import ThinkDesk.Infra.Mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    private final TenantService tenantService;

    public CategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper, TenantService tenantService) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
        this.tenantService = tenantService;
    }

    public Category create(CategoryDto categoryDto, Tenant tenant) {
        Category category = new Category(categoryDto, tenant);
        return categoryRepository.save(category);
    }

    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Category update(Long id, CategoryDto data) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new RuntimeException("Category not found"));
        categoryMapper.updateEntityFromDto(data, category);
        return categoryRepository.save(category);
    }

    public void delete(Long id) {
        categoryRepository.deleteById(id);
    }
}

package ThinkDesk.Application.Controllers;

import ThinkDesk.Application.DTOs.RoleDto;
import ThinkDesk.Domain.Models.Role;
import ThinkDesk.Domain.Services.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<Role> create(@RequestBody RoleDto data){
        return ResponseEntity.ok(roleService.create(data));
    }

    @GetMapping
    public ResponseEntity<List<Role>> getAll(){
        return ResponseEntity.ok(roleService.getAll());
    }

    @GetMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Role> getById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getById(id));
    }

    @GetMapping
    @RequestMapping("/{name}")
    public ResponseEntity<Role> getByName(@RequestParam String name) {
        return ResponseEntity.ok(roleService.getByName(name));
    }

    @PutMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable Long id, @RequestBody RoleDto data){
        return ResponseEntity.ok(roleService.update(id, data));
    }

    @DeleteMapping
    public  ResponseEntity<Role> delete(@PathVariable Long id){
        roleService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

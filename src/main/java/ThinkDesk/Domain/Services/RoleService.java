package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.RoleDto;
import ThinkDesk.Domain.Models.Role;
import ThinkDesk.Domain.Repositories.RoleRepository;
import ThinkDesk.Infra.Mapper.RoleMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;


    public RoleService(ThinkDesk.Domain.Repositories.RoleRepository roleRepository, RoleMapper roleMapper) {
        this.roleRepository = roleRepository;
        this.roleMapper = roleMapper;
    }

    public Role create(RoleDto data){
        return roleRepository.save(roleMapper.toEntity(data));
    }

    public Role getById(Long id){
        return roleRepository.getReferenceById(id);
    }

    public Role getByName(String name){
        return roleRepository.findByRole(name);
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role update(Long id, RoleDto data){
        Role role = roleRepository.findById(id).orElseThrow(() -> new RuntimeException("Role not found"));
        role.setRole(data.role());
        return roleRepository.save(role);
    }

    public void delete(Long id){
        roleRepository.deleteById(id);
    }

}

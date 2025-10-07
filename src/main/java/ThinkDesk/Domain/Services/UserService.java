package ThinkDesk.Domain.Services;

import ThinkDesk.Application.DTOs.UserRequestDto;
import ThinkDesk.Domain.Models.Tenant;
import ThinkDesk.Domain.Models.User;
import ThinkDesk.Domain.Repositories.UserRepository;
import ThinkDesk.Infra.Mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final TenantService tenantService;

    public UserService(UserRepository userRepository, UserMapper userMapper, TenantService tenantService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.tenantService = tenantService;
    }

    public User create(UserRequestDto userDto) {
        User user = userMapper.toEntity(userDto);
        Tenant tenant = tenantService.getById(userDto.tenantId());
        user.setTenant(tenant);
        return userRepository.save(new User());
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User com " + id + " não encontrado."));
    }

    public User update(Long id, UserRequestDto userDto) {
        User user = getById(id);
        userMapper.updateEntityFromDto(userDto, user);
        return userRepository.save(user);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);

    }
}


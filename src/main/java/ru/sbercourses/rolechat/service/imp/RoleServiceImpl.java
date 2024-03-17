package ru.sbercourses.rolechat.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sbercourses.rolechat.dao.RoleRepository;
import ru.sbercourses.rolechat.model.RoleEntity;
import ru.sbercourses.rolechat.service.RoleService;

import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleEntity getRoleEntityByUserId(long userId) {
        Optional<RoleEntity> optionalRole = roleRepository.findById(userId);
        return optionalRole.orElse(null);
    }

    @Override
    public void deleteEntityById(long roleId) {
        roleRepository.deleteById(roleId);
    }

    @Override
    public void addRole(RoleEntity role) {
        roleRepository.save(role);
    }
}
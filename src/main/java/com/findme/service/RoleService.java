package com.findme.service;

import com.findme.dao.RoleDAO;
import com.findme.exception.BadRequestException;
import com.findme.exception.InternalServerError;
import com.findme.models.Role;
import com.findme.models.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public Role save(Role role) throws InternalServerError, BadRequestException{
        validateRoleName(role.getName());
        return roleDAO.save(role);
    }

    public Role update(Role role) throws InternalServerError, BadRequestException {
        validateRoleName(role.getName());
        return roleDAO.update(role);
    }

    public void delete(long id) throws InternalServerError, BadRequestException {
        if (id <= 0) {
            throw new BadRequestException("Wrong id " + id);
        }

        roleDAO.delete(id);
    }

    public Role findById(long id) throws InternalServerError, BadRequestException {
        if (id <= 0) {
            throw new BadRequestException("Wrong id " + id);
        }

        return roleDAO.findById(id);
    }

    private void validateRoleName(String name) throws BadRequestException {
        try {
            UserRole.valueOf(name);
        } catch (IllegalStateException e) {
            throw new BadRequestException("Wrong role name " + name);
        }
    }
}

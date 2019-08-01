package com.findme.dao;

import com.findme.exception.InternalServerError;
import com.findme.models.Role;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class RoleDAO extends GeneralDAO<Role> {

    public RoleDAO() {
        setTypeParameterOfClass(Role.class);
    }

    @Override
    public Role save(Role obj) throws InternalServerError {
        return super.save(obj);
    }

    @Override
    public void delete(long id) throws InternalServerError {
        super.delete(id);
    }

    @Override
    public Role update(Role obj) throws InternalServerError {
        return super.update(obj);
    }

    @Override
    public Role findById(long id) throws InternalServerError {
        return super.findById(id);
    }
}

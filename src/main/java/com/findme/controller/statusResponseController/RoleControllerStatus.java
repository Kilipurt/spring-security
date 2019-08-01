package com.findme.controller.statusResponseController;

import com.findme.models.Role;
import com.findme.service.RoleService;
import com.findme.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleControllerStatus {

    private RoleService roleService;
    private JsonUtil jsonUtil;

    @Autowired
    public RoleControllerStatus(RoleService roleService, JsonUtil jsonUtil) {
        this.roleService = roleService;
        this.jsonUtil = jsonUtil;
    }

    @RequestMapping(path = "/role/add-role", method = RequestMethod.POST)
    public ResponseEntity<String> addRole(@RequestBody Role role) throws Exception {
        roleService.save(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/role/update-role", method = RequestMethod.POST)
    public ResponseEntity<String> updateRole(@RequestBody Role role) throws Exception {
        roleService.update(role);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/role/delete-role/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> deleteRole(@PathVariable String id) throws Exception {
        roleService.delete(Long.parseLong(id));
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @RequestMapping(path = "/role/get-role/{id}", method = RequestMethod.POST)
    public ResponseEntity<String> findRoleById(@PathVariable String id) throws Exception {
        return new ResponseEntity<>(jsonUtil.toJson(roleService.findById(Long.parseLong(id))), HttpStatus.OK);
    }
}

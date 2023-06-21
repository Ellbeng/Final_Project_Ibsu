package ge.ibsu.demo.controllers;

import ge.ibsu.demo.dto.EditUser;
import ge.ibsu.demo.dto.SearchUser;
import ge.ibsu.demo.dto.request.RequestData;
import ge.ibsu.demo.entities.User;
import ge.ibsu.demo.entities.enums.Role;
import ge.ibsu.demo.services.UserService;
import ge.ibsu.demo.utils.GeneralUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(value = "api/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAuthority('user:read')")
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = {"application/json"})
    public List<User> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();


        if (!authentication.getAuthorities().contains(Role.ADMIN)) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        return userService.getAll();
    }

    @PreAuthorize("hasAuthority('user:read')")
    @RequestMapping(value = "/search", method = RequestMethod.POST, produces = {"application/json"})
    public Slice<User> search(@RequestBody RequestData<SearchUser> rd) {
        return userService.search(rd.getData(), rd.getPaging());
    }

    @PreAuthorize("hasAuthority('user:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/json"})
    public User getById(@PathVariable Long id) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserId = authentication.getName();


        if (!currentUserId.equals(id.toString()) && !authentication.getAuthorities().contains(Role.ADMIN)) {
            throw new AccessDeniedException("You are not authorized to access this resource");
        }
        return userService.getById(id);
    }

    @PreAuthorize("hasAuthority('user:read')")
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = {"application/json"})
    public User edit(@PathVariable Long id, @RequestBody EditUser editUser) throws Exception {
        GeneralUtil.checkRequiredProperties(editUser, Arrays.asList("email"));

        return userService.edit(id, editUser);
    }
}

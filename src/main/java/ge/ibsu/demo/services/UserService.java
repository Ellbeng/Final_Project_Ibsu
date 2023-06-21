package ge.ibsu.demo.services;

import ge.ibsu.demo.dto.EditUser;
import ge.ibsu.demo.dto.SearchUser;
import ge.ibsu.demo.dto.request.Paging;
import ge.ibsu.demo.entities.User;
import ge.ibsu.demo.repositories.UserRepository;
import ge.ibsu.demo.utils.GeneralUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;


    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User getById(Long id) throws Exception {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("RECORD_NOT_FOUND"));
    }

    public Slice<User> search(SearchUser searchUser, Paging paging){
        String email = null;

        if(searchUser.getEmail() != null && !searchUser.getEmail().equals("")){
            email = "%" + searchUser.getEmail() + "%";
        }

        Pageable pageable = PageRequest.of(paging.getPage(), paging.getSize());
        return userRepository.search( email, pageable);
    }


    @Transactional
    public User edit(Long id, EditUser editUser) throws Exception{
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("USER_NOT_FOUND"));
        GeneralUtil.getCopyOf(editUser,user);

        return userRepository.save(user);
    }

}

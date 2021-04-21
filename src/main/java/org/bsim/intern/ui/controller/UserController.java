package org.bsim.intern.ui.controller;

import org.bsim.intern.service.iservice.IUserService;
import org.bsim.intern.shared.dto.UserDTO;
import org.bsim.intern.ui.model.request.UserRequest;
import org.bsim.intern.ui.model.response.UserResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    IUserService userService;

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<UserResponse> getUsers(){
        List<UserResponse> value = new ArrayList<>();
        ModelMapper mapper = new ModelMapper();

        List<UserDTO> users = userService.getListUser();
        for (UserDTO userDTO : users){
            value.add(mapper.map(userDTO, UserResponse.class));
        }

        return value;
    }

    @GetMapping(path = "/{username}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse getUserByUsername(@PathVariable String username){
        UserDTO getUser = userService.getUserByUsername(username);
        if (getUser == null)
            return null;
        //else
        return new ModelMapper().map(getUser, UserResponse.class);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public UserResponse addNewUser(@RequestBody UserRequest user){
        ModelMapper mapper = new ModelMapper();
        //UserRequest --> UserDTO
        UserDTO userDTO = mapper.map(user, UserDTO.class);
        UserDTO createdUser = userService.addNewData(userDTO);
        //UserDTO --> UserResponse
        UserResponse response = mapper.map(createdUser, UserResponse.class);

        return response;
    }

}

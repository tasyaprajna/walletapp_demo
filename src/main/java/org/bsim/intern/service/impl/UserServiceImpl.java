package org.bsim.intern.service.impl;

import org.bsim.intern.io.entity.UserEntity;
import org.bsim.intern.io.irepository.UserRepository;
import org.bsim.intern.service.iservice.IUserInterface;
import org.bsim.intern.shared.dto.UserDTO;
import org.bsim.intern.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.util.Length;

import java.util.List;

@Service
public class UserServiceImpl implements IUserInterface {
    @Autowired
    UserRepository userRepository;
    @Autowired
    GenerateRandomPublicId generateRandomPublicId;

    @Override
    public List<UserDTO> getListUser() {
        return null;
    }

    @Override
    public UserDTO addData(UserDTO user) {
        user.setUserId(generateRandomPublicId.generateUserId( 30)); //generate user id

        ModelMapper mapper = new ModelMapper();

        //UserDTO --> UserEntity (save data di entity)
        UserEntity entity = mapper.map(user, UserEntity.class);
        UserEntity storeData = userRepository.save(entity);

        //UserEntity --> UserDTO (return value)
        UserDTO value = mapper.map(storeData, UserDTO.class);

        return null;
    }
}

package org.bsim.intern.service.impl;

import org.bsim.intern.io.entity.UserEntity;
import org.bsim.intern.io.entity.WalletEntity;
import org.bsim.intern.io.irepository.UserRepository;
import org.bsim.intern.io.irepository.WalletRepository;
import org.bsim.intern.service.iservice.IWalletService;
import org.bsim.intern.shared.dto.UserDTO;
import org.bsim.intern.shared.dto.WalletDTO;
import org.bsim.intern.shared.utils.GenerateRandomPublicId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WalletServiceImpl implements IWalletService {
    @Autowired
    WalletRepository walletRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    GenerateRandomPublicId generateRandomPublicId;

    @Override
    public WalletDTO addNewWalletData(String userid, WalletDTO walletDTO){
        ModelMapper mapper = new ModelMapper();

        //Generate WalletId
        walletDTO.setWalletid(generateRandomPublicId.generateUserId(30));

        //Get User
        UserEntity userData = userRepository.findByUserId(userid);

        //Set User
        walletDTO.setUser(mapper.map(userData, UserDTO.class));
        WalletEntity entity = mapper.map(walletDTO, WalletEntity.class);

        //save data to database (table: walletsTBL)
        WalletEntity storedData = walletRepository.save(entity);

        return mapper.map(storedData, WalletDTO.class);
    }
}

package org.bsim.intern.service.iservice;

import org.bsim.intern.shared.dto.WalletDTO;

public interface IWalletService {
    WalletDTO addNewWalletData(String userid, WalletDTO walletsDTO);
}
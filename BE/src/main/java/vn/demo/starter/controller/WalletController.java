package vn.demo.starter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.demo.starter.service.UserCryptoWalletService;
import vn.demo.starter.service.UserWalletService;
import vn.demo.starter.service.dto.WalletDto;

import java.util.List;

@RestController
@RequestMapping("/api/wallet")
@RequiredArgsConstructor
@Tag(name = "Wallet Resource")
public class WalletController {

    private final UserWalletService userWalletService;
    private final UserCryptoWalletService userCryptoWalletService;

    @GetMapping
    public ResponseEntity<WalletDto> getWallet() {
        return ResponseEntity.ok(userWalletService.getWallet());
    }

    @GetMapping(value = "/crypto")
    public ResponseEntity<List<WalletDto>> getWalletCrypto() {
        return ResponseEntity.ok(userCryptoWalletService.getWallets());
    }
}

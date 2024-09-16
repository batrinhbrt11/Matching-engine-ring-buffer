package vn.demo.starter.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.demo.starter.service.AssetService;
import vn.demo.starter.service.dto.AssetDto;

import java.util.List;

@RestController
@RequestMapping("/api/asset")
@RequiredArgsConstructor
@Tag(name = "Asset Resource")
public class AssetController {

    private final AssetService assetService;

    @GetMapping
    public ResponseEntity<List<AssetDto>> getAssets() {
        return ResponseEntity.ok(assetService.getAssetResponse());
    }
}

package vn.demo.starter.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.demo.starter.entity.Asset;
import vn.demo.starter.exception.BadRequestException;
import vn.demo.starter.repository.AssetRepository;
import vn.demo.starter.service.AssetService;
import vn.demo.starter.service.dto.AssetDto;
import vn.demo.starter.service.mapper.AppMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final AppMapper appMapper;

    @Override
    public List<Asset> getAsset() {
        return assetRepository
                .findAll()
                .stream()
                .toList();
    }

    @Override
    public Asset getById(Long id) {
        return assetRepository.findById(id).orElseThrow(() -> new BadRequestException("Asset is not exist"));
    }

    @Override
    public List<AssetDto> getAssetResponse() {
        return assetRepository
                .findAll()
                .stream()
                .map(appMapper::toDto).toList();
    }
}

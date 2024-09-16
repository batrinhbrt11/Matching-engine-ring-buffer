package vn.demo.starter.service;

import vn.demo.starter.entity.Asset;
import vn.demo.starter.service.dto.AssetDto;

import java.util.List;

public interface AssetService {

    List<Asset> getAsset();

    Asset getById(Long id);

    List<AssetDto> getAssetResponse();

}

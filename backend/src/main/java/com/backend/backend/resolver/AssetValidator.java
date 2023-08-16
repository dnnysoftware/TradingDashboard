package com.backend.backend.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.backend.backend.model.Asset;
import com.backend.backend.repository.AssetRepository;

@Component
public class AssetValidator implements Validator {

    @Autowired
    private AssetRepository assetRepository;

    @Override
    public boolean supports(Class<?> clazz) {
        return Asset.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Asset asset = (Asset) target;

        if (assetRepository.existsByNameAndTicker(asset.getName(), asset.getTicker())) {
            errors.rejectValue("name", "duplicate.nameAndTicker", "Asset with the same name and ticker already exists.");
        }
    }
}

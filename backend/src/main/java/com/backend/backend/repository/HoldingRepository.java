package com.backend.backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.backend.backend.model.Holding;

public interface HoldingRepository extends MongoRepository<Holding, String> {



}

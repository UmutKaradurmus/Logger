package com.micro.logger.repository;

import com.micro.logger.model.LogEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogRepository extends ElasticsearchRepository<LogEntity, String> {
  }


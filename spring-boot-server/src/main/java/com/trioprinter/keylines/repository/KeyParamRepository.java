package com.trioprinter.keylines.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.trioprinter.keylines.model.KeyParam;


public interface KeyParamRepository extends JpaRepository<KeyParam, Long> {

	List<KeyParam> findByShapeTemplateId(byte  shapeTemplateId);
	
	
}

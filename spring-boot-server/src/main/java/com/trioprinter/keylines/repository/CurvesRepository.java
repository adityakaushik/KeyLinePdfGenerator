package com.trioprinter.keylines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.trioprinter.keylines.model.Curves;

@Repository
public interface CurvesRepository extends JpaRepository<Curves, Long> {
	 @Nullable
	List<Curves> findByShapeTemplateId(byte  shapeTemplateId);
}

package com.trioprinter.keylines.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import com.trioprinter.keylines.model.Lines;

@Repository
public interface LinesRepository extends JpaRepository<Lines, Long> {
	 @Nullable
	List<Lines> findByShapeTemplateId(byte  shapeTemplateId);
}

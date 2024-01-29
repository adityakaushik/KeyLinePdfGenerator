package com.trioprinter.keylines.controller;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.trioprinter.keylines.model.KeyParam;
import com.trioprinter.keylines.model.Tutorial;
import com.trioprinter.keylines.pojos.CurveValues;
import com.trioprinter.keylines.pojos.KeyLineParam;
import com.trioprinter.keylines.pojos.LineValues;
import com.trioprinter.keylines.repository.KeyParamRepository;
import com.trioprinter.keylines.repository.TutorialRepository;
import com.trioprinter.keylines.services.DrawKeyLines;
import com.trioprinter.keylines.services.DrawLine;
import com.trioprinter.keylines.services.DrawSimpleInvertedKeyLines;
import com.trioprinter.keylines.services.ResolveCurves;
import com.trioprinter.keylines.services.ResolveLine;
import com.trioprinter.keylines.services.ResolveParamValues;


@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class TutorialController {

	@Autowired
	TutorialRepository tutorialRepository;


	@Autowired
	KeyParamRepository keyParamRepository;

	@Autowired
	DrawLine drawLine;
	
	@Autowired 
	ResolveLine resolveLine;
	
	@Autowired 
	ResolveCurves resolveCurves ;
	
	@Autowired
	ResolveParamValues resolveParamValues;
	
	@Autowired
	DrawKeyLines drawKeyLines;
	
	@Autowired
	DrawSimpleInvertedKeyLines drawSimpleInvertedKeyLines;
	
	@RequestMapping(value={"/tutorials"})
	public ResponseEntity<Object> getAllTutorials() {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();
			
			tutorialRepository.findAll().forEach(tutorials::add);
			
			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
	@RequestMapping(value={"/tutorials/{title}"})
	public ResponseEntity<Object> getAllTutorialsTitle(@PathVariable(required = false) String title) {
		try {
			List<Tutorial> tutorials = new ArrayList<Tutorial>();

			if (title == null)
				tutorialRepository.findAll().forEach(tutorials::add);
			else
				tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<Object>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/tutorials/{id}")
	public ResponseEntity<Tutorial> getTutorialById(@PathVariable("id") long id) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			return new ResponseEntity<>(tutorialData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/tutorials")
	public ResponseEntity<Object> createTutorial(@RequestBody Tutorial tutorial) {
		try {
			Tutorial _tutorial = tutorialRepository
					.save(new Tutorial(tutorial.getTitle(), tutorial.getDescription(), false));
			return new ResponseEntity<Object>(_tutorial, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/tutorials/{id}")
	public ResponseEntity<Object> updateTutorial(@PathVariable("id") long id, @RequestBody Tutorial tutorial) {
		Optional<Tutorial> tutorialData = tutorialRepository.findById(id);

		if (tutorialData.isPresent()) {
			Tutorial _tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			return new ResponseEntity<Object>(tutorialRepository.save(_tutorial), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/tutorials/{id}")
	public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") long id) {
		try {
			tutorialRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/tutorials")
	public ResponseEntity<HttpStatus> deleteAllTutorials() {
		try {
			tutorialRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/tutorials/published")
	public ResponseEntity<List<Tutorial>> findByPublished() {
		try {
			List<Tutorial> tutorials = tutorialRepository.findByPublished(true);

			if (tutorials.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(tutorials, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	
//	@RequestMapping(value = "/tutorials/pdfreport", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
//	public ResponseEntity<InputStreamResource> getPDF() {
//		try {
//			  ByteArrayInputStream bis = drawLine.generateKeyLinePdf();
//			  HttpHeaders headers = new HttpHeaders();
//		        headers.add("Content-Disposition", "inline; filename=keylines.pdf");
//
//		        return ResponseEntity
//		                .ok()
//		                .headers(headers)
//		                .contentType(MediaType.APPLICATION_PDF)
//		                .body(new InputStreamResource(bis));
//		} catch (Exception e) {
//			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//	}
	
	
	@RequestMapping(value = "/tutorials/formula", method = RequestMethod.GET,
          produces = MediaType.APPLICATION_JSON_VALUE)
	public  ResponseEntity<List<KeyParam>> getformula() {
		try {
			List<KeyParam> params = keyParamRepository.findByShapeTemplateId((byte) 1);

			if (params.isEmpty()&&params!=null) {
				System.out.println("params empty");
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			System.out.println("resolveParams hitting");
		//	ResolveParamValues.resolveParams(params,1,2,2,2,2,2);
			System.out.println("resolveParams back");
			return new ResponseEntity<>(params, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
//	@RequestMapping(value = "/tutorials/lineValue", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
//		public  ResponseEntity<InputStreamResource>  getLinesParam() {
//			try {
//				int templateId=1; 
//				double L=60; 
//				double W=30; 
//				double D=80; 
//				double CTBK=0.5;
//				double TUCKIN=12;
//				double PASTING=10;
//				int numX=4;
//				int numY=5;
//				List<KeyParam> params = keyParamRepository.findByShapeTemplateId((byte) templateId);
//				HashMap<String, Double> mParamValue = resolveParamValues
//						.resolveParams(params, L, W, D, CTBK, TUCKIN, PASTING);
//				List<LineValues> lv=resolveLine.resolveParams(mParamValue,templateId, L, W, D, CTBK, TUCKIN, 10);
//				List<CurveValues> cv=resolveCurves.resolveParams(mParamValue, templateId, L, W, D, CTBK, TUCKIN, PASTING);
//				ByteArrayInputStream bis = drawKeyLines.generateSimpleKeyLine(lv, cv,mParamValue,numX, numY);
//				HttpHeaders headers = new HttpHeaders();
//		        headers.add("Content-Disposition", "inline; filename=keylines.pdf");
//
//		        return ResponseEntity
//		                .ok()
//		                .headers(headers)
//		                .contentType(MediaType.APPLICATION_PDF)
//		                .body(new InputStreamResource(bis));
//			} catch (Exception e) {
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		}
	
	@RequestMapping(value = "/createKeylines/simplekeyLine", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_PDF_VALUE)
		public  ResponseEntity<InputStreamResource>  getSimplekeyLine(@RequestBody KeyLineParam keyLineParam) {
			try {
				int templateId=keyLineParam.getTemplateId(); 
				double L=keyLineParam.getL(); 
				double W=keyLineParam.getW(); 
				double D=keyLineParam.getD(); 
				double CTBK=keyLineParam.getCTBK();
				double TUCKIN=keyLineParam.getTUCKIN();
				double PASTING=keyLineParam.getPASTING();
				int numX=keyLineParam.getNumX();
				int numY=keyLineParam.getNumY();
				
				List<KeyParam> params = keyParamRepository.findByShapeTemplateId((byte) templateId);
				HashMap<String, Double> mParamValue = resolveParamValues
						.resolveParams(params, L, W, D, CTBK, TUCKIN, PASTING);
				List<LineValues> lv=resolveLine.resolveParams(mParamValue,templateId, L, W, D, CTBK, TUCKIN, 10);
				List<CurveValues> cv=resolveCurves.resolveParams(mParamValue, templateId, L, W, D, CTBK, TUCKIN, PASTING);
				ByteArrayInputStream bis = drawKeyLines.generateSimpleKeyLine(lv, cv,mParamValue,numX, numY);
				HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=keylines.pdf");

		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	
//	@RequestMapping(value = "/tutorials/inverted", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
//		public  ResponseEntity<InputStreamResource>  getLinesParam2() {
//			try {
//				int templateId=1; 
//				double L=60; 
//				double W=30; 
//				double D=80; 
//				double CTBK=0.5;
//				double TUCKIN=12;
//				double PASTING=10;
//				int numX=4;
//				int numY=4;
//				List<KeyParam> params = keyParamRepository.findByShapeTemplateId((byte) templateId);
//				HashMap<String, Double> mParamValue = resolveParamValues
//						.resolveParams(params, L, W, D, CTBK, TUCKIN, PASTING);
//				List<LineValues> lv=resolveLine.resolveParams(mParamValue,templateId, L, W, D, CTBK, TUCKIN, 10);
//				List<CurveValues> cv=resolveCurves.resolveParams(mParamValue, templateId, L, W, D, CTBK, TUCKIN, PASTING);
//				ByteArrayInputStream bis = drawSimpleInvertedKeyLines.generateSimpleKeyLine(lv, cv,mParamValue,numX, numY);
//				HttpHeaders headers = new HttpHeaders();
//		        headers.add("Content-Disposition", "inline; filename=InvertedKeyLines.pdf");
//
//		        return ResponseEntity
//		                .ok()
//		                .headers(headers)
//		                .contentType(MediaType.APPLICATION_PDF)
//		                .body(new InputStreamResource(bis));
//			} catch (Exception e) {
//				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//		}
	
	

	@RequestMapping(value = "/createKeylines/invertedKeyLine", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_PDF_VALUE)
		public  ResponseEntity<InputStreamResource>  getLinesParamInverted(@RequestBody KeyLineParam keyLineParam) {
			try {
				int templateId=keyLineParam.getTemplateId(); 
				double L=keyLineParam.getL(); 
				double W=keyLineParam.getW(); 
				double D=keyLineParam.getD(); 
				double CTBK=keyLineParam.getCTBK();
				double TUCKIN=keyLineParam.getTUCKIN();
				double PASTING=keyLineParam.getPASTING();
				int numX=keyLineParam.getNumX();
				int numY=keyLineParam.getNumY();
				
				List<KeyParam> params = keyParamRepository.findByShapeTemplateId((byte) templateId);
				HashMap<String, Double> mParamValue = resolveParamValues
						.resolveParams(params, L, W, D, CTBK, TUCKIN, PASTING);
				List<LineValues> lv=resolveLine.resolveParams(mParamValue,templateId, L, W, D, CTBK, TUCKIN, 10);
				List<CurveValues> cv=resolveCurves.resolveParams(mParamValue, templateId, L, W, D, CTBK, TUCKIN, PASTING);
				ByteArrayInputStream bis = drawSimpleInvertedKeyLines.generateSimpleKeyLine(lv, cv,mParamValue,numX, numY);
				HttpHeaders headers = new HttpHeaders();
		        headers.add("Content-Disposition", "inline; filename=InvertedKeyLines.pdf");

		        return ResponseEntity
		                .ok()
		                .headers(headers)
		                .contentType(MediaType.APPLICATION_PDF)
		                .body(new InputStreamResource(bis));
			} catch (Exception e) {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
}

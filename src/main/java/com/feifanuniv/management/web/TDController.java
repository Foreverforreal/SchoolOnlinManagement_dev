package com.feifanuniv.management.web;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.feifanuniv.management.service.TeachingPlanService;

@Controller
public class TDController extends BaseController {
	static TeachingPlanService schoolTeachingPlanService = (TeachingPlanService) getApplicationContext()
			.getBean("schoolService");

	public ResponseEntity<byte[]> downloadTeachingplanEbook(String schoolSymbol) throws IOException {
		File downloadExcel = schoolTeachingPlanService.downloadAllTeachingplan(schoolSymbol);
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment",
				new String(downloadExcel.getName().getBytes("UTF-8"), "iso-8859-1"));
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(downloadExcel), headers, HttpStatus.CREATED);
	}
}

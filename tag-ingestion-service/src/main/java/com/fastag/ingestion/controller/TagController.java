package com.fastag.ingestion.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fastag.common.dto.TagScanEvent;
//import com.fastag.ingestion.dto.TagScanEvent;
import com.fastag.ingestion.service.TagService;

@RestController
@RequestMapping("/tag")
public class TagController {

	private TagService tagService;
	
	public TagController(TagService tagService) {
		this.tagService = tagService;
	}

	@PostMapping("/scan")
	public String scan(@RequestBody TagScanEvent event) {
        return tagService.scanTag(event);
    }
}

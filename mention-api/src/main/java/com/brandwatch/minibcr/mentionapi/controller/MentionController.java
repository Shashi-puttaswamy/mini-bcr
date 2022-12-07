package com.brandwatch.minibcr.mentionapi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.minibcr.common.model.Mention;
import com.brandwatch.minibcr.mentionapi.repository.MentionRepository;


@RestController
@RequestMapping("/mention")
public class MentionController {

    private final MentionRepository mentionRepository;

    public MentionController(MentionRepository mentionRepository) {
        this.mentionRepository = mentionRepository;
    }


    @RequestMapping("/query/{queryId}")
    public ResponseEntity<List<Mention>> getMentionsByQueryId(@PathVariable("queryId") Long queryId) {
        return new ResponseEntity<>(mentionRepository.findByQueryId(queryId), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Mention>> getMentions() {
        List<Mention> mentionList = new ArrayList<>();
        mentionRepository.findAll().iterator().forEachRemaining(mentionList::add);
        return new ResponseEntity<>(mentionList, HttpStatus.OK);
    }

}

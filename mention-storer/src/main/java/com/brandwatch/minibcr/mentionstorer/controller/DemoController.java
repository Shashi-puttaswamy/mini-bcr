package com.brandwatch.minibcr.mentionstorer.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.minibcr.mentionstorer.repository.MentionRepository;

@RestController
@RequestMapping("/mention")
public class DemoController {

    private final MentionRepository mentionRepository;

    public DemoController(MentionRepository mentionRepository) {
        this.mentionRepository = mentionRepository;
    }

    @DeleteMapping
    public void deleteAll() {
        mentionRepository.deleteAll();
    }
}

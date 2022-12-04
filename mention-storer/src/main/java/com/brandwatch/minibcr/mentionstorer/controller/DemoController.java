package com.brandwatch.minibcr.mentionstorer.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.minibcr.common.model.Resource;
import com.brandwatch.minibcr.mentionstorer.entity.Mention;
import com.brandwatch.minibcr.mentionstorer.repository.MentionRepository;

@RestController
@RequestMapping("/mention")
public class DemoController {

    private final MentionRepository mentionRepository;

    public DemoController(MentionRepository mentionRepository) {
        this.mentionRepository = mentionRepository;
    }

    @PostMapping
    public void saveMention() {
        Mention mention = new Mention();
        mention.setBody("body");
        mention.setTitle("title");
        mention.setQueryId(1L);
        mention.setResource(new Resource("testTitle", "testText"));
        mentionRepository.save(mention);
    }

    @DeleteMapping
    public void deleteAll() {
        mentionRepository.deleteAll();
    }
}

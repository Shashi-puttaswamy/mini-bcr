package com.brandwatch.minibcr.queryapi.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.brandwatch.minibcr.queryapi.entity.UserQuery;
import com.brandwatch.minibcr.queryapi.repository.UserQueryRepository;

@RestController
@RequestMapping("/query")
public class UserQueryController {

    private final UserQueryRepository userQueryRepository;

    public UserQueryController(UserQueryRepository userQueryRepository) {
        this.userQueryRepository = userQueryRepository;
    }

    @GetMapping
    public ResponseEntity<List<UserQuery>> getUserQueries() {
        try {
            return new ResponseEntity<>(userQueryRepository.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserQuery> getQueryById(@PathVariable("id") long id) {
        Optional<UserQuery> userQueryById = userQueryRepository.findById(id);
        return userQueryById
                .map(userQuery
                        -> new ResponseEntity<>(userQuery, HttpStatus.OK)).orElseGet(()
                        -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<UserQuery> newUserQuery(@RequestBody UserQuery userQuery) {
        UserQuery newUserQuery = userQueryRepository.save(new UserQuery().setQuery(userQuery.getQuery()));
        return new ResponseEntity<>(newUserQuery, HttpStatus.OK);
    }

}

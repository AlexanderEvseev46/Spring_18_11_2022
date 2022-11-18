package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@RestController
public class Controller {
    private final List<Topic> topics = new ArrayList<>();
    private final List<User> users = new ArrayList<>();
    //curl -X POST http://localhost:8080/addTopic -d "{\"name\" : \"USA\"}" -H "Content-Type: application/json"
    @PostMapping("addTopic")
    public ResponseEntity<Void> addTopic(@RequestBody String name){
        Topic topic = new Topic();
        topic.setName(name);
        topics.add(topic);
        return ResponseEntity.accepted().build();
    }
    //curl -X DELETE http://localhost:8080/deleteTopic/1 -H "Content-Type: application/json"
    @DeleteMapping("deleteTopic/{index}")
    public ResponseEntity<Void> deleteTopic(@PathVariable ("index") Integer index){
        if(index < topics.size()) {
            topics.remove((int)index);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    //curl -X GET http://localhost:8080/getAllTopics -H "Content-Type: application/json"
    @GetMapping("getAllTopics")
    public ResponseEntity<List<String>> getAllTopics(){
        List<String> topicnames = new ArrayList<>();
        for (int i = 0; i < topics.size(); i++){
            topicnames.add(topics.get(i).getName());
        }
        return ResponseEntity.ok(topicnames);
    }
    //curl -X PUT http://localhost:8080/updateTopic/1 -d "{\"name\" : \"China\"}" -H "Content-Type: application/json"
    @PutMapping("updateTopic/{index}")
    public ResponseEntity<Void> updateTopic(@PathVariable ("index") Integer index,
                                            @RequestBody String name){
        if(index < topics.size()) {
            topics.remove((int) index);
            Topic topic = new Topic();
            topic.setName(name);
            topics.add(index, topic);
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    //curl -X GET http://localhost:8080/getCount -H "Content-Type: application/json"
    @GetMapping("getCount")
    public ResponseEntity<Integer> getCount(){
        return ResponseEntity.ok(topics.size());
    }
    //curl -X DELETE http://localhost:8080/deleteAll -H "Content-Type: application/json"
    @DeleteMapping("deleteAll")
    public ResponseEntity<Void> deleteAll(){
        topics.clear();
        return ResponseEntity.accepted().build();
    }
    //curl -X GET http://localhost:8080/getTopic/3 -H "Content-Type: application/json"
    @GetMapping("getTopic/{index}")
    public ResponseEntity<String> getTopic(@PathVariable ("index") Integer index){
        if (index < topics.size()) {
            return ResponseEntity.ok(topics.get(index).getName());
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
    //curl -X POST http://localhost:8080/addComment/2 -d "{\"comment\" : \"Good\"}" -H "Content-Type: application/json"
    @PostMapping("addComment/{index}")
    public ResponseEntity<Void> addTopic(@RequestBody String comment,
                                         @PathVariable ("index") Integer index){
        if(index < topics.size()){
        topics.get(index).getComments().add(comment);
        return ResponseEntity.accepted().build();}
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    //curl -X DELETE http://localhost:8080/deleteComment/1/5 -H "Content-Type: application/json"
    @DeleteMapping("deleteComment/{indexTopic}/{indexComment}")
    public ResponseEntity<Void> deleteComment(@PathVariable ("indexTopic") Integer indexTopic,
                                              @PathVariable ("indexComment") Integer indexComment){
        if(indexTopic < topics.size() && indexComment < topics.get(indexTopic).getComments().size()){
        topics.get(indexTopic).getComments().remove((int) indexComment);
        return ResponseEntity.accepted().build();}
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    //curl -X PUT http://localhost:8080/updateComment/1/2 -d "{\"comment\" : \"Very good\"}" -H "Content-Type: application/json"
    @PutMapping("updateComment/{indexTopic}/{indexComment}")
    public ResponseEntity<Void> update(@PathVariable ("indexTopic") Integer indexTopic,
                                       @PathVariable ("indexComment") Integer indexComment,
                                       @RequestBody String comment){
        if(indexTopic < topics.size() && indexComment < topics.get(indexTopic).getComments().size()){
            topics.get(indexTopic).getComments().remove((int) indexComment);
            topics.get(indexTopic).getComments().add(indexComment, comment);
            return ResponseEntity.accepted().build();}
        else {
            return ResponseEntity.badRequest().build();
        }
    }
    //curl -X GET http://localhost:8080/getAllComments -H "Content-Type: application/json"
    @GetMapping("getAllComments/{index}")
    public ResponseEntity<List<String>> getAllcomments(@PathVariable ("index") Integer index){
        if(index < topics.size()){
            return ResponseEntity.ok(topics.get(index).getComments());}
        else {
            return ResponseEntity.badRequest().build();
        }
    }

}

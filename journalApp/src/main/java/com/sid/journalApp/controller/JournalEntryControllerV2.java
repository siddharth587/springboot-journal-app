package com.sid.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.LongFunction;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.journalApp.entity.JournalEntry;
import com.sid.journalApp.service.JournalEntryservice;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

    @Autowired
    private JournalEntryservice journalEntryservice;
    
    @GetMapping
    public  ResponseEntity<?> getAll(){
        List<JournalEntry> all = journalEntryservice.getAll();

    if (all != null && !all.isEmpty()) {
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping
    public ResponseEntity<JournalEntry> createentry(@RequestBody JournalEntry myEntry){
        try {
            journalEntryservice.saveEntry(myEntry);
       myEntry.setDate(LocalDateTime.now());
       return new ResponseEntity<>(myEntry,HttpStatus.CREATED); 
        } catch (Exception e) {
            return new ResponseEntity<>(myEntry,HttpStatus.BAD_REQUEST);
        }
       
    }
    @GetMapping("id/{myID}")
    public ResponseEntity<JournalEntry> getJournalEntrybyid(@PathVariable ObjectId myID){
         Optional<JournalEntry> journalEntry = journalEntryservice.findbyid(myID);
         if(journalEntry.isPresent()){
            return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);  
         }
         return new ResponseEntity<>(HttpStatus.NOT_FOUND); 
    }
    @DeleteMapping("id/{myID}")
    public ResponseEntity<?> deleteJournalEntrybyid(@PathVariable ObjectId myID){
      journalEntryservice.deletebyid(myID);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
        
    @PutMapping("id/{myID}")
    public ResponseEntity<JournalEntry> updateJournalEntrybyid(@PathVariable("myID") ObjectId myID,@RequestBody JournalEntry newEntry){
       JournalEntry old = journalEntryservice.findbyid(myID).orElse( null);
       if(old!=null){
        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
        old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():newEntry.getContent());
        journalEntryservice.saveEntry(old);
        return new ResponseEntity<>(old,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
       
       
    }
    
}

              

    


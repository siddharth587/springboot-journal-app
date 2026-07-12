package com.sid.journalApp.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<JournalEntry> getAll(){
        return journalEntryservice.getAll();
    }
    @PostMapping
    public JournalEntry createentry(@RequestBody JournalEntry myEntry){
       journalEntryservice.saveEntry(myEntry);
       myEntry.setDate(LocalDateTime.now());
       return myEntry; 
    }
    @GetMapping("id/{myID}")
    public JournalEntry getJournalEntrybyid(@PathVariable ObjectId myID){
        return journalEntryservice.findbyid(myID).orElse(null);
    }
    @DeleteMapping("id/{myID}")
    public boolean deleteJournalEntrybyid(@PathVariable ObjectId myID){
      journalEntryservice.deletebyid(myID);
      return true;
    }
        
    @PutMapping("id/{myID}")
    public JournalEntry updateJournalEntrybyid(@PathVariable("myID") ObjectId myID,@RequestBody JournalEntry newEntry){
       JournalEntry old = journalEntryservice.findbyid(myID).orElse( null);
       if(old!=null){
        old.setTitle(newEntry.getTitle()!=null && !newEntry.getTitle().equals("")?newEntry.getTitle():old.getTitle());
        old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():newEntry.getContent());
       }
       journalEntryservice.saveEntry(old);
       
       return old; 
    }
    
}

              

    


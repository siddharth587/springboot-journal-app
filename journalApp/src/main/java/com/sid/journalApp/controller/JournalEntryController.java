package com.sid.journalApp.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.LongFunction;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sid.journalApp.entity.JournalEntry;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    private Map<Long,JournalEntry> journalentries = new HashMap<>();
    @GetMapping
    public List<JournalEntry> getAll(){
        return new ArrayList<>(journalentries.values());
    }
    @PostMapping
    public boolean createentry(@RequestBody JournalEntry myEntry){
        journalentries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myID}")
    public JournalEntry getJournalEntrybyid(@PathVariable Long myID){
        return journalentries.get(myID);
    }
    @DeleteMapping("id/{myID}")
    public JournalEntry deleteJournalEntrybyid(@PathVariable Long myID){
        return journalentries.remove(myID);
    }
    @PutMapping("id/{myID}")
    public JournalEntry updateJournalEntrybyid(@PathVariable("myID") Long myID,@RequestBody JournalEntry myEntry){
        return journalentries.put(myID,myEntry);
    }
    
}

              

    


package com.sid.journalApp.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import com.sid.journalApp.entity.JournalEntry;
import com.sid.journalApp.repository.JournalEntryrepository;

@Component
public class JournalEntryservice {
    @Autowired
     private JournalEntryrepository journalEntryrepository;

     public void saveEntry(JournalEntry journalEntry){
      journalEntry.setDate(LocalDateTime.now());
        journalEntryrepository.save(journalEntry);
     }
     public List<JournalEntry> getAll(){
      return journalEntryrepository.findAll();
     }
     public Optional<JournalEntry> findbyid(ObjectId id){
        return journalEntryrepository.findById(id);
     }
     public void deletebyid(ObjectId id){
      journalEntryrepository.deleteById(id);
     }
}
//controller --> service --> repository
package com.sid.journalApp.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sid.journalApp.entity.JournalEntry;

public interface JournalEntryrepository extends MongoRepository<JournalEntry, ObjectId> {
    
}
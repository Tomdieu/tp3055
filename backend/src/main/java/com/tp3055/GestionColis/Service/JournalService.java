package com.tp3055.GestionColis.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tp3055.GestionColis.Repository.JournalRepository;

@Service
public class JournalService {
    @Autowired
    private JournalRepository journalRepository;
    

    
}

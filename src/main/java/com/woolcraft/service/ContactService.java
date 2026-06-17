package com.woolcraft.service;

import com.woolcraft.entity.ContactMessage;
import com.woolcraft.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;

    @Transactional
    public ContactMessage save(String name, String email, String phone, String message) {
        return contactRepository.save(ContactMessage.builder().name(name).email(email).phone(phone).message(message).createdAt(java.time.LocalDateTime.now()).build());
    }

    public List<ContactMessage> findAll() { return contactRepository.findAllByOrderByCreatedAtDesc(); }
    public ContactMessage findById(Long id) { return contactRepository.findById(id).orElse(null); }

    @Transactional
    public void reply(Long id, String reply) {
        ContactMessage cm = contactRepository.findById(id).orElseThrow();
        cm.setReply(reply);
        contactRepository.save(cm);
    }

    public long count() { return contactRepository.count(); }
}

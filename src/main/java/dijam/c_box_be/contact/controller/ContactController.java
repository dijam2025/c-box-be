package dijam.c_box_be.contact.controller;

import dijam.c_box_be.contact.dto.ContactRequest;
import dijam.c_box_be.contact.entity.Contact;
import dijam.c_box_be.contact.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:8080")
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    public String sendContact(@RequestBody ContactRequest request) {
        Contact contact = Contact.builder()
                .userId(request.getUserId())
                .email(request.getEmail())
                .contactText(request.getContactText())
                .build();

        contactRepository.save(contact);
        return "문의가 접수되었습니다.";
    }
    @GetMapping("/contact")
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }
}

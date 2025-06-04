package dijam.c_box_be.contact.repository;

import dijam.c_box_be.contact.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {
}

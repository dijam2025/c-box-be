package dijam.c_box_be.contact.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@Getter
@Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contactId;

    private String userId;
    private String email;
    @Column(columnDefinition = "TEXT")
    private String contactText;

    private LocalDateTime createdAt = LocalDateTime.now();
}

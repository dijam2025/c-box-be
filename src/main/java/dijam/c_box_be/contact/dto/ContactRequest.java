package dijam.c_box_be.contact.dto;


import lombok.*;

@Getter @Setter
public class ContactRequest {
    private String userId;
    private String email;
    private String contactText;
}

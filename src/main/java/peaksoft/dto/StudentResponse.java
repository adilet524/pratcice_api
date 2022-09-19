package peaksoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private boolean isDeleted;
    private boolean isActive;
}

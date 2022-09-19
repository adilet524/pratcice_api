package peaksoft.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "students")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    @Id
    @GeneratedValue( generator = "student_gen", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "student_gen",
            sequenceName = "student_seq",
            allocationSize = 1)

    private Long id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private boolean isDeleted = false;
    private boolean isActive = true;
    @CreatedDate
    private LocalDateTime created;
}

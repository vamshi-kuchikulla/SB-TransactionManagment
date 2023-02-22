package org.cst.TransactionManagment.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Entity
@Table(name = "student_tbl")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int studentId;
    private int rollNo;
    private String fullName;
    private String email;
    private long mobileNumber;
    @OneToMany( targetEntity = Address.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "sa_fk", referencedColumnName = "studentId")
    private List<Address> address;

    public Student(int rollNo, String fullName, String email, long mobileNumber, List<Address> address) {
        this.rollNo = rollNo;
        this.fullName = fullName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.address = address;
    }
}

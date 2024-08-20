package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COMPANYBRANCHES")
public class CompanyBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRANCHID")
    private Long branchID;

    @Column(name = "BRANCHNAME")
    private String branchName;

    @Column(name = "BRANCHADDRESS")
    private String brachAddress;

    @Column(name = "BRANCHPHONE")
    private String branchPhone;

    @Column(name = "BRANCHMANAGER")
    private String branchManager;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "COMPANYID")
    private Company company;
}

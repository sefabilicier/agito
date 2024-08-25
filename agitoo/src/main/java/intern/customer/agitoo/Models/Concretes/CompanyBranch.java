package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COMPANYBRANCHES")
@Builder
public class CompanyBranch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BRANCHID")
    private Long branchID;

    @Column(name = "BRANCHNAME")
    private String branchName;

    @Column(name = "BRANCHADDRESS")
    private String branchAddress;

    @Column(name = "BRANCHPHONE")
    private String branchPhone;

    @Column(name = "BRANCHMANAGER")
    private String branchManager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANYID", nullable = false)
    private Company company;
}

package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "COMPANIES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANYID")
    private Long companyid;

    @Column(name = "COMPANYNAME")
    private String companyname;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "REGISTRATIONNUMBER")
    private Integer registrationnumber;

    @Column(name = "TAXIDENTIFICATIONNUMBER")
    private Integer taxidentificationnumber;

    @Column(name = "CONTACTCOMPANY")
    private String contactcompany;

    @Column(name = "ESTABLISHEDDATE")
    private Date establisheddate;

    @Column(name = "NUMBEROFEMPLOYEES")
    private Integer numberofemployees;

    @Column(name = "REVENUE")
    private float revenue;

    @Column(name = "WEBSITEURL")
    private String websiteurl;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMERID")
    private Customer customer;


    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    List<CompanyBranch> companyBranches;

    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    List<CompanyFinancial> companyFinancials;
}

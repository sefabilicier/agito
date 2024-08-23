package intern.customer.agitoo.Models.Concretes;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
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
    private Long companyId;

    @Column(name = "COMPANYNAME")
    private String companyName;

    @Column(name = "INDUSTRY")
    private String industry;

    @Column(name = "REGISTRATIONNUMBER", unique = true)
    private String registrationNumber;

    @Column(name = "TAXIDENTIFICATIONNUMBER", unique = true)
    private String taxIdentificationNumber;

    @Column(name = "CONTACTCOMPANY")
    private String contactCompany;

    @Column(name = "ESTABLISHEDDATE")
    private Date establishedDate;

    @Column(name = "NUMBEROFEMPLOYEES")
    private Integer numberOfEmployees;

    @Column(name = "REVENUE", precision = 15, scale = 2)
    private BigDecimal revenue;

    @Column(name = "WEBSITEURL")
    private String websiteURL;

    //foreign key
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMERID",
            insertable = false,
            updatable = false,
            nullable = false)
    private Customer customer;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompanyBranch> companyBranches;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CompanyFinancial> companyFinancials;
}

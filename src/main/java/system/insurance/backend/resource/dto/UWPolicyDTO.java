package system.insurance.backend.resource.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import system.insurance.backend.insurance.InsuranceType;

import java.util.Date;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UWPolicyDTO {

    private final int id;
    private final String name;
    private final Date date;
    private final String physicalPolicy;
    private final String environmentalPolicy;
    private final String financialPolicy;


    @Builder
    public UWPolicyDTO(int id, String name, Date date, String physicalPolicy, String environmentalPolicy,String financialPolicy) {
        this.id = id;
        this.name=name;
        this.date=date;
        this.physicalPolicy=physicalPolicy;
        this.environmentalPolicy=environmentalPolicy;
        this.financialPolicy=financialPolicy;
    }

}
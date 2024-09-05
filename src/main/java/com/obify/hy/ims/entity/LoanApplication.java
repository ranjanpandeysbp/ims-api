package com.obify.hy.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loan_application")
public class LoanApplication {
    @Id
    private String id;
    private String merchantId;
    private Double loanAmountRequested;
    private EStatus eLoanStatus;
    private List<String> comments;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
    private ECurrency currency;
}

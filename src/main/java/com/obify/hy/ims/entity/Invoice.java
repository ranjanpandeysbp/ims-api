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
@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;
    private String name;
    private String description;
    private List<String> sales;
    private Double invoiceAmount;
    private EStatus status;
    private ECurrency currency;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}

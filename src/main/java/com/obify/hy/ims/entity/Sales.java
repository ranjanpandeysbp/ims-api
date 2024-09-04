package com.obify.hy.ims.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sales")
public class Sales {
    @Id
    private String id;
    private Integer quantity;
    private String productId;
    private String merchantId;
    private String buyerName;
    private String buyerAddress;
    private String buyerEmail;
    private String buyerPhone;
    private LocalDateTime createdDateTime;
    private LocalDateTime updatedDateTime;
}

package com.tomattman.javapersistence.springDataJpa.model;

import com.tomattman.javapersistence.springDataJpa.model.converter.MonetaryAmountConverter;
import jakarta.persistence.*;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.*;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Item {
    @Id
    @GeneratedValue
    private Long id;

    @Access(AccessType.PROPERTY) //Access by getter setter
    @Column(name = "item_name")
    private String name;
    @Column(name = "description")
    private String description;

    @Basic(optional = false) //@Column(nullable = "false")
    @Column(insertable = false)
    @ColumnDefault("1.00")
    @Generated(GenerationTime.INSERT)
    private BigDecimal initialPrice;

    public void setName(String name) {
        this.name = name.startsWith("AUCTION: ") ? name : "AUCTION: " + name;
    }

    @Formula("CONCAT(SUBSTR(description, 1, 12), '...'")
    private String shortDescription;

    @Formula(
          "(SELECT AVG(b.amount) FROM bid b WHERE b.item_id = id)"
    )
    private BigDecimal averageBidAmount;
    @Column(name = "imperial_weight")
    @ColumnTransformer(
            read = "imperial_weight / 2.20462",
            write = "? * 2.20462"
    )
    private double metricWeight;

    @CreationTimestamp
    private LocalDate createdOn;
    @UpdateTimestamp
    private LocalDateTime lastModified;

    @Enumerated(EnumType.STRING)
    private AuctionType auctionType = AuctionType.HIGHEST_BID;

    @Lob
    private byte[] image;

    @Convert(converter = MonetaryAmountConverter.class)
    @Column(name = "price", length = 63)
    private MonetaryAmount buyNowPrice;
}

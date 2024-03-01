package com.laguna.project18.WallapopGratuito.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
public class TagCounterKey implements Serializable {
    @Column(name = "user_id")
    Long userId;

    @Column(name = "tag_id")
    Long tagId;
}

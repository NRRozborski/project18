package com.laguna.project18.WallapopGratuito.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TagCounterKey implements Serializable {
    @Column(name = "user_id")
    private Long studentId;

    @Column(name = "tag_id")
    private Long tagId;
}

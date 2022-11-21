package pl.jablonski.jooqpj.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private final UUID uuid = UUID.randomUUID();

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (!(o instanceof final BaseEntity entityBase)) return false;
        return Objects.equals(uuid, entityBase.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uuid);
    }
}

package Core.Models;

import java.util.UUID;

public class BaseEntity {
    private final UUID id;

    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    public BaseEntity(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}

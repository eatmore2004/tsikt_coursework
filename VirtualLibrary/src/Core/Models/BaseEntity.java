package Core.Models;

import java.util.UUID;

/**
 * BaseEntity class
 * created by Andrii Yeremenko
 */
public class BaseEntity {

    private final UUID id;

    /**
     * Constructor. On creation generates random UUID
     */
    public BaseEntity() {
        this.id = UUID.randomUUID();
    }

    /**
     * Constructor. On creation uses passed UUID
     * @param id
     */
    public BaseEntity(UUID id) {
        this.id = id;
    }

    /**
     * Getter for id
     * @return UUID
     */
    public UUID getId() {
        return id;
    }
}

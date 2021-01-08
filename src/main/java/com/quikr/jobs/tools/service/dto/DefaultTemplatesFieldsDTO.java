package com.quikr.jobs.tools.service.dto;

import javax.validation.constraints.*;
import java.io.Serializable;
import com.quikr.jobs.tools.domain.enumeration.DefaultFieldType;

/**
 * A DTO for the {@link com.quikr.jobs.tools.domain.DefaultTemplatesFields} entity.
 */
public class DefaultTemplatesFieldsDTO implements Serializable {
    
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private DefaultFieldType type;

    private String displayName;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefaultFieldType getType() {
        return type;
    }

    public void setType(DefaultFieldType type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultTemplatesFieldsDTO)) {
            return false;
        }

        return id != null && id.equals(((DefaultTemplatesFieldsDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefaultTemplatesFieldsDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            "}";
    }
}

package com.quikr.jobs.tools.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.quikr.jobs.tools.domain.enumeration.DefaultFieldType;

/**
 * A DefaultTemplatesFields.
 */
@Entity
@Table(name = "default_templates_fields")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class DefaultTemplatesFields implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private DefaultFieldType type;

    @Column(name = "display_name")
    private String displayName;

    @ManyToMany(mappedBy = "defaultTemplatesFields")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @JsonIgnore
    private Set<DefaultTemplates> defaultTemplates = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public DefaultTemplatesFields name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DefaultFieldType getType() {
        return type;
    }

    public DefaultTemplatesFields type(DefaultFieldType type) {
        this.type = type;
        return this;
    }

    public void setType(DefaultFieldType type) {
        this.type = type;
    }

    public String getDisplayName() {
        return displayName;
    }

    public DefaultTemplatesFields displayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<DefaultTemplates> getDefaultTemplates() {
        return defaultTemplates;
    }

    public DefaultTemplatesFields defaultTemplates(Set<DefaultTemplates> defaultTemplates) {
        this.defaultTemplates = defaultTemplates;
        return this;
    }

    public DefaultTemplatesFields addDefaultTemplates(DefaultTemplates defaultTemplates) {
        this.defaultTemplates.add(defaultTemplates);
        defaultTemplates.getDefaultTemplatesFields().add(this);
        return this;
    }

    public DefaultTemplatesFields removeDefaultTemplates(DefaultTemplates defaultTemplates) {
        this.defaultTemplates.remove(defaultTemplates);
        defaultTemplates.getDefaultTemplatesFields().remove(this);
        return this;
    }

    public void setDefaultTemplates(Set<DefaultTemplates> defaultTemplates) {
        this.defaultTemplates = defaultTemplates;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultTemplatesFields)) {
            return false;
        }
        return id != null && id.equals(((DefaultTemplatesFields) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefaultTemplatesFields{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +
            ", displayName='" + getDisplayName() + "'" +
            "}";
    }
}

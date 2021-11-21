package com.proiectcolectiv.proiect.entities;

import javax.persistence.*;

@Entity
@Table(name = "technology")
public class Technology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "technology", nullable = false)
    private String technology;

    public Long getId() {
        return id;
    }

    public String getTechnology() {
        return technology;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTechnology(String technology) {
        this.technology = technology;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Technology that = (Technology) o;

        if (!id.equals(that.id)) return false;
        if (technology != null ? !technology.equals(that.technology) : that.technology != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (technology != null ? technology.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Technology{" +
                "id=" + id +
                ", technology='" + technology.substring(0,50) +
                '}';
    }
}

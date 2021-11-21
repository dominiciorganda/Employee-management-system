package com.proiectcolectiv.proiect.entities;

import javax.persistence.*;

@Entity
@Table(name = "regions")
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;
    @Column(name = "region", nullable = false)
    private String region;

    public Long getId() {
        return id;
    }

    public String getRegion() {
        return region;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Region that = (Region) o;

        if (!id.equals(that.id)) return false;
        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        return true;
    }

    @Override
    public int hashCode() {
        int result = id.intValue();
        result = 31 * result + (region != null ? region.hashCode() : 0);
        return result;
    }
    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", region='" + region.substring(0,50) +
                '}';
    }

}

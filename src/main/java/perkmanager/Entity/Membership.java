package perkmanager.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Perk> perkList;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Person> personList;
    private String name;
    private String imagePath;

    public void addPerk(Perk perk) {
        this.perkList.add(perk);
    }

    public Perk createPerk(String name, String description) {
        Perk perk = new Perk();
        perk.setPerkName(name);
        perk.setPerkDescription(description);
        addPerk(perk);
        return perk;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Membership: ").append(name).append("\n");
        sb.append("perkList=").append(perkList);
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Membership that = (Membership) o;
        return Objects.equals(name, that.name);
    }

    // Override hashCode() method to generate a hash code for Membership objects
    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

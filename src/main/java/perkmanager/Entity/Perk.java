package perkmanager.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
public class Perk {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String locations;

    private String perkDescription;
    private String perkName;

    private int useful;

    private String expirationDate;

    private String times;
    private int expirationYear;

    private int expirationMonth;

    private int expirationDay;

    private String product;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private Membership membership;


    public void upvote(int count) {
        useful += count;
    }

    public void downvote(int count) {
        useful -= count;
    }

    public void setExpirationDate(String date) {
        expirationDate = date;
        String[] dateArray = date.split("-");
        expirationYear = Integer.parseInt(dateArray[0]);
        expirationMonth = Integer.parseInt(dateArray[1]);
        expirationDay = Integer.parseInt(dateArray[2]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(perkName).append('\n');
        sb.append(perkDescription).append('\n');
        sb.append(expirationDate);
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
        Perk that = (Perk) o;
        return Objects.equals(perkName, that.perkName) && Objects.equals(perkDescription, that.perkDescription);
    }

    // Override hashCode() method to generate a hash code for Membership objects
    @Override
    public int hashCode() {
        return Objects.hash(perkName, perkDescription);
    }

}

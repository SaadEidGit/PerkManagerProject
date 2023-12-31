package perkmanager.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import perkmanager.Service.MembershipService;

import java.util.ArrayList;
import java.util.List;


/**
 *  This class represents the Users in the Perk Manager system.
 *
 * @author Santhosh Pradeepan
 */
@Entity
@Data
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private List<Membership> membershipList;
    private boolean signedIn; //To be used in a future iteration.
    private String username;
    private String password;

    public Person() {
        this.membershipList = new ArrayList<>();
        this.signedIn = false;
    }

    public void setPassword(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        this.password = encoder.encode(password);
    }

    /**
     * Function used to create a profile for the user, which includes all the memberships.
     * This function also gets a list of all the available perks associated with the profile.
     *
     * @param memberships The list of all memberships
     */
    public void createProfile(ArrayList<String> memberships) {
        for (String membership : memberships) {
            addMembership(membership);
        }
        signedIn = true;
    }

    /**
     * Function used to add a membership to the user profile.
     *
     * @param membership The membership to be added
     */
    public void addMembership(Membership membership) {
        if (membership != null) {
            if (!membershipList.contains(membership)) {
                membershipList.add(membership);
            }
        }
    }

    public void removeMembership(Membership membership) {
        if (membership != null) {
            membershipList.remove(membership);
        }
    }

    /**
     * Function used to add a membership to the user profile.
     *
     * @param name The name of the membership to be added
     */
    public void addMembership(String name) {
        MembershipService membershipService = new MembershipService();
        Membership tempMembership = membershipService.findByName(name);
        addMembership(tempMembership);
    }

    public String toString() {
        String temp = String.format(
                "User[id=%d]",
                id);
        for (Membership membership: membershipList) {
            temp += "\n";
            temp += membership.toString();
        }
        return temp;
    }
}

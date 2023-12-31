package perkmanager.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import perkmanager.Entity.Membership;
import perkmanager.Entity.Person;
import perkmanager.Repository.MembershipRepository;
import perkmanager.Service.MembershipService;
import perkmanager.Repository.PerkRepository;
import perkmanager.Repository.PersonRepository;

import java.util.List;

@Controller
public class MembershipController {

    @Autowired
    PersonRepository personRepository;

    @Autowired
    MembershipService membershipService;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    PerkRepository perkRepository;

    @GetMapping("/myMemberships-content")
    public String allMyMembershipsContent(Model model) {
        List<Membership> memberships = personRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).getMembershipList();
        List<Membership> allMemberships = membershipRepository.findAll();
        model.addAttribute("memberships", memberships);
        model.addAttribute("allMemberships", allMemberships);
        return "allMemberships :: content";
    }

    @PostMapping("/removeMembership/{id}")
    @ResponseBody
    public void removeMembership(@PathVariable("id") Long membershipId) {
        Person person = personRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Membership membership = membershipService.findById(membershipId);
        person.removeMembership(membership);
        personRepository.save(person);
        //membershipService.deleteById(membershipId);
    }

    @PostMapping("/addMembership/{id}")
    @ResponseBody
    public void addMembership(@PathVariable("id") Long membershipId) {
        Person person = personRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        Membership membership = membershipService.findById(membershipId);
        person.addMembership(membership);
        personRepository.save(person);
    }

}

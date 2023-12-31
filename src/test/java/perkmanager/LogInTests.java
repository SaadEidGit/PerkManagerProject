package perkmanager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LogInTests {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser
    public void testLoginPage() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser
    public void testSuccessfulLogin() throws Exception {
        mockMvc.perform(post("/login")
                        .param("username", "mutaz")
                        .param("password", "mutaz"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/LandingPage")); // Change this to the URL you expect after successful login
    }
}

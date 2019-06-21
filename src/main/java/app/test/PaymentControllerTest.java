package app.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import app.controller.PaymentController;
import app.datastore.PaymentRepository;
import app.model.Payment;
import app.service.PaymentService;

@RunWith(SpringRunner.class)
@WebMvcTest(PaymentController.class)
public class PaymentControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private PaymentRepository paymentRepository;
    @MockBean private PaymentService paymentService;
    @Autowired private PaymentController controller;
    private JacksonTester < Payment > jsonTester;
    private Payment payment;
    
    @Before
    public void setup() {
        JacksonTester.initFields(this, objectMapper);
        payment = new Payment();
    }
    
    @Test
    public void contexLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
    
    @Test
    public void addPayment_IsValid() throws Exception {
        final String paymentJson = jsonTester.write(payment).getJson();
        given(paymentService.isPaymentValid(any(Payment.class))).willReturn(true);
        mockMvc
            .perform(post("/app/addPayment").content(paymentJson).contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());
    }
    
    @Test
    public void addPayment_IsNotValid() throws Exception {
        final String paymentJson = jsonTester.write(payment).getJson();
        given(paymentService.isPaymentValid(any(Payment.class))).willReturn(false);
        mockMvc
            .perform(post("/app/addPayment").content(paymentJson).contentType(APPLICATION_JSON_UTF8))
            .andExpect(status().isBadRequest());
    }	
	
}
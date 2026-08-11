package org.ScrumLords.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentControllerTest {

    private PaymentController paymentController;

    @BeforeEach
    void setUp() {
        paymentController = new PaymentController();
    }

    @Test
    void calculateTicketTotalTwoTickets() {
        double result = paymentController.calculateTicketTotal(2);

        assertEquals(24.00, result, 0.001);
    }

    @Test
    void calculateTicketTotalZeroTickets() {
        double result = paymentController.calculateTicketTotal(0);

        assertEquals(0.00, result, 0.001);
    }

    @Test
    void validatePaymentBlankCardholderName() {
        String result = paymentController.validatePayment(
                "",
                "1234567890123456",
                "123",
                "12/28",
                "93063"
        );

        assertEquals(
                "Please enter the cardholder's name.",
                result
        );
    }

    @Test
    void validatePaymentInvalidCardNumber() {
        String result = paymentController.validatePayment(
                "Andrew Garcia",
                "1234",
                "123",
                "12/28",
                "93063"
        );

        assertEquals(
                "Please enter 16 digits for the card number with no spaces or '-'.",
                result
        );
    }

    @Test
    void validatePaymentInvalidCvv() {
        String result = paymentController.validatePayment(
                "Andrew Garcia",
                "1234567890123456",
                "12",
                "12/28",
                "93063"
        );

        assertEquals(
                "Please enter 3 digits for the CVV number.",
                result
        );
    }

    @Test
    void validatePaymentInvalidExpiration() {
        String result = paymentController.validatePayment(
                "Andrew Garcia",
                "1234567890123456",
                "123",
                "1228",
                "93063"
        );

        assertEquals(
                "Please enter the expiration date in MM/YY format.",
                result
        );
    }

    @Test
    void validatePaymentInvalidZipCode() {
        String result = paymentController.validatePayment(
                "Andrew Garcia",
                "1234567890123456",
                "123",
                "12/28",
                "9306"
        );

        assertEquals(
                "Please enter 5 digits for the ZIP code.",
                result
        );
    }

    @Test
    void validatePaymentValidPayment() {
        String result = paymentController.validatePayment(
                "Andrew Garcia",
                "1234567890123456",
                "123",
                "12/28",
                "93063"
        );

        assertNull(result);
    }
}
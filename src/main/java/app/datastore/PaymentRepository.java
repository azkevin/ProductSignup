package app.datastore;

import org.springframework.data.repository.CrudRepository;

import app.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, String> {

}

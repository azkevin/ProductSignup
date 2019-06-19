package app.datastore;

import org.springframework.data.repository.CrudRepository;

import app.model.Payment;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface PaymentRepository extends CrudRepository<Payment, String> {

}

package service;

import model.Customer;
import java.util.ArrayList;
import java.util.Collection;

public class CustomerService {
    private Collection<Customer> customers;
    private static CustomerService customerService = new CustomerService();

    //constructor
    private CustomerService(){
        customers = new ArrayList<>();
    }

    public static CustomerService getInstance(){
        return customerService;
    }

    public void addCustomer(String email, String firstName, String lastName){
        Customer customer = new Customer(email, firstName, lastName);
        customers.add(customer);
    }

    public Customer getCustomer(String customerEmail){
        Customer c = new Customer(customerEmail);
        c.validateEmail(customerEmail);
        for(Customer customer: customers){
            if(customer.getEmail().equals(customerEmail)){
                return customer;
            }
        }
        return null;
    }


    public void validateEmail(){

    }


    public void getAllCustomers() {
        for(Customer c: customers){
            System.out.println(c.toString());
        }
    }
}

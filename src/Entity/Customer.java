package src.Entity;

public class Customer {
    private int customer_id;
    private String customer_name;
    private String email;
    private String address;

    public Customer(int customer_id, String customer_name, String email, String address) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.email = email;
        this.address = address;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" + "customer_id=" + customer_id + ", email='" + email + '\'' + ", " +
                "address='" + address + '\'' + ", customer_name='" + customer_name + '\'' + '}';
    }
}
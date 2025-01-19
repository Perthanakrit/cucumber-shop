package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private boolean purchaseFailed;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        purchaseFailed = false;
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
   public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        Product prod = catalog.getProduct(name);

        try {
            order.addItem(prod, quantity);
            purchaseFailed = false;
        }
        catch (IllegalArgumentException ignored) {
            purchaseFailed = true;
        }
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

//   @When(" I buy {string} with quantity {int}")
//   public void i_buy_with_quantity(String name, String quantity) {}

    @Then("the purchase should fail")
    public void the_purchase_should_fail() {
        assertTrue(purchaseFailed);
    }

    @Then("the stock of {string} should remain {int}")
    public void the_stock_of_should_remain(String name,  int stock) {
        // Write code here that turns the phrase above into concrete actions
        Product prod = catalog.getProduct(name);

        assertEquals(stock, prod.getStock());
    }
}


package com.example.android.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary(5);
        displayMessage(priceMessage);
    }

    public void increment(View view) {

        quantity = quantity + 1;
        display(quantity);
    }

    public void decrement(View view) {

        quantity = quantity - 1;
        display(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given price on the screen.
     */
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        return price;
    }

    private String createOrderSummary(int pricePerOrder) {
        String priceMessage = "Name= Kaptain Kunal";
        priceMessage += "\nQuantity= " + quantity;
        priceMessage += "\nAdd Whipped Cream= " + hasWhippedCream();
        priceMessage += "\nTotal= " + (quantity * pricePerOrder);
        priceMessage += "\nThank you!";
        return priceMessage;

    }

    private Boolean hasWhippedCream()
    {
        CheckBox hasWhipppedCream=(CheckBox) findViewById(R.id.checkBox);
        Boolean checkWhipped= hasWhipppedCream.isChecked();
        return checkWhipped;
    }
}
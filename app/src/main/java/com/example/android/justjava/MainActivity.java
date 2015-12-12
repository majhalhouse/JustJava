package com.example.android.justjava;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
        if (quantity > 100) {
            quantity = 100;
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT).show();
        }
        display(quantity);
    }

    public void decrement(View view) {

        quantity = quantity - 1;
        if (quantity < 1) {
            quantity = 1;
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT).show();
        }
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
//        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
//        orderSummaryTextView.setText(message);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc822");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + this.getName());
        intent.putExtra(Intent.EXTRA_TEXT, message);

        startActivity(Intent.createChooser(intent, "Send Email"));
    }

    /**
     * Calculates the price of the order.
     *
     * @param quantity is the number of cups of coffee ordered
     */
    private int calculatePrice(int quantity) {
        int price = quantity * 5;
        if (hasWhippedCream()) {
            price = price + (quantity);
        }
        if (hasChocolate()) {
            price = price + (2 * quantity);
        }
        Log.v("Price :", String.valueOf(price));
        return price;
    }

    private String createOrderSummary(int pricePerOrder) {
        String priceMessage = "Name=" + getName();
        priceMessage += "\nQuantity= " + quantity;
        priceMessage += "\nAdd Whipped Cream " + hasWhippedCream();
        priceMessage += "\nAdd Chocolate " + hasChocolate();
        priceMessage += "\nTotal= " + (calculatePrice(quantity));
        priceMessage += "\nThank you!";
        return priceMessage;


    }

    private Boolean hasWhippedCream()
    {
        CheckBox hasWhipppedCream=(CheckBox) findViewById(R.id.checkBox);
        return hasWhipppedCream.isChecked();
    }

    private Boolean hasChocolate() {
        CheckBox hasChocolate = (CheckBox) findViewById(R.id.checkBox_choc);
        return hasChocolate.isChecked();
    }

    private String getName() {
        EditText customerName = (EditText) findViewById(R.id.name_Editable);
        //Log.i("test",customer);
        return customerName.getText().toString();
    }
}
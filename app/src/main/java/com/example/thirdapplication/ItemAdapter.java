// ItemAdapter.java
package com.example.thirdapplication;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
public class ItemAdapter extends BaseAdapter {
    private Context context;
    private List<Item> itemList;

    public ItemAdapter(Context context, List<Item> itemList) {
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public int getCount() {
        return itemList.size();
    }

    @Override
    public Object getItem(int position) {
        return itemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public double calculateTotalPrice() {
        double total = 0;

        for (Item item : itemList) {
            total += item.getQuantity() * Double.parseDouble(item.getItemPrice());
        }

        return total;
    }

    public void updateTotalPrice(Button totalPriceButton) {
        double totalPrice = calculateTotalPrice();
        totalPriceButton.setText(String.format("%.2f DZD", totalPrice));
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.cart_layout, parent, false);
        }

        final Item currentItem = (Item) getItem(position);

        ImageView itemImageView = convertView.findViewById(R.id.itemImageView);
        TextView itemNameTextView = convertView.findViewById(R.id.itemNameTextView);
        TextView itemDescriptionTextView = convertView.findViewById(R.id.itemDescriptionTextView);
        TextView itemPriceTextView = convertView.findViewById(R.id.itemPriceTextView);
        TextView itemPriceQuantity = convertView.findViewById(R.id.itemPriceQuantityTextView);
        final TextView quantityTextView = convertView.findViewById(R.id.quantityTextView);
        Button plusButton = convertView.findViewById(R.id.plusButton);
        Button minusButton = convertView.findViewById(R.id.minusButton);
        final ImageView addItemToCartImageView = convertView.findViewById(R.id.addItemToCard); // Get the ImageView

        itemImageView.setImageResource(currentItem.getItemImageResourceId());
        itemNameTextView.setText(currentItem.getItemName());
        itemDescriptionTextView.setText(currentItem.getItemDescription());
        itemPriceTextView.setText(currentItem.getItemPrice() + " DZD");
        quantityTextView.setText(String.valueOf(currentItem.getQuantity()));
        itemPriceQuantity.setText(String.valueOf(currentItem.getQuantity()) + " x " + currentItem.getItemPrice() + " DZD");

        // Set click listener for the plus button
        plusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = currentItem.getQuantity() + 1;
                  if (newQuantity == 1) {
                      addItemToCartImageView.setImageResource(R.drawable.ic_outline_remove_shopping_cart_24);
                      currentItem.setIconAdded(true);
                }
                currentItem.setQuantity(newQuantity);
                notifyDataSetChanged();
            }
        });

        // Set click listener for the minus button
        minusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int newQuantity = currentItem.getQuantity() - 1;
                if (newQuantity >= 0) {
                    currentItem.setQuantity(newQuantity);
                    if (newQuantity == 0) {
                    addItemToCartImageView.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24);
                    currentItem.setIconAdded(false);
                    }
                    notifyDataSetChanged();
                }
            }
        });


        // Set an OnClickListener for the ImageView
        addItemToCartImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isIconAdded = currentItem.isIconAdded(); // Get the current state of the icon

                // Toggle the icon based on the current state
                if (isIconAdded) {
                    addItemToCartImageView.setImageResource(R.drawable.ic_baseline_add_shopping_cart_24); // Set the first icon
                    currentItem.setQuantity(0);

                } else {
                    addItemToCartImageView.setImageResource(R.drawable.ic_outline_remove_shopping_cart_24);
                    if(currentItem.getQuantity() == 0){
                        currentItem.setQuantity(1);
                    }
                    // Set the second icon
                }

                // Update the state of the icon for the current item
                currentItem.setIconAdded(!isIconAdded); // Invert the icon state
                notifyDataSetChanged();
            }
        });

        final Button totalPriceButton = ((Activity) context).findViewById(R.id.totalPrice);
        updateTotalPrice(totalPriceButton);
        return convertView;
    }
}

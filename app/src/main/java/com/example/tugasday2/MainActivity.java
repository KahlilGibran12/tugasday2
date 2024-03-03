package com.example.tugasday2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    RadioGroup rgItem;
    EditText etTextPrice;
    RadioButton rbMember;
    Button btnProcess, btnClear;
    TextView tvNota;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgItem = findViewById(R.id.rgItem);
        etTextPrice = findViewById(R.id.etTextPrice);
        rbMember = findViewById(R.id.rbMember);
        btnProcess = findViewById(R.id.btnProcess);
        btnClear = findViewById(R.id.btnClear);
        tvNota = findViewById(R.id.tvNota);

        btnProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processTransaction();
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearFields();
            }
        });
    }


    private void processTransaction() {
        int selectedItemId = rgItem.getCheckedRadioButtonId();
        if (selectedItemId == -1) {
            Toast.makeText(this, "Pilih barang terlebih dahulu", Toast.LENGTH_SHORT).show();
            return;
        }

        RadioButton selectedItem = findViewById(selectedItemId);
        String itemName = selectedItem.getText().toString();
        double itemPrice = Double.parseDouble(etTextPrice.getText().toString());

        double adminFee = 0;
        double discountPercentage = 0;
        double discountAmount = 0;

        switch (itemName) {
            case "Dana":
                adminFee = 2000;
                break;
            case "Shoopepay":
                adminFee = 2500;
                break;
            case "Gopay":
                adminFee = 3000;
                break;
            // tambahkan case untuk barang lain jika diperlukan
        }

        double totalAmount = itemPrice + adminFee;
        double discount = 0;
        if (rbMember.isChecked()) {
            discountPercentage = 0.05; // Misalnya, member mendapatkan diskon 5%
            discountAmount = totalAmount * discountPercentage;
        }

        double finalAmount = totalAmount - discountAmount;

        String nota =
                "Nama Pengisian saldo: " + itemName + "\n" +
        "Total Harga Barang: " + itemPrice + "\n" +
                "Biaya Admin: " + adminFee + "\n" +
                "Persentase Diskon: " + (discountPercentage * 100) + "%" + "\n" +
                "Potongan Harga dari Diskon: " + discountAmount + "\n" +
                "Total Bayar: " + finalAmount;

        tvNota.setText(nota);
        tvNota.setVisibility(View.VISIBLE);
    }

    private void clearFields() {
        etTextPrice.setText("");
        rgItem.clearCheck();
        rbMember.setChecked(false);
        tvNota.setVisibility(View.GONE);
    }
}

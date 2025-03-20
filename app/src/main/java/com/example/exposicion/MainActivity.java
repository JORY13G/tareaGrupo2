package com.example.exposicion;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Términos aceptados" : "Términos no aceptados";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        });

        Switch switchDarkMode = findViewById(R.id.switchDarkMode);
        View rootLayout = findViewById(android.R.id.content);

        switchDarkMode.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                rootLayout.setBackgroundColor(Color.DKGRAY);
                Toast.makeText(this, "Modo oscuro activado", Toast.LENGTH_SHORT).show();
            } else {
                rootLayout.setBackgroundColor(Color.WHITE);
                Toast.makeText(this, "Modo claro activado", Toast.LENGTH_SHORT).show();
            }
        });

        GridView gridView = findViewById(R.id.gridView);
        List<String> items = Arrays.asList("🏡", "📞", "📅", "📷", "🔔", "⚙️", "🔑", "💾", "🔍");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items) {
            @Override
            public View getView(int position, View convertView, android.view.ViewGroup parent) {
                TextView view = (TextView) super.getView(position, convertView, parent);
                view.setTextSize(24f);
                view.setTextColor(Color.BLACK);
                view.setPadding(20, 20, 20, 20);
                return view;
            }
        };

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) ->
                Toast.makeText(this, "Seleccionaste: " + items.get(position), Toast.LENGTH_SHORT).show()
        );
    }
}

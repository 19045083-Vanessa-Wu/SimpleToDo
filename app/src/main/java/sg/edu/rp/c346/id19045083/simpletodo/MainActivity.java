package sg.edu.rp.c346.id19045083.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etInput = findViewById(R.id.editTextToDo);
        Button btnAdd = findViewById(R.id.buttonAdd);
        Button btnClear = findViewById(R.id.buttonClear);
        Button btnDelete = findViewById(R.id.buttonDelete);
        ListView lvToDo = findViewById(R.id.listViewToDo);
        Spinner spnAddRemove = findViewById(R.id.spinner);

        ArrayList<String> alToDo = new ArrayList<>();
//        alToDo.add("Watch movie with Jenny");

        ArrayAdapter aaToDo = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, alToDo);
        lvToDo.setAdapter(aaToDo);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = etInput.getText().toString();
                if (!etInput.getText().toString().isEmpty()){
                    alToDo.add(input);
                }
                aaToDo.notifyDataSetChanged();
                etInput.setText("");
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alToDo.clear();
                aaToDo.notifyDataSetChanged();
            }
        });

        spnAddRemove.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        etInput.setHint(R.string.addHint);
                        etInput.setInputType(InputType.TYPE_CLASS_TEXT);
                        btnAdd.setEnabled(true);
                        btnDelete.setEnabled(false);
                        break;

                    case 1:
                        etInput.setHint(R.string.removeHint);
                        etInput.setInputType(InputType.TYPE_CLASS_NUMBER);
                        btnAdd.setEnabled(false);
                        btnDelete.setEnabled(true);

                        // Check the size of arrayList to enable/disable delete button
                        if (alToDo.size()==0){
                            Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                            btnDelete.setClickable(false);
                        } else {
                            btnDelete.setClickable(true);
                        }
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Check input cannot be empty
                if (etInput.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Input cannot be empty", Toast.LENGTH_SHORT).show();
                } else {
                    int input = Integer.parseInt(etInput.getText().toString());

                    // Check arrayList cannot be empty
                    if (alToDo.size()==0){
                        Toast.makeText(MainActivity.this, "You don't have any task to remove", Toast.LENGTH_LONG).show();
                        btnDelete.setClickable(false);
                    } else {
                        btnDelete.setClickable(true);

                        // Check input is within the size of the arrayList
                        if (input < alToDo.size()){
                            alToDo.remove(input);
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong index number", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                aaToDo.notifyDataSetChanged();
                etInput.setText("");
            }
        });
    }
}
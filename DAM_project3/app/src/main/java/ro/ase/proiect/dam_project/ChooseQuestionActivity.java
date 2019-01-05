package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ChooseQuestionActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    ListView listView;
    private AdapterView l;
    private View v;
    private int poz;
    private long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_question);

        initComponents();
    }

    private void initComponents(){
        listView=findViewById(R.id.question_listview);
        listView.setOnItemClickListener(this);

    }
    public  void onItemClick(AdapterView<?> l, View v, int poz, long id ){

        this.l = l;
        this.v = v;
        this.poz = poz;
        this.id = id;
        Intent intent=new Intent();
        intent.setClass(this, ChooseAnswerActivity.class);
        startActivity(intent);

    }
}

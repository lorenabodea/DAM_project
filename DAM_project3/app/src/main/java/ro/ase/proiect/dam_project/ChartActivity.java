package ro.ase.proiect.dam_project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ro.ase.proiect.dam_project.util.Chart;
import ro.ase.proiect.dam_project.util.Constants;
import ro.ase.proiect.dam_project.util.Contest;

public class ChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        Intent intent = getIntent();

        List<Contest> contests = intent != null
                && intent.hasExtra(Constants.CHART_CONTESTS_KEY) ?
                intent.<Contest>getParcelableArrayListExtra(Constants.CHART_CONTESTS_KEY) : null;

        setContentView(new Chart(getApplicationContext()));
 //               createSource(contests)));
    }


//    private Map<String, Long> createSource(List<Contest> contests) {
//        if (contests == null ||
//                contests.size() == 0) {
//            return null;
//        }
//
//        Map<String, Long> result = new HashMap<>();
//
//        for (Contest contest : contests) {
//            if (result.containsKey(contest.getName())) {
//                result.put(contest.getName(),
//                        result.get(contest.getName())
//                                + contest.getStudentsNumber());
//            } else {
//                result.put(contest.getName(),
//                        contest.getStudentsNumber());
//
//            }
//        }
//            return result;
//    }
}


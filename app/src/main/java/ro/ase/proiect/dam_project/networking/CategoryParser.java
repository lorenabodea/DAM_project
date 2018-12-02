package ro.ase.proiect.dam_project.networking;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CategoryParser {

    public static CategoryJson fromJson(String json) throws JSONException {

        if(json == null){
            return null;
        }

        JSONObject object = new JSONObject(json);

        List<ContestJson> softSkills = getContestListFromJson(object.getJSONArray("softSkills"));
        List<ContestJson> technoligies = getContestListFromJson(object.getJSONArray("technoligies"));
        List<ContestJson> fun = getContestListFromJson(object.getJSONArray("fun"));

        return new CategoryJson(softSkills, technoligies, fun);
    }

    public static ContestJson getContestFromJsonObject(JSONObject object) throws JSONException {

        if(object == null){
            return null;
        }

        String name = object.getString("name");
        String description = object.getString("description");
        JSONArray array = object.getJSONArray("questions");

        List<QuestionJson> questions = new ArrayList<>();

        if(array!=null){

            if(array != null){
                for(int i = 0; i < array.length(); i++){
                    JSONObject arrayObject = array.getJSONObject(i);

                    if(arrayObject != null){
                        String text = arrayObject.getString("text");
                        String timeLimit = arrayObject.getString("timeLimit");
                        List<AnswerJson> answers = getAnswerListFromJsonObject(object.getJSONArray("answers"));
                        questions.add(new QuestionJson(text, timeLimit, answers));
                    }
                }
            }

        }
        return new ContestJson(name, description, questions);
    }


    public static List<ContestJson> getContestListFromJson(JSONArray array) throws JSONException {

        if(array == null){
            return null;
        }

        List<ContestJson> contests = new ArrayList<>();
        for(int i=0; i<array.length(); i++){
            ContestJson contest = getContestFromJsonObject(array.getJSONObject(i));
            if(contest!=null)
            {
                contests.add(contest);
            }
        }
        return contests;
    }


    public static List<AnswerJson> getAnswerListFromJsonObject(JSONArray array) throws JSONException {

        if(array == null){
            return null;
        }

        List<AnswerJson> answers = new ArrayList<>();

        for(int i=0; i<array.length(); i++){
            AnswerJson answer = getAnswerFromJsonObject(array.getJSONObject(i));
            answers.add(answer);
        }

        return answers;
    }

    public static AnswerJson getAnswerFromJsonObject(JSONObject object) throws JSONException {

        if(object == null){
            return null;
        }

        String text = object.getString("text");
        Boolean correct = object.getBoolean("correct");
        String message = object.getString("message");

        return new AnswerJson(text, correct, message);

    }
}

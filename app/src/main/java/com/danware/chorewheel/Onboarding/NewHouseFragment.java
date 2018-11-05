package com.danware.chorewheel.Onboarding;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.danware.chorewheel.DataModels.Chore;
import com.danware.chorewheel.DataModels.House;
import com.danware.chorewheel.DataModels.User;
import com.danware.chorewheel.R;
import com.danware.chorewheel.utils.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.danware.chorewheel.utils.Utils.random;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewHouseFragment extends Fragment {

    private DatabaseReference mDatabase;


    public NewHouseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_house, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        final EditText house_et = view.findViewById(R.id.new_house_et);
        final EditText user_et = view.findViewById(R.id.new_user_et);
        Button startBtn = view.findViewById(R.id.new_house_btn);

        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (house_et.getText() == null) {
                    Snackbar snackBar = Snackbar.make(view, "Must enter a house name", Snackbar.LENGTH_SHORT);
                    snackBar.show();
                    return;
                }

                if (user_et.getText() == null) {
                    Snackbar snackBar = Snackbar.make(view, "Must enter a name", Snackbar.LENGTH_SHORT);
                    snackBar.show();
                    return;
                }

                String houseName = house_et.getText().toString();
                String userName = user_et.getText().toString();
                createNewHouse(houseName, createUser(userName));
            }
        });
    }

    void createNewUser(String name) {
        User user = new User();
        user.setId(random());
        user.setHouseId("");
        user.setName(name);
    }

    private String fetchInitialChoreList() {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getContext().getAssets().open("chore-wheel-initial-data.json")));

            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                Log.i(Utils.getTAG(this), "createNewHouse: " + line);
            }
        } catch (IOException e) {
            Log.i(Utils.getTAG(this), "createNewHouse: " + e.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.i(Utils.getTAG(this), "createNewHouse: " + e.getMessage());
                }
            }
        }

        String jsonData = builder.toString();
        Log.i(Utils.getTAG(this), "createNewHouse: " + jsonData);
        return jsonData;
    }



    List<Chore> setupChores() {

        Map<String, Chore> choreMap;
        List<Chore> Chores = new ArrayList<>();
        try {
            JSONObject choreList = new JSONObject(fetchInitialChoreList());
            JSONArray choreArray = choreList.getJSONArray("initialChoreSet");
            for (int i = 0; i < choreArray.length(); i++) {

                JSONObject choreItem = choreArray.getJSONObject(i);

                Chore chore = new Chore();
                chore.setId(choreItem.getString("id"));
                chore.setDescription(choreItem.getString("description"));
                chore.setCompleted(false);
                chore.setFrequency(choreItem.getString("frequency"));
                chore.setShared(choreItem.getBoolean("shared"));
                chore.setTitle(choreItem.getString("title"));
                Chores.add(chore);

//              TODO  add chore to shared and private list
//                choreMap = new HashMap<>();
//                choreMap.put(chore.getId(), chore);
//                DatabaseReference choreRef = mDatabase.child("House");
//                choreRef.setValue(chore);
            }



        } catch (JSONException e) {
            e.printStackTrace();
        }

        return Chores;
    }

    List<User> createUser(String name) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setName(name);
        user.setId(Utils.random());
        userList.add(user);
        mDatabase.child("Users").child(user.getId()).setValue(user);
        return userList;
    }

    void createNewHouse(String houseName, List<User> User) {


        House Home = new House();
        Home.setId(houseName);
        Home.setChores(setupChores());
        Home.setUser(User);
        mDatabase.child("Houses").child(Home.id).setValue(Home);
    }
}

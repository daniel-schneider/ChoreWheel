package com.danware.chorewheel.Onboarding;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.danware.chorewheel.DataModels.Chore;
import com.danware.chorewheel.MainActivity;
import com.danware.chorewheel.R;
import com.danware.chorewheel.utils.Constants;
import com.danware.chorewheel.utils.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class JoinHouseFragment extends Fragment {

    DatabaseReference mHouseDatabase;
    List<Chore> mChores = new ArrayList<>();

    public JoinHouseFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_join_house, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mHouseDatabase = FirebaseDatabase.getInstance().getReference("Houses");

        final EditText editText = view.findViewById(R.id.join_house_et);
        Button joinButton = view.findViewById(R.id.join_house_button);
        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!editText.getText().toString().isEmpty())
                {
//                    findHouse(editText.getText().toString());
                    SharedPreferences appPrefs = PreferenceManager
                            .getDefaultSharedPreferences(getActivity().getApplicationContext());
                    appPrefs.edit().putString(Constants.HOUSE_NAME_KEY, editText.getText().toString()).apply();

                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });
    }

    private void findHouse(String id) {
        Query houseQuery = mHouseDatabase.child(id);
        houseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while((iterator.hasNext())){
                    Chore chore = iterator.next().getValue(Chore.class);
                    mChores.add(chore);
                    Log.d(Utils.getTAG(this), chore.getDescription() + " found");

                }

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d(Utils.getTAG(this),"this item is not in the list");

            }
        });
    }
}

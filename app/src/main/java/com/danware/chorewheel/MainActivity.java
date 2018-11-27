package com.danware.chorewheel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.danware.chorewheel.DataModels.Chore;
import com.danware.chorewheel.DataModels.User;
import com.danware.chorewheel.utils.Constants;
import com.danware.chorewheel.utils.Utils;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ChoreFragment.OnFragmentInteractionListener {

    private ViewPager mPager;
    private ChorePagerAdapter mPagerAdapter;
    static List<String> mKeys = new ArrayList<>();
    public static List<User> mUsers = new ArrayList<>();
    public static List<Chore> mAllChores = new ArrayList<>();
    public static List<Chore> mDailyChores = new ArrayList<>();
    public static List<Chore> mWeeklyChores = new ArrayList<>();
    public static List<Chore> mMonthlyChores = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPager = findViewById(R.id.fragment_pager);
        mPagerAdapter = new ChorePagerAdapter(getSupportFragmentManager());

        mPager.setOffscreenPageLimit(2);
        mPager.setAdapter(mPagerAdapter);
        getUsers(this);
        getChoreData();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {
        Log.i("MainActivity", "fragment interaction");
    }

    public static List<User> getUsers(Context context) {
        mUsers.clear();
        String houseName = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.HOUSE_NAME_KEY, null);
        final DatabaseReference houseDatabase = FirebaseDatabase.getInstance().getReference("Houses");
        Query userQuery = houseDatabase.child(houseName).child("user");
        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child :
                        children) {
                    User user = child.getValue(User.class);
                    mUsers.add(user);
                    Log.i(Utils.getTAG(this), user.getName() + " added to list");

                }
                Log.i(Utils.getTAG(this), "mUsers size: " + mUsers.size());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        userQuery.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//                User maybe = dataSnapshot.getValue(User.class);
//                mUsers.add(maybe);
//                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
//                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
//                while((iterator.hasNext())){
//                    String key = iterator.next().getKey();
//                    Log.d(Utils.getTAG(this), key + " found");
//                    User user = dataSnapshot.getValue(User.class);
//                    mUsers.add(user);
//
//                }
//
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        return mUsers;
    }

    void getChoreData() {

        String houseName = PreferenceManager
                .getDefaultSharedPreferences(this).
                        getString(Constants.HOUSE_NAME_KEY, "");


//        mAllChores = Utils.findHouse(houseName);
//        findHouseChores(houseName);
//        getMonthlyChore();
//        getWeeklyChores();
//        getDailyChores();
    }

    void getMonthlyChore() {
        for (Chore chore : mAllChores) {
            if (chore.frequency.equals(Constants.MONTHLY)) {
                mMonthlyChores.add(chore);
            }
        }
    }

    void getWeeklyChores() {
        for (Chore chore : mAllChores) {
            if (chore.frequency.equals(Constants.WEEKLY)) {
                mWeeklyChores.add(chore);
            }
        }
    }

    void getDailyChores() {
        for (Chore chore : mAllChores) {
            if (chore.frequency.equals(Constants.DAILY)) {
                mDailyChores.add(chore);
            }
        }
    }

    Chore getRandomChore(List<Chore> chores) {
        Random random = new Random();
        int index = random.nextInt(chores.size());
        Chore randomChore = chores.get(index);
        return randomChore;
    }


}

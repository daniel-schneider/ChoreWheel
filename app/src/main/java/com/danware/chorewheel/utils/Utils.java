package com.danware.chorewheel.utils;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.danware.chorewheel.DataModels.Chore;
import com.danware.chorewheel.DataModels.User;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Utils {
    private static final int MAX_LENGTH = 10;
    static List<Chore> mChores = new ArrayList<>();
    static List<User> mUsers = new ArrayList<>();
    static List<String> mKeys = new ArrayList<>();


    public static String random() {
//        ArrayList<String> list = new ArrayList<>();
        Random rnd = new Random();
        String str = "";

        String randomLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String randomLetterSmall = "abcdefghijklmnopqrstuvwxyz";

        for (int n = 0; n < MAX_LENGTH; n++) {
            str = String.valueOf(randomLetters.charAt(rnd.nextInt(randomLetters.length())));

            str += String.valueOf(randomLetterSmall.charAt(rnd.nextInt(randomLetters.length())));
            str += String.valueOf(randomLetterSmall.charAt(rnd.nextInt(randomLetters.length())));
            str += String.valueOf(randomLetterSmall.charAt(rnd.nextInt(randomLetters.length())));
            str += String.valueOf(randomLetterSmall.charAt(rnd.nextInt(randomLetters.length())));

            //Copy above line to increase character of the String
//            list.add(str);
        }
//        Collections.sort(list);
        return str;
    }

    public static String getTAG(Object o) {
        StackTraceElement[] elements = Thread.currentThread().getStackTrace();

        int position = 0;
        for (int i = 0; i < elements.length; i++) {
            if (elements[i].getFileName().contains(o.getClass().getSimpleName())) {
                position = i;
                break;
            }
        }

        StackTraceElement element = elements[position];
        String className = element.getFileName().replace(".java", "");
        return "[" + className + "](" + element.getMethodName() + ":" + element.getLineNumber() + ")";
    }

    public static List<Chore> findHouse(String id) {
        DatabaseReference houseDatabase = FirebaseDatabase.getInstance().getReference("Houses");
        Query houseQuery = houseDatabase.child(id);
        houseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while ((iterator.hasNext())) {
                    Chore chore = iterator.next().getValue(Chore.class);
                    String key = dataSnapshot.getKey();

                    if (s == null) {
                        mChores.add(0, chore);
                        mKeys.add(0, key);
                    } else {
                        int previousIndex = mKeys.indexOf(s);
                        int nextIndex = previousIndex + 1;
                        if (nextIndex == mChores.size()) {
                            mChores.add(chore);
                            mKeys.add(key);
                        } else {
                            mChores.add(nextIndex, chore);
                            mKeys.add(nextIndex, key);
                        }
                    }

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
                Log.d(Utils.getTAG(this), "this item is not in the list");

            }
        });


        return mChores;
    }

    public static List<User> getUsers(Context context) {
        String houseName = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.HOUSE_NAME_KEY, null);
        DatabaseReference houseDatabase = FirebaseDatabase.getInstance().getReference("Houses");
        Query userQuery = houseDatabase.child(houseName).child("user");
        userQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child : children) {
                    User user = child.getValue(User.class);
                    mUsers.add(user);
                }

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

    public static void assignChores(Context context) {
        String houseName = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(Constants.HOUSE_NAME_KEY, null);
        final List<Chore> chores = findHouse(houseName);
        mChores = chores;
        final List<User> users = getUsers(context);
        mUsers = users;
        Log.i("Utils", "made it this far");

    }
}

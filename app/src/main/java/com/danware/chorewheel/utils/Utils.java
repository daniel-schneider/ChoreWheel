package com.danware.chorewheel.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.danware.chorewheel.DataModels.Chore;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Utils {
    private static final int MAX_LENGTH = 10;

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
            if (elements[i].getFileName().contains(o.getClass().getSimpleName())){
                position = i;
                break;
            }
        }

        StackTraceElement element = elements[position];
        String className = element.getFileName().replace(".java", "");
        return "[" + className + "](" + element.getMethodName() + ":" + element.getLineNumber() + ")";
    }

    public static List<Chore> findHouse(String id) {
        final List<Chore> chores = new ArrayList<>();
        DatabaseReference houseDatabase = FirebaseDatabase.getInstance().getReference("Houses");
        Query houseQuery = houseDatabase.child(id);
        houseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Iterable<DataSnapshot> snapshotIterator = dataSnapshot.getChildren();
                Iterator<DataSnapshot> iterator = snapshotIterator.iterator();
                while((iterator.hasNext())){
                    Chore chore = iterator.next().getValue(Chore.class);
                    chores.add(chore);
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

        return chores;
    }


}

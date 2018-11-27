package com.danware.chorewheel;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.danware.chorewheel.DataModels.Chore;
import com.danware.chorewheel.DataModels.User;
import com.danware.chorewheel.utils.Constants;
import com.danware.chorewheel.utils.Utils;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ChoreFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ChoreFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    static List<String> mKeys = new ArrayList<>();
    public static List<Chore> mAllChores = new ArrayList<>();
    public static List<User> mAllusers = new ArrayList<>();


    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mRef;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ChoreFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChoreFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChoreFragment newInstance(String param1, String param2) {
        ChoreFragment fragment = new ChoreFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private void getChoreData(DataSnapshot dataSnapshot) {
        String houseName = PreferenceManager
                .getDefaultSharedPreferences(getContext()).
                        getString(Constants.HOUSE_NAME_KEY, "");
        DataSnapshot choreSnapshot = dataSnapshot.child("Houses").child(houseName).child("chores");

        for (DataSnapshot ds : choreSnapshot.getChildren()) {
            Chore chore = ds.getValue(Chore.class);
            mAllChores.add(chore);

            Log.d(Utils.getTAG(this), "chore ID: " + chore.getId());
            Log.d(Utils.getTAG(this), "chore title: " + chore.getTitle());
            Log.d(Utils.getTAG(this), "chore description: " + chore.getDescription());
            Log.d(Utils.getTAG(this), "chore Frequency: " +  chore.getFrequency());

            mAdapter = new ChoreListAdapter(mAllChores);
            mRecyclerView.setAdapter(mAdapter);

        }
    }

    private void getUserData(DataSnapshot dataSnapshot) {
        String houseName = PreferenceManager
                .getDefaultSharedPreferences(getContext()).
                        getString(Constants.HOUSE_NAME_KEY, "");
        DataSnapshot usersSnapshot = dataSnapshot.child("Houses").child(houseName).child("user");

        for (DataSnapshot snapshot : usersSnapshot.getChildren()) {
            User user = snapshot.getValue(User.class);
            mAllusers.add(user);

            Log.d(Utils.getTAG(this), "User name: " + user.getName());
            Log.d(Utils.getTAG(this), "User ID: " + user.getId());
            Log.d(Utils.getTAG(this), "User House: " + user.getHouseId());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chore, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String houseName = PreferenceManager
                .getDefaultSharedPreferences(getContext()).
                getString(Constants.HOUSE_NAME_KEY, "");

        mRecyclerView = view.findViewById(R.id.choreList);
        mLayoutManager = new LinearLayoutManager(getContext());

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef = mFirebaseDatabase.getReference();

        mRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                getChoreData(dataSnapshot);
                getUserData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        mRecyclerView.setLayoutManager(mLayoutManager);
//        Utils.assignChores(getContext());
//        findHouseChores(houseName);

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public List<Chore> findHouseChores(String id) {
        DatabaseReference houseDatabase = FirebaseDatabase.getInstance().getReference("Houses");
        Query houseQuery = houseDatabase.child(id).child("chores");
        houseQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    mAllChores.add(child.getValue(Chore.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        houseQuery.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
                for (DataSnapshot child: children) {
                    mAllChores.add(child.getValue(Chore.class));
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

        mAdapter.notifyDataSetChanged();
        return mAllChores;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

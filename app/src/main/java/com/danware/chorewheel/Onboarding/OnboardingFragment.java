package com.danware.chorewheel.Onboarding;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.danware.chorewheel.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class OnboardingFragment extends Fragment {


    public OnboardingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button newUserButton = view.findViewById(R.id.new_house_button);
        newUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NewHouseFragment newHouseFragment = new NewHouseFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, newHouseFragment)
                        .addToBackStack(null)
                        .commit();
            }
        });

        Button joinHouseButton = view.findViewById(R.id.join_house_button);
        joinHouseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    JoinHouseFragment joinHouseFragment = new JoinHouseFragment();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_frame, joinHouseFragment)
                        .addToBackStack(null)
                        .commit();

            }
        });

    }
}

package com.danware.chorewheel.Onboarding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.danware.chorewheel.R;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        OnboardingFragment onboardingFragment = new OnboardingFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, onboardingFragment)
                .addToBackStack(null)
                .commit();
    }

//    private void uploadInitialChoreList() {
//        Chore chore = new Chore();
//        chore.title = "Wipe Down Counters";
//        chore.description = "Kitchen";
//        chore.frequency = "1/day";
//        chore.completed = false;
//        chore.shared = true;
//        chore.id = random();
//
//        Chore chore1 = new Chore();
//        chore1.title = "Clean Dirty Dishes";
//        chore1.description = "Kitchen";
//        chore1.frequency = "1/day";
//        chore1.completed = false;
//        chore1.shared = true;
//        chore1.id = random();
//
//        Chore chore2 = new Chore();
//        chore2.title = "Put clean dishes away";
//        chore2.description = "Kitchen";
//        chore2.frequency = "1/day";
//        chore2.completed = false;
//        chore2.shared = true;
//        chore2.id = random();
//
//        Chore chore3 = new Chore();
//        chore3.title = "Sweep the floor";
//        chore3.description = "Kitchen";
//        chore3.frequency = "1/day";
//        chore3.completed = false;
//        chore3.shared = true;
//        chore3.id = random();
//
//        Chore chore4 = new Chore();
//        chore4.title = "Put items back into drawers/cabinets";
//        chore4.description = "Kitchen/Bathroom/Laundry";
//        chore4.frequency = "1/day";
//        chore4.completed = false;
//        chore4.shared = false;
//        chore4.id = random();
//
//        Chore chore5 = new Chore();
//        chore5.title = "Put dirty clothes in hamper";
//        chore5.description = "Bedroom";
//        chore5.frequency = "1/day";
//        chore5.completed = false;
//        chore5.shared = false;
//        chore5.id = random();
//
//        Chore chore6 = new Chore();
//        chore6.title = "Throw away junk mail";
//        chore6.description = "Living Room";
//        chore6.frequency = "1/day";
//        chore6.completed = false;
//        chore6.shared = true;
//        chore6.id = random();
//
//        Chore chore7 = new Chore();
//        chore7.title = "Tidy up all surfaces";
//        chore7.description = "Living Room";
//        chore7.frequency = "1/day";
//        chore7.completed = false;
//        chore7.shared = true;
//        chore7.id = random();
//
//        Chore chore8 = new Chore();
//        chore8.title = "Wipe down appliances";
//        chore8.description = "Kitchen";
//        chore8.frequency = "1/week";
//        chore8.completed = false;
//        chore8.shared = true;
//        chore8.id = random();
//
//        Chore chore9 = new Chore();
//        chore9.title = "Clean sink, fridge handle, and microwave exterior";
//        chore9.description = "Kitchen";
//        chore9.frequency = "1/week";
//        chore9.completed = false;
//        chore9.shared = true;
//        chore9.id = random();
//
//        Chore chore10 = new Chore();
//        chore10.title = "Take out trash and Recycling if full";
//        chore10.description = "Kitchen";
//        chore10.frequency = "1/week";
//        chore10.completed = false;
//        chore10.shared = true;
//        chore10.id = random();
//
//        Chore chore11 = new Chore();
//        chore11.title = "Replace sponges";
//        chore11.description = "Kitchen";
//        chore11.frequency = "1/week";
//        chore11.completed = false;
//        chore11.shared = true;
//        chore11.id = random();
//
//        Chore chore12 = new Chore();
//        chore12.title = "Clean toilet, counter, mirror, and doorknob";
//        chore12.description = "Bathroom";
//        chore12.frequency = "1/week";
//        chore12.completed = false;
//        chore12.shared = true;
//        chore12.id = random();
//
//        Chore chore13 = new Chore();
//        chore13.title = "Put bathroom and kitchen towels in hamper";
//        chore13.description = "Bathroom/kitchen";
//        chore13.frequency = "1/week";
//        chore13.completed = false;
//        chore13.shared = true;
//        chore13.id = random();
//
//        Chore chore14 = new Chore();
//        chore14.title = "Organize desk/working space";
//        chore14.description = "Office";
//        chore14.frequency = "1/week";
//        chore14.completed = false;
//        chore14.shared = false;
//        chore14.id = random();
//
//        Chore chore15 = new Chore();
//        chore15.title = "Water plants";
//        chore15.description = "Living Room";
//        chore15.frequency = "1/week";
//        chore15.completed = false;
//        chore15.shared = true;
//        chore15.id = random();
//
//        Chore chore16 = new Chore();
//        chore16.title = "Dust";
//        chore16.description = "Living Room";
//        chore16.frequency = "1/week";
//        chore16.completed = false;
//        chore16.shared = true;
//        chore16.id = random();
//
//        Chore chore17 = new Chore();
//        chore17.title = "Vacuum";
//        chore17.description = "Living Room";
//        chore17.frequency = "1/week";
//        chore17.completed = false;
//        chore17.shared = true;
//        chore17.id = random();
//
//        Chore chore18 = new Chore();
//        chore18.title = "Disinfect light switches and doorknobs";
//        chore18.description = "Living Room";
//        chore18.frequency = "1/month";
//        chore18.completed = false;
//        chore18.shared = true;
//        chore18.id = random();
//
//        Chore chore19 = new Chore();
//        chore19.title = "Mop floors";
//        chore19.description = "Living Room/Bathroom/Kitchen";
//        chore19.frequency = "1/month";
//        chore19.completed = false;
//        chore19.shared = true;
//        chore19.id = random();
//
//        Chore chore20 = new Chore();
//        chore20.title = "Clean bath tub";
//        chore20.description = "Bathroom";
//        chore20.frequency = "1/month";
//        chore20.completed = false;
//        chore20.shared = true;
//        chore20.id = random();
//
//        Chore chore21 = new Chore();
//        chore21.title = "Clean Rugs: shake out and vacuum";
//        chore21.description = "Living Room/Bathroom/Kitchen";
//        chore21.frequency = "1/month";
//        chore21.completed = false;
//        chore21.shared = true;
//        chore21.id = random();
//
//        Chore chore22 = new Chore();
//        chore22.title = "Clean microwave interior";
//        chore22.description = "Kitchen";
//        chore22.frequency = "1/month";
//        chore22.completed = false;
//        chore22.shared = true;
//        chore22.id = random();
//
//        Chore chore23 = new Chore();
//        chore23.title = "Clean fridge, freezer, and pantry. Throw away expired food";
//        chore23.description = "Kitchen";
//        chore23.frequency = "1/month";
//        chore23.completed = false;
//        chore23.shared = true;
//        chore23.id = random();
//
//
//
//        mDatabase.child("initialChoreSet").child(chore.getId()).setValue(chore);
//        mDatabase.child("initialChoreSet").child(chore1.getId()).setValue(chore1);
//        mDatabase.child("initialChoreSet").child(chore2.getId()).setValue(chore2);
//        mDatabase.child("initialChoreSet").child(chore3.getId()).setValue(chore3);
//        mDatabase.child("initialChoreSet").child(chore4.getId()).setValue(chore4);
//        mDatabase.child("initialChoreSet").child(chore5.getId()).setValue(chore5);
//        mDatabase.child("initialChoreSet").child(chore6.getId()).setValue(chore6);
//        mDatabase.child("initialChoreSet").child(chore7.getId()).setValue(chore7);
//        mDatabase.child("initialChoreSet").child(chore8.getId()).setValue(chore8);
//        mDatabase.child("initialChoreSet").child(chore9.getId()).setValue(chore9);
//        mDatabase.child("initialChoreSet").child(chore10.getId()).setValue(chore10);
//        mDatabase.child("initialChoreSet").child(chore11.getId()).setValue(chore11);
//        mDatabase.child("initialChoreSet").child(chore12.getId()).setValue(chore12);
//        mDatabase.child("initialChoreSet").child(chore13.getId()).setValue(chore13);
//        mDatabase.child("initialChoreSet").child(chore14.getId()).setValue(chore14);
//        mDatabase.child("initialChoreSet").child(chore15.getId()).setValue(chore15);
//        mDatabase.child("initialChoreSet").child(chore16.getId()).setValue(chore16);
//        mDatabase.child("initialChoreSet").child(chore17.getId()).setValue(chore17);
//        mDatabase.child("initialChoreSet").child(chore18.getId()).setValue(chore18);
//        mDatabase.child("initialChoreSet").child(chore19.getId()).setValue(chore19);
//        mDatabase.child("initialChoreSet").child(chore20.getId()).setValue(chore20);
//        mDatabase.child("initialChoreSet").child(chore21.getId()).setValue(chore21);
//        mDatabase.child("initialChoreSet").child(chore22.getId()).setValue(chore22);
//        mDatabase.child("initialChoreSet").child(chore23.getId()).setValue(chore23);
//
//    }
}

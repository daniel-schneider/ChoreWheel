package com.danware.chorewheel;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.danware.chorewheel.DataModels.Chore;
import com.danware.chorewheel.utils.Utils;

import java.util.List;

public class ChoreListAdapter extends RecyclerView.Adapter<ChoreListAdapter.ChoreListItemView> {

    private List<Chore> mChoreList;

    public ChoreListAdapter(String houseName) {
        mChoreList = Utils.findHouse(houseName);
    }

    public ChoreListAdapter(List<Chore> choreList) {
        mChoreList = choreList;
    }

    @NonNull
    @Override
    public ChoreListItemView onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chore_list_item, viewGroup, false);
        return new ChoreListItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChoreListItemView choreListItemView, int i) {
        choreListItemView.title.setText(mChoreList.get(i).title);
        choreListItemView.description.setText(mChoreList.get(i).description);
    }

    @Override
    public int getItemCount() {
        if (mChoreList == null) {
            return 0;
        }
        return mChoreList.size();
    }

    public void updateChores(List<Chore> chores) {
        mChoreList.clear();
        mChoreList.addAll(chores);
        notifyDataSetChanged();

    }

    public class ChoreListItemView extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public ChoreListItemView(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.chore_title);
            description = itemView.findViewById(R.id.chore_description);
        }
    }
}

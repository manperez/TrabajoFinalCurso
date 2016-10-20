package com.manuel.trabajofinalcurso.persons_list.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.manuel.trabajofinalcurso.R;
import com.manuel.trabajofinalcurso.persons_list.model.pojo.FakePerson;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by manuel on 10/18/16.
 */
public class PersonsAdapter extends RecyclerView.Adapter {
    List<FakePerson> persons;
    Context context;

    public PersonsAdapter(List<FakePerson> persons, Context context) {
        this.persons = persons;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == 0) {//male
            return new PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.fake_person_list_item, parent, false));
        } else {//female
            return new PersonViewHolder(LayoutInflater.from(context).inflate(R.layout.fake_person_list_item_female, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final PersonViewHolder viewHolder = (PersonViewHolder) holder;
        viewHolder.name.setText(persons.get(position).getName());
        viewHolder.email.setText(persons.get(position).getEmail());
        Picasso.with(context).load(persons.get(position).getPhoto()).placeholder(R.mipmap.ic_launcher).into(viewHolder.personImageView);
    }

    @Override
    public int getItemCount() {
        if (persons == null) {
            return 0;
        } else {
            return persons.size();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (persons.get(position).getGender().toLowerCase().equalsIgnoreCase("male")) {
            return 0;
        } else {
            return 1;
        }
    }

    static class PersonViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout container;
        TextView name;
        TextView email;
        CircleImageView personImageView;

        public PersonViewHolder(View itemView) {
            super(itemView);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            personImageView = (CircleImageView) itemView.findViewById(R.id.person_image);
        }
    }
}

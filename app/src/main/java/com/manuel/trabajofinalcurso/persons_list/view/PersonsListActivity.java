package com.manuel.trabajofinalcurso.persons_list.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.manuel.trabajofinalcurso.R;
import com.manuel.trabajofinalcurso.persons_list.PersonsListMvp;
import com.manuel.trabajofinalcurso.persons_list.model.PersonsInteractor;
import com.manuel.trabajofinalcurso.persons_list.model.pojo.FakePerson;
import com.manuel.trabajofinalcurso.persons_list.presenter.PersonsPresenter;

import java.util.List;

public class PersonsListActivity extends AppCompatActivity implements PersonsListMvp.View {

    //Vistas
    private RecyclerView recyclerView;
    private ProgressBar loadingBar;

    private PersonsListMvp.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.persons_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(PersonsListActivity.this));
        loadingBar = (ProgressBar) findViewById(R.id.loading_bar);
        presenter = new PersonsPresenter(this, new PersonsInteractor());
        presenter.getPersons(10);
    }

    @Override
    public void showLoading() {
        recyclerView.setVisibility(View.GONE);
        loadingBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        loadingBar.setVisibility(View.GONE);
    }

    @Override
    public void showError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayPersons(List<FakePerson> persons) {
        PersonsAdapter adapter = new PersonsAdapter(persons, this);
        recyclerView.setAdapter(adapter);
        recyclerView.setVisibility(View.VISIBLE);
    }
}

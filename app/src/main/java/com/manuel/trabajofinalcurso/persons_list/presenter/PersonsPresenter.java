package com.manuel.trabajofinalcurso.persons_list.presenter;

import com.manuel.trabajofinalcurso.persons_list.PersonsListMvp;
import com.manuel.trabajofinalcurso.persons_list.model.pojo.FakePerson;

import java.util.List;

/**
 * Created by manuel on 10/18/16.
 */
public class PersonsPresenter implements PersonsListMvp.Presenter {

    private PersonsListMvp.View view;
    private PersonsListMvp.Interactor interactor;

    public PersonsPresenter(PersonsListMvp.View view, PersonsListMvp.Interactor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void getPersons(int personNumber) {
        if (view != null) {
            view.showLoading();
        }

        interactor.getPersons(personNumber, new GetPersonCallback() {

            @Override
            public void onSuccess(List<FakePerson> persons) {
                if (view != null) {
                    view.hideLoading();
                    view.displayPersons(persons);
                }
            }

            @Override
            public void onError(String errorMessage) {
                if (view != null) {
                    view.hideLoading();
                    view.showError(errorMessage);
                }

            }
        });

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }
}

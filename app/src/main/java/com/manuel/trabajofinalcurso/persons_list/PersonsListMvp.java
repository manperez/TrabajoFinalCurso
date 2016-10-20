package com.manuel.trabajofinalcurso.persons_list;

import com.manuel.trabajofinalcurso.persons_list.model.pojo.FakePerson;

import java.util.List;

/**
 * Created by manuel on 10/18/16.
 */
public interface PersonsListMvp {

    interface View {

        void showLoading();

        void hideLoading();

        void showError(String errorMessage);

        void displayPersons(List<FakePerson> facts);

    }

    interface Presenter {

        void getPersons(int personNumber);

        void onDestroy();

        interface GetPersonCallback {

            void onSuccess(List<FakePerson> persons);

            void onError(String errorMessage);

        }

    }

    interface Interactor {

        void getPersons(int factsNumber, final PersonsListMvp.Presenter.GetPersonCallback callback);

    }
}

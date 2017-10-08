package com.example.jevil.gsontest;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.jevil.gsontest.Adapters.MainListAdapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CardsFragment extends Fragment {

    RecyclerView rv;
    MainListAdapter adapter;
    ProgressBar pb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cards, container, false);

        pb = (ProgressBar) v.findViewById(R.id.pb);
        rv = (RecyclerView) v.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this.getContext()));
        rv.setItemAnimator(new DefaultItemAnimator());

        MyAsyncTask asyncTask = new MyAsyncTask();
        asyncTask.execute();

        return v;
    }

    private class MyAsyncTask extends AsyncTask<Void, Void, List<JsonModel>> {
        private JsonApi jsonApi;
        List<JsonModel> posts;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            jsonApi = Controller.getApi();
            posts = new ArrayList<>();
        }

        @Override
        protected List<JsonModel>  doInBackground(Void... parameter) {

            try {
                addData(jsonApi.getPosts(getRandomInt(100)).execute().body().get(0), "posts");
                addData(jsonApi.getComments(getRandomInt(500)).execute().body().get(0), "comments");
                addUsersData(jsonApi.getUsers().execute().body(), "users");
                addData(jsonApi.getPhotos(1).execute().body().get(0), "photos");
                addData(jsonApi.getTodos(getRandomInt(200)).execute().body().get(0), "todos");

            } catch (IOException e) {
                e.printStackTrace();
                Snackbar myAwesomeSnackbar = Snackbar.make(
                        rv,
                        "Нет подключения к интернету",
                        Snackbar.LENGTH_SHORT
                );
                myAwesomeSnackbar.show();
            }
            return posts;
        }

        @Override
        protected void onPostExecute(List<JsonModel> result) {
            super.onPostExecute(result);
            pb.setVisibility(View.GONE);
            adapter = new MainListAdapter(result, getContext(), getActivity());
            rv.setAdapter(adapter);
        }

        private void addData(JsonModel request, String category) {
            JsonModel jsonModel = request;
            jsonModel.setCategory(category);
            posts.add(jsonModel);
        }

        private void addUsersData(List<JsonModel> users, String category) {

            List<JsonModel> usersList = users.subList(0, 5);
            JsonModel usersFinal = new JsonModel();
            usersFinal.setUsersList(usersList);
            addData(usersFinal, category);
        }

        private int getRandomInt(int max){
            return ((int) (Math.random() * max) + 1);
        }
    }


}

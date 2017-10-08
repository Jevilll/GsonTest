package com.example.jevil.gsontest.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import com.example.jevil.gsontest.Controller;
import com.example.jevil.gsontest.JsonApi;
import com.example.jevil.gsontest.JsonModel;
import com.example.jevil.gsontest.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainListAdapter extends RecyclerView.Adapter<MainListHolder> {

    private List<JsonModel> data;
    private Context context;
    private ViewGroup vg;
    private Activity activity;

    public MainListAdapter(List<JsonModel> items, Context context, Activity activity) {
        this.context = context;
        this.data = items;
        this.activity = activity;
    }

    @Override
    public MainListHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_view, viewGroup, false);
        MainListHolder holder = new MainListHolder(v);
        vg = viewGroup;
        return holder;
    }

    @Override
    public void onBindViewHolder(final MainListHolder holder, final int position) {
        holder.tvName.setText(data.get(position).getCategory());
        switch (data.get(position).getCategory()) {
            case "posts":
                holder.tvTitle.setText(data.get(position).getTitle());
                holder.tvText.setText(data.get(position).getBody());
                holder.btrConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // прячем клавиатуру
                        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(holder.btrConfirm.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        if (holder.etId.getText().toString().equals("")) {
                            Snackbar myAwesomeSnackbar = Snackbar.make(
                                    holder.cv,
                                    "Поле ввода не должно быть пустым",
                                    Snackbar.LENGTH_SHORT
                            );
                            myAwesomeSnackbar.show();
                        } else {
                            if (checkId(position, Integer.valueOf(holder.etId.getText().toString()))) {
                                getJson(Integer.valueOf(holder.etId.getText().toString()), holder, position);
                            } else {
                                Snackbar myAwesomeSnackbar = Snackbar.make(
                                        holder.cv,
                                        "Диапазон значений id от 1 до 100",
                                        Snackbar.LENGTH_SHORT
                                );
                                myAwesomeSnackbar.show();
                            }
                        }

                    }
                });
                holder.tvTitle.setVisibility(View.VISIBLE);
                holder.tvText.setVisibility(View.VISIBLE);
                holder.clButton.setVisibility(View.VISIBLE);
                break;
            case "comments":
                holder.tvTitle.setText(data.get(position).getName());
                holder.tvText.setText(data.get(position).getBody());
                holder.tvEmail.setText(data.get(position).getEmail());
                holder.btrConfirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // прячем клавиатуру
                        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.hideSoftInputFromWindow(holder.btrConfirm.getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                        if (holder.etId.getText().toString().equals("")) {
                            Snackbar myAwesomeSnackbar = Snackbar.make(
                                    holder.cv,
                                    "Поле ввода не должно быть пустым",
                                    Snackbar.LENGTH_SHORT
                            );
                            myAwesomeSnackbar.show();
                        } else {
                            if (checkId(position, Integer.valueOf(holder.etId.getText().toString()))) {
                                getJson(Integer.valueOf(holder.etId.getText().toString()), holder, position);
                            } else {
                                Snackbar myAwesomeSnackbar = Snackbar.make(
                                        holder.cv,
                                        "Диапазон значений id от 1 до 500",
                                        Snackbar.LENGTH_SHORT
                                );
                                myAwesomeSnackbar.show();
                            }
                        }
                    }
                });
                holder.tvTitle.setVisibility(View.VISIBLE);
                holder.tvText.setVisibility(View.VISIBLE);
                holder.tvEmail.setVisibility(View.VISIBLE);
                holder.clButton.setVisibility(View.VISIBLE);
                break;
            case "users":
                String[] usersStringsArray = new String[data.get(position).getUsersList().size()];
                for (int i = 0; i != data.get(position).getUsersList().size(); i++) {
                    usersStringsArray[i] = data.get(position).getUsersList().get(i).getName();
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(vg.getContext(),
                        android.R.layout.simple_list_item_1, usersStringsArray);

                holder.lvUsers.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(context);
                        builder.setTitle(data.get(2).getUsersList().get(i).getName());
                        View vDialog = LayoutInflater.from(builder.getContext()).inflate(R.layout.user_card, (ViewGroup) holder.lvUsers.getRootView(), false);
                        Button btnUsername = (Button) vDialog.findViewById(R.id.btnUsername);
                        btnUsername.setText(data.get(2).getUsersList().get(i).getUsername());
                        Button btnPhone = (Button) vDialog.findViewById(R.id.btnPhone);
                        btnPhone.setText(data.get(2).getUsersList().get(i).getPhone());
                        Button btnEmail = (Button) vDialog.findViewById(R.id.btnEmail);
                        btnEmail.setText(data.get(2).getUsersList().get(i).getEmail());
                        Button btnWeb = (Button) vDialog.findViewById(R.id.btnWeb);
                        btnWeb.setText(data.get(2).getUsersList().get(i).getWebsite());
                        builder.setView(vDialog);
                        builder.setNegativeButton("Закрыть", null);
                        builder.show();
                    }
                });

                // присваиваем адаптер списку
                holder.lvUsers.setAdapter(adapter);
                holder.separator.setVisibility(View.GONE);
                holder.lvUsers.setVisibility(View.VISIBLE);
                break;
            case "photos":
                holder.tvTitlePhoto.setText(data.get(position).getTitle());
                Picasso.with(holder.cv.getContext())
                        .load(data.get(position).getUrl())
                        .into(holder.iv);
                holder.clPhoto.setVisibility(View.VISIBLE);
                break;
            case "todos":
                holder.tvTitle.setText(data.get(position).getTitle());
                if (data.get(position).getCompleted()) {
                    holder.cbComplete.setChecked(true);
                }
                holder.tvTitle.setVisibility(View.VISIBLE);
                holder.cbComplete.setVisibility(View.VISIBLE);
                break;
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private boolean checkId(int position, int id) {
        boolean isCorrectId = false;
        switch (position) {
            case 0://posts
                if ((id > 0) && (id <= 100)) isCorrectId = true;
                break;
            case 1://comments
                if ((id > 0) && (id <= 500)) isCorrectId = true;
                break;
        }
        return isCorrectId;
    }

    private void getJson(int id, final MainListHolder holder, final int position) {
        JsonApi jsonApi = Controller.getApi();
        if (position == 0) {
            jsonApi.getPosts(id).enqueue(new Callback<List<JsonModel>>() {
                @Override
                public void onResponse(Call<List<JsonModel>> call, Response<List<JsonModel>> response) {
                    holder.tvTitle.setText(response.body().get(0).getTitle());
                    holder.tvText.setText(response.body().get(0).getBody());
                }

                @Override
                public void onFailure(Call<List<JsonModel>> call, Throwable t) {
                    Toast.makeText(context,"Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            jsonApi.getComments(id).enqueue(new Callback<List<JsonModel>>() {
                @Override
                public void onResponse(Call<List<JsonModel>> call, Response<List<JsonModel>> response) {
                    holder.tvTitle.setText(response.body().get(0).getName());
                    holder.tvText.setText(response.body().get(0).getBody());
                    holder.tvEmail.setText(response.body().get(0).getEmail());
                }

                @Override
                public void onFailure(Call<List<JsonModel>> call, Throwable t) {
                    Toast.makeText(context,"Ошибка: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }

    }

}
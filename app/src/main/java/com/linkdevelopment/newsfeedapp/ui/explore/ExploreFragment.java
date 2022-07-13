package com.linkdevelopment.newsfeedapp.ui.explore;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.linkdevelopment.newsfeedapp.Data.Model.APIResponse;
import com.linkdevelopment.newsfeedapp.Data.Model.Article;
import com.linkdevelopment.newsfeedapp.MainActivity;
import com.linkdevelopment.newsfeedapp.Utils.RecyclerViewVerticalSpacing;
import com.linkdevelopment.newsfeedapp.databinding.FragmentExploreBinding;
import com.linkdevelopment.newsfeedapp.ui.ArticleDetailsScreen;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExploreFragment extends Fragment {

    private FragmentExploreBinding binding;

    static RecyclerView articleRecyclerView;
    ArticleRecyclerViewAdapter adapter;
    static List<Article> result = new ArrayList<>();

    public static class MyOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            int itemPosition = articleRecyclerView.getChildLayoutPosition(view);
            Article selectedArticle = result.get(itemPosition);

            Intent editProfileIntent = new Intent(MainActivity.context, ArticleDetailsScreen.class);
            editProfileIntent.putExtra("selectedArticle",selectedArticle);
            editProfileIntent.addFlags(FLAG_ACTIVITY_NEW_TASK);
            MainActivity.context.startActivity(editProfileIntent);

//            Toast.makeText(MainActivity.context, item.getTitle(), Toast.LENGTH_LONG).show();
        }
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentExploreBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        articleRecyclerView = binding.articlesRecyclerview;
        articleRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.context));
        RecyclerViewVerticalSpacing dividerItemDecoration = new RecyclerViewVerticalSpacing(45);
        articleRecyclerView.addItemDecoration(dividerItemDecoration);


        loadAllArticles();


        return root;
    }


    private void loadAllArticles() {

        result.clear();

        MainActivity.mService.GetArticles("articles?source=the-next-web&apiKey=533af958594143758318137469b41ba9").enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {
                if (response.body().getStatus().equals("ok")) {
                    result.addAll(response.body().getArticles());

                    MainActivity.mService.GetArticles("articles?source=associated-press&apiKey=533af958594143758318137469b41ba9").enqueue(new Callback<APIResponse>() {
                        @Override
                        public void onResponse(Call<APIResponse> call, Response<APIResponse> response) {

                            if (response.body().getStatus().equals("ok")) {
                                result.addAll(response.body().getArticles());

                                adapter = new ArticleRecyclerViewAdapter(result, MainActivity.context);
                                articleRecyclerView.setAdapter(adapter);
                                articleRecyclerView.invalidate();


                            } else {
                                Toast.makeText(MainActivity.context, "something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<APIResponse> call, Throwable t) {
                            Toast.makeText(MainActivity.context, "error in connection please try again later", Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(MainActivity.context, "something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<APIResponse> call, Throwable t) {
                Toast.makeText(MainActivity.context, "error in connection please try again later", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}
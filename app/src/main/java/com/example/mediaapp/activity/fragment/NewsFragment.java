package com.example.mediaapp.activity.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mediaapp.R;
import com.example.mediaapp.activity.adapter.NewsAdapter;
import com.example.mediaapp.activity.model.News;
import com.example.mediaapp.activity.rest.RestClient;
import com.example.mediaapp.activity.rest.RestService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NewsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private News mNewsArrayList;
    private TextView mDisconnectedTextView;
    ProgressDialog mProgressDialog;
    private SwipeRefreshLayout mSwipeContainer;
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_news, container, false);
        mSwipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);

        initViews();
        //Configure the refreshing colors
        mSwipeContainer.setColorSchemeResources(android.R.color.holo_orange_dark);

        mSwipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadJSON();
                Toast.makeText(view.getContext(), "News List Refreshed", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    /*
     Intializing views
     */
    private void initViews(){
        mProgressDialog = new ProgressDialog (getActivity());
        mProgressDialog.setMessage("Fetching News........");
        mProgressDialog.setCancelable(false);
        mProgressDialog.show();
        mRecyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mRecyclerView.smoothScrollToPosition(0);
        loadJSON();
    }
    /*
     load Json method using retrofit api
     */
    private void loadJSON(){
        mDisconnectedTextView = (TextView) getActivity().findViewById(R.id.disconnected);
        try{
            RestClient client = new RestClient();
            RestService request = RestClient.retrofitInstance.create(RestService.class);
            Call<News> call = request.getNews();
            call.enqueue(new Callback<News>() {
                @Override
                public void onResponse(Call<News> call, Response<News> response) {
                    Log.d("newsurl", "response.raw().request().url();"+response.raw().request().url());
                    mNewsArrayList = response.body();
                    mRecyclerView.setAdapter(new NewsAdapter(view.getContext(), mNewsArrayList.getRows()));
                    mRecyclerView.smoothScrollToPosition(0);
                    mSwipeContainer.setRefreshing(false);
                    mProgressDialog.hide();

                }

                @Override
                public void onFailure(Call<News> call, Throwable t) {
                    Log.d("Error", t.getMessage());
                    Toast.makeText(view.getContext(), "Error Fetching mNews !", Toast.LENGTH_SHORT).show();
                    mDisconnectedTextView.setVisibility(View.VISIBLE);
                    mProgressDialog.hide();

                }
            });
        }catch (Exception e){
            Log.d("Error", e.getMessage());

        }
    }
}
package com.debut.ellipsis.freehit.Social.Polls;


import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.MainActivity;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SocialPolls extends Fragment {

    private ProgressBar mProgressBar;
    public TextView NoPollsText;
    public Button NoPollsButton;
    public Button NoConnectionButton;
    private FloatingActionButton fab;
    private LinearLayoutManager mLinearLayoutManager;
    private RecyclerViewReadyCallback recyclerViewReadyCallback;

    public interface RecyclerViewReadyCallback {
        void onLayoutReady();
    }

    public SocialPolls() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_news_list, container, false);

        MainActivity.apiInterface = ApiClient.getClient().create(APIInterface.class);

        View viewRecycler = rootView.findViewById(R.id.news_list);

        View viewFAB = rootView.findViewById(R.id.fab);
        fab = viewFAB.findViewById(R.id.common_fab);

        fab.setImageResource(R.drawable.arrow_down);
        fab.setVisibility(View.INVISIBLE);

        final RecyclerView recyclerView = viewRecycler.findViewById(R.id.recycler_list);

        mLinearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(mLinearLayoutManager);

        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);

        View viewProgress = rootView.findViewById(R.id.progress);
        mProgressBar = viewProgress.findViewById(R.id.progress_bar);

        final SwipeRefreshLayout refLayout = viewRecycler.findViewById(R.id.refresh_layout);

        final View No_polls = rootView.findViewById(R.id.No_news);

        NoPollsText = No_polls.findViewById(R.id.empty_view);
        NoPollsButton = No_polls.findViewById(R.id.No_Live_Matches_button);

        if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES)
        {
            recyclerView.setBackgroundColor(getResources().getColor(R.color.night_background));
            fab.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.dark)));
            NoPollsText.setTextColor(Color.WHITE);
            NoPollsButton.setBackgroundResource(R.drawable.button_shape_dark);
            NoPollsButton.setTextColor(Color.BLACK);
        }


        final View no_internet_connection = rootView.findViewById(R.id.Unavailable_connection);

        NoConnectionButton = no_internet_connection.findViewById(R.id.no_internet_refresh_button);

        refLayout.setEnabled(false);
        final Call<PollCardItem> call = MainActivity.apiInterface.doGetPollsListResources();
        call.enqueue(new Callback<PollCardItem>() {
            @Override
            public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                mProgressBar.setVisibility(View.INVISIBLE);
                if (getActivity() != null) {

                    List<PollCardItem> polls = response.body().getResults();
                    if (polls.size() == 0) {

                        No_polls.setVisibility(View.VISIBLE);
                        NoPollsText.setText(R.string.EmptyPolls);
                        NoPollsButton.setOnClickListener(new View.OnClickListener() {

                            public void onClick(View v) {
                                FragmentTransaction ft = getFragmentManager().beginTransaction();
                                ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();
                            }
                        });

                    }
                    recyclerView.setVisibility(View.VISIBLE);
                    if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES) {
                        recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item_dark, getContext()));
                    }
                    else if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_NO)
                    {
                        recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                    }
                    if (mLinearLayoutManager.findLastVisibleItemPosition() == polls.size() - 1) {
                        recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
                            @Override
                            public void onLayoutReady() {
                                recyclerView.setOnTouchListener(new View.OnTouchListener() {
                                    @Override
                                    public boolean onTouch(View v, MotionEvent event) {
                                        return false;
                                    }
                                });
                            }
                        };
                    }
                }

            }

            @Override
            public void onFailure(Call<PollCardItem> call, Throwable t) {

                mProgressBar.setVisibility(View.GONE);
                no_internet_connection.setVisibility(View.VISIBLE);
                NoConnectionButton.setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        FragmentTransaction ft = getFragmentManager().beginTransaction();
                        ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();

                    }
                });
                call.cancel();
            }
        });
        refLayout.setColorSchemeResources(R.color.orange);
        refLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                           @Override
                                           public void onRefresh() {
                                               recyclerView.setOnTouchListener(new View.OnTouchListener() {
                                                   @Override
                                                   public boolean onTouch(View v, MotionEvent event) {
                                                       return true;
                                                   }
                                               });
                                               refLayout.setRefreshing(true);
                                               Call<PollCardItem> call = MainActivity.apiInterface.doGetPollsListResources();
                                               call.enqueue(new Callback<PollCardItem>() {
                                                   @Override
                                                   public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {

                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       no_internet_connection.setVisibility(View.INVISIBLE);
                                                       No_polls.setVisibility(View.INVISIBLE);
                                                       fab.hide();
                                                       if (getActivity() != null) {

                                                           List<PollCardItem> polls = response.body().getResults();
                                                           if (polls.size() == 0) {
                                                               No_polls.setVisibility(View.VISIBLE);

                                                               NoPollsText.setText(R.string.EmptyPolls);
                                                               NoPollsButton.setOnClickListener(new View.OnClickListener() {

                                                                   public void onClick(View v) {
                                                                       FragmentTransaction ft = getFragmentManager().beginTransaction();
                                                                       ft.detach(SocialPolls.this).attach(SocialPolls.this).commit();
                                                                   }
                                                               });

                                                           }
                                                           recyclerView.setVisibility(View.VISIBLE);
                                                           if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_YES) {
                                                               recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item_dark, getContext()));
                                                           }
                                                           else if(AppCompatDelegate.getDefaultNightMode() ==AppCompatDelegate.MODE_NIGHT_NO)
                                                           {
                                                               recyclerView.setAdapter(new PollItemAdapter(polls, R.layout.fragment_social_polls_list_item, getContext()));
                                                           }
                                                           recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
                                                               @Override
                                                               public void onLayoutReady() {
                                                                   recyclerView.setOnTouchListener(new View.OnTouchListener() {
                                                                       @Override
                                                                       public boolean onTouch(View v, MotionEvent event) {
                                                                           return false;
                                                                       }
                                                                   });
                                                               }
                                                           };
                                                           /*}*/
                                                           refLayout.setRefreshing(false);
                                                       }
                                                   }

                                                   @Override
                                                   public void onFailure(Call<PollCardItem> call, Throwable t) {

                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       mProgressBar.setVisibility(View.INVISIBLE);
                                                       recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
                                                           @Override
                                                           public void onLayoutReady() {
                                                               recyclerView.setOnTouchListener(new View.OnTouchListener() {
                                                                   @Override
                                                                   public boolean onTouch(View v, MotionEvent event) {
                                                                       return false;
                                                                   }
                                                               });
                                                           }
                                                       };
                                                       refLayout.setRefreshing(false);
                                                       Toast toast = Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT);
                                                       toast.show();
                                                       call.cancel();
                                                   }
                                               });


                                           }
                                       }
        );


        recyclerView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (recyclerViewReadyCallback != null) {
                    recyclerViewReadyCallback.onLayoutReady();
                }
                recyclerViewReadyCallback = null;
            }
        });

        recyclerViewReadyCallback = new RecyclerViewReadyCallback() {
            @Override
            public void onLayoutReady() {
                //
                //here comes your code that will be executed after all items has are laid down
                //
                refLayout.setEnabled(true);
                recyclerView.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
            }
        };


        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                if (mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() >= totalItemCount) {
                    fab.hide();
                }
                if (mLinearLayoutManager.findLastCompletelyVisibleItemPosition() >= totalItemCount - 2) {
                    fab.hide();
                }                   //Its at bottom ..
                else if (mLinearLayoutManager.findFirstCompletelyVisibleItemPosition() >= 0 || mLinearLayoutManager.findLastCompletelyVisibleItemPosition() < totalItemCount - 2) {
                    fab.show();
                }
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int totalItemCount = recyclerView.getAdapter().getItemCount();
                int firstVisibleItemIndex = mLinearLayoutManager.findFirstVisibleItemPosition();
                if (firstVisibleItemIndex > 0) {
                    mLinearLayoutManager.smoothScrollToPosition(recyclerView, null, 0);
                }
            }
        });

        return rootView;
    }

}
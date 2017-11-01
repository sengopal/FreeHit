package com.debut.ellipsis.freehit.Social.Polls;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.debut.ellipsis.freehit.APIInterface;
import com.debut.ellipsis.freehit.ApiClient;
import com.debut.ellipsis.freehit.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static com.debut.ellipsis.freehit.IntoSlider.WelcomeActivity.MY_PREFS_NAME;


public class PollItemAdapter extends RecyclerView.Adapter<com.debut.ellipsis.freehit.Social.Polls.PollItemAdapter.PollsViewHolder> {

    private List<PollCardItem> PollCardItems;
    private int rowLayout;
    private Context context;


    public class PollsViewHolder extends RecyclerView.ViewHolder {
        RadioGroup rGroup;
        TextView title;
        RadioButton button1;
        RadioButton button2;
        RadioButton button3;
        RadioButton button4;
        Button submit;
        CardView cardView;
        RelativeLayout RlContainer;


        public PollsViewHolder(View v) {
            super(v);
            rGroup = (RadioGroup) v.findViewById(R.id.poll_group);
            title = (TextView) v.findViewById(R.id.poll_title);
            button1 = (RadioButton) v.findViewById(R.id.option_1);
            button2 = (RadioButton) v.findViewById(R.id.option_2);
            button3 = (RadioButton) v.findViewById(R.id.option_3);
            button4 = (RadioButton) v.findViewById(R.id.option_4);
            submit = (Button) v.findViewById(R.id.poll_submit);
            cardView = (CardView) v.findViewById(R.id.card_view);

            RlContainer = (RelativeLayout) v.findViewById(R.id.parent_layout);
        }
    }

    public PollItemAdapter(List<PollCardItem> news, int rowLayout, Context context) {
        this.PollCardItems = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public PollsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);

        return new PollsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final PollsViewHolder holder, int position) {

        final SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();

        holder.title.setText(PollCardItems.get(position).getQuestion());
        final View pollRes = holder.RlContainer.findViewById(R.id.pollItem_result);
        final RelativeLayout rlayout = (RelativeLayout) pollRes.findViewById(R.id.pollItem_result);
        final RelativeLayout pGroupLay = (RelativeLayout) holder.RlContainer.findViewById(R.id.poll_group_layout);

        TextView option1 = (TextView) pollRes.findViewById(R.id.option_1);

        TextView option2 = (TextView) pollRes.findViewById(R.id.option_2);

        TextView option3 = (TextView) pollRes.findViewById(R.id.option_3);

        TextView option4 = (TextView) pollRes.findViewById(R.id.option_4);


        final TextView peroption1 = (TextView) pollRes.findViewById(R.id.percentage_option_1);

        final TextView peroption2 = (TextView) pollRes.findViewById(R.id.percentage_option_2);

        final TextView peroption3 = (TextView) pollRes.findViewById(R.id.percentage_option_3);

        final TextView peroption4 = (TextView) pollRes.findViewById(R.id.percentage_option_4);

        final TextView valueoption1 = (TextView) pollRes.findViewById(R.id.votecount_option_1);

        final TextView valueoption2 = (TextView) pollRes.findViewById(R.id.votecount_option_2);

        final TextView valueoption3 = (TextView) pollRes.findViewById(R.id.votecount_option_3);

        final TextView valueoption4 = (TextView) pollRes.findViewById(R.id.votecount_option_4);


        final RoundCornerProgressBar progress1 = (RoundCornerProgressBar) pollRes.findViewById(R.id.progress_bar_option1);
        progress1.setProgressColor(Color.parseColor("#00796b"));
        progress1.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

        final RoundCornerProgressBar progress2 = (RoundCornerProgressBar) pollRes.findViewById(R.id.progress_bar_option2);
        progress2.setProgressColor(Color.parseColor("#00796b"));
        progress2.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

        final RoundCornerProgressBar progress3 = (RoundCornerProgressBar) pollRes.findViewById(R.id.progress_bar_option3);
        progress3.setProgressColor(Color.parseColor("#00796b"));
        progress3.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

        final RoundCornerProgressBar progress4 = (RoundCornerProgressBar) pollRes.findViewById(R.id.progress_bar_option4);
        progress4.setProgressColor(Color.parseColor("#00796b"));
        progress4.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

        holder.title.setText(PollCardItems.get(position).getQuestion());

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_YES) {
            holder.cardView.setBackgroundColor(Color.parseColor("#484a4f"));
            holder.title.setTextColor(Color.WHITE);
            option1.setTextColor(Color.WHITE);
            option2.setTextColor(Color.WHITE);
            option3.setTextColor(Color.WHITE);
            option4.setTextColor(Color.WHITE);
            holder.button1.setTextColor(Color.WHITE);
            holder.button2.setTextColor(Color.WHITE);
            holder.button3.setTextColor(Color.WHITE);
            holder.button4.setTextColor(Color.WHITE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.button1.setButtonTintList(ColorStateList.valueOf(Color.WHITE));
                holder.button2.setButtonTintList(ColorStateList.valueOf(Color.WHITE));
                holder.button3.setButtonTintList(ColorStateList.valueOf(Color.WHITE));
                holder.button4.setButtonTintList(ColorStateList.valueOf(Color.WHITE));
            }

            holder.submit.setBackgroundResource(R.drawable.button_shape_dark);
            holder.submit.setTextColor(Color.BLACK);
            peroption1.setTextColor(Color.WHITE);
            peroption2.setTextColor(Color.WHITE);
            peroption3.setTextColor(Color.WHITE);
            peroption4.setTextColor(Color.WHITE);
            valueoption1.setTextColor(Color.WHITE);
            valueoption2.setTextColor(Color.WHITE);
            valueoption3.setTextColor(Color.WHITE);
            valueoption4.setTextColor(Color.WHITE);

        }

        if (PollCardItems.get(position).getCount() == 2) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            option1.setText(PollCardItems.get(position).getCtitle(0));

            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            option2.setText(PollCardItems.get(position).getCtitle(1));

            option3.setVisibility(View.GONE);
            ;
            option4.setVisibility(View.GONE);
            ;
            holder.button3.setVisibility(View.GONE);
            ;
            holder.button4.setVisibility(View.GONE);
            ;
        }

        if (PollCardItems.get(position).getCount() == 3) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            option1.setText(PollCardItems.get(position).getCtitle(0));

            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            option2.setText(PollCardItems.get(position).getCtitle(1));

            holder.button3.setText(PollCardItems.get(position).getCtitle(2));
            option3.setText(PollCardItems.get(position).getCtitle(2));

            option4.setVisibility(View.GONE);
            ;
            holder.button4.setVisibility(View.GONE);
            ;
        }

        if (PollCardItems.get(position).getCount() == 4) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            option1.setText(PollCardItems.get(position).getCtitle(0));

            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            option2.setText(PollCardItems.get(position).getCtitle(1));

            holder.button3.setText(PollCardItems.get(position).getCtitle(2));
            option3.setText(PollCardItems.get(position).getCtitle(2));

            holder.button4.setText(PollCardItems.get(position).getCtitle(3));
            option4.setText(PollCardItems.get(position).getCtitle(3));

        }


        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.button1.isChecked() || holder.button2.isChecked() || holder.button3.isChecked() || holder.button4.isChecked()) {
                    holder.submit.setVisibility(View.INVISIBLE);
                    holder.title.setVisibility(View.VISIBLE);
                    pGroupLay.setVisibility(View.INVISIBLE);

                    editor.putBoolean("has_voted_" + PollCardItems.get(holder.getAdapterPosition()).getVoteid(), true);
                    editor.apply();

                    int selectedId = holder.rGroup.getCheckedRadioButtonId();
                    RadioButton clicked = (RadioButton) holder.RlContainer.findViewById(selectedId);
                    String name = clicked.getText().toString();
                    int choice = PollCardItems.get(holder.getAdapterPosition()).searchTitle(name);
                    rlayout.setVisibility(View.VISIBLE);
                    APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
                    Call<PollCardItem> call = apiInterface.doVotePollListResources(PollCardItems.get(holder.getAdapterPosition()).getId().toString(), String.valueOf(choice));
                    call.enqueue(new Callback<PollCardItem>() {
                        @Override
                        public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                            PollCardItem poll = response.body().getResults().get(0);

                            int total = poll.getTotalVotes();
                            holder.title.setVisibility(View.VISIBLE);

                            if (PollCardItems.get(holder.getAdapterPosition()).getCount() == 2) {
                                progress1.setMax(100);
                                progress1.setProgress(PollResult(poll.getCvotes(0), total));
                                progress2.setMax(100);
                                progress2.setProgress(PollResult(poll.getCvotes(1), total));
                                progress3.setVisibility(View.GONE);
                                progress4.setVisibility(View.GONE);

                                peroption1.setText(String.format("%.2f", (PollResult(poll.getCvotes(0), total))) + '%');
                                peroption2.setText(String.format("%.2f", (PollResult(poll.getCvotes(1), total))) + '%');
                                peroption3.setVisibility(View.GONE);
                                peroption4.setVisibility(View.GONE);

                                valueoption1.setText("( " + poll.getCvotes(0) + " )");
                                valueoption2.setText("( " + poll.getCvotes(1) + " )");
                                valueoption3.setVisibility(View.GONE);
                                valueoption4.setVisibility(View.GONE);
                            }

                            if (PollCardItems.get(holder.getAdapterPosition()).getCount() == 3) {
                                progress1.setMax(100);
                                progress1.setProgress(PollResult(poll.getCvotes(0), total));
                                progress2.setMax(100);
                                progress2.setProgress(PollResult(poll.getCvotes(1), total));
                                progress3.setMax(100);
                                progress3.setProgress(PollResult(poll.getCvotes(2), total));
                                progress4.setVisibility(View.GONE);

                                peroption1.setText(String.format("%.2f", (PollResult(poll.getCvotes(0), total))) + '%');
                                peroption2.setText(String.format("%.2f", (PollResult(poll.getCvotes(1), total))) + '%');
                                peroption3.setText(String.format("%.2f", (PollResult(poll.getCvotes(2), total))) + '%');
                                peroption4.setVisibility(View.GONE);

                                valueoption1.setText("( " + poll.getCvotes(0) + " )");
                                valueoption2.setText("( " + poll.getCvotes(1) + " )");
                                valueoption3.setText("( " + poll.getCvotes(2) + " )");
                                valueoption4.setVisibility(View.GONE);
                            }

                            if (PollCardItems.get(holder.getAdapterPosition()).getCount() == 4) {
                                progress1.setMax(100);
                                progress1.setProgress(PollResult(poll.getCvotes(0), total));
                                progress2.setMax(100);
                                progress2.setProgress(PollResult(poll.getCvotes(1), total));
                                progress3.setMax(100);
                                progress3.setProgress(PollResult(poll.getCvotes(2), total));
                                progress4.setMax(100);
                                progress4.setProgress(PollResult(poll.getCvotes(3), total));
                                peroption1.setText(String.format("%.2f", (PollResult(poll.getCvotes(0), total))) + '%');
                                peroption2.setText(String.format("%.2f", (PollResult(poll.getCvotes(1), total))) + '%');
                                peroption3.setText(String.format("%.2f", (PollResult(poll.getCvotes(2), total))) + '%');
                                peroption4.setText(String.format("%.2f", (PollResult(poll.getCvotes(3), total))) + '%');

                                valueoption1.setText("( " + poll.getCvotes(0) + " )");
                                valueoption2.setText("( " + poll.getCvotes(1) + " )");
                                valueoption3.setText("( " + poll.getCvotes(2) + " )");
                                valueoption4.setText("( " + poll.getCvotes(3) + " )");

                            }


                        }

                        @Override
                        public void onFailure(Call<PollCardItem> call, Throwable t) {
                            Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    Toast.makeText(context, "Please select an option for Poll : " + (holder.getAdapterPosition() + 1), Toast.LENGTH_SHORT).show();
                }
            }
        });


        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean name = prefs.getBoolean("has_voted_" + PollCardItems.get(position).getVoteid(), false);
        if (name) {
            holder.submit.setVisibility(View.INVISIBLE);

            pGroupLay.setVisibility(View.INVISIBLE);

            APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
            Call<PollCardItem> call = apiInterface.doGetPollsListResources();
            call.enqueue(new Callback<PollCardItem>() {
                @Override
                public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {

                    List<PollCardItem> poll = null;

                    if (response.body().cvotes.size() != -1) {
                        poll = response.body().getResults();
                    }
                    holder.title.setVisibility(View.VISIBLE);
                    rlayout.setVisibility(View.VISIBLE);
                    int total = poll.get(holder.getAdapterPosition()).getTotalVotes();

                    if (poll.size() != -1) {
                        if (PollCardItems.get(holder.getAdapterPosition()).getCount() == 2) {
                            progress1.setMax(100);
                            progress1.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(0), total));
                            progress2.setMax(100);
                            progress2.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(1), total));
                            progress3.setVisibility(View.GONE);
                            progress4.setVisibility(View.GONE);

                            peroption1.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(0), total))) + '%');
                            peroption2.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(1), total))) + '%');
                            peroption3.setVisibility(View.GONE);
                            peroption4.setVisibility(View.GONE);

                            valueoption1.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(0) + " )");
                            valueoption2.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(1) + " )");
                            valueoption3.setVisibility(View.GONE);
                            valueoption4.setVisibility(View.GONE);
                        }

                        if (PollCardItems.get(holder.getAdapterPosition()).getCount() == 3) {
                            progress1.setMax(100);
                            progress1.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(0), total));
                            progress2.setMax(100);
                            progress2.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(1), total));
                            progress3.setMax(100);
                            progress3.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(2), total));
                            progress4.setVisibility(View.GONE);

                            peroption1.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(0), total))) + '%');
                            peroption2.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(1), total))) + '%');
                            peroption3.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(2), total))) + '%');
                            peroption4.setVisibility(View.GONE);

                            valueoption1.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(0) + " )");
                            valueoption2.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(1) + " )");
                            valueoption3.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(2) + " )");
                            valueoption4.setVisibility(View.GONE);
                        }

                        if (PollCardItems.get(holder.getAdapterPosition()).getCount() == 4) {
                            progress1.setMax(100);
                            progress1.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(0), total));
                            progress2.setMax(100);
                            progress2.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(1), total));
                            progress3.setMax(100);
                            progress3.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(2), total));
                            progress4.setMax(100);
                            progress4.setProgress(PollResult(poll.get(holder.getAdapterPosition()).getCvotes(3), total));
                            peroption1.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(0), total))) + '%');
                            peroption2.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(1), total))) + '%');
                            peroption3.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(2), total))) + '%');
                            peroption4.setText(String.format("%.2f", (PollResult(poll.get(holder.getAdapterPosition()).getCvotes(3), total))) + '%');

                            valueoption1.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(0) + " )");
                            valueoption2.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(1) + " )");
                            valueoption3.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(2) + " )");
                            valueoption4.setText("( " + poll.get(holder.getAdapterPosition()).getCvotes(3) + " )");
                        }
                    }
                }

                @Override
                public void onFailure(Call<PollCardItem> call, Throwable t) {
                    Toast.makeText(context, R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    public float PollResult(int PollValue, int total) {
        return (((float) PollValue / (float) total) * 100);
    }

    @Override
    public int getItemCount() {
        return PollCardItems.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


}
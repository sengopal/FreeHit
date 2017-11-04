package com.debut.ellipsis.freehit.Social.Polls;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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

public class PollsItemAdapter extends ArrayAdapter {


    List<PollCardItem> PollCardItems;


    public PollsItemAdapter(Context context, List<PollCardItem> pollCardItems) {
        super(context, 0, pollCardItems);
        PollCardItems = pollCardItems;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        ViewHolder holder = new ViewHolder();
        final SharedPreferences.Editor editor = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
        if (convertView == null) {

            switch (AppCompatDelegate.getDefaultNightMode()) {
                case AppCompatDelegate.MODE_NIGHT_YES:
                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_social_polls_list_item_dark, parent, false);
                    break;
                case AppCompatDelegate.MODE_NIGHT_NO:
                    listItemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_social_polls_list_item, parent, false);
                    break;
            }

            holder.rGroup = listItemView.findViewById(R.id.poll_group);
            holder.title = listItemView.findViewById(R.id.poll_title);
            holder.button1 = listItemView.findViewById(R.id.option_1);
            holder.button2 = listItemView.findViewById(R.id.option_2);
            holder.button3 = listItemView.findViewById(R.id.option_3);
            holder.button4 = listItemView.findViewById(R.id.option_4);
            holder.submit = listItemView.findViewById(R.id.poll_submit);
            holder.cardView = listItemView.findViewById(R.id.card_view);

            holder.RlContainer = listItemView.findViewById(R.id.parent_layout);

            holder.pollRes = holder.RlContainer.findViewById(R.id.pollItem_result);
            holder.rlayout = holder.pollRes.findViewById(R.id.pollItem_result);
            holder.pGroupLay = holder.RlContainer.findViewById(R.id.poll_group_layout);

            holder.option1 = holder.pollRes.findViewById(R.id.option_1);

            holder.option2 = holder.pollRes.findViewById(R.id.option_2);

            holder.option3 = holder.pollRes.findViewById(R.id.option_3);

            holder.option4 = holder.pollRes.findViewById(R.id.option_4);


            holder.peroption1 = holder.pollRes.findViewById(R.id.percentage_option_1);

            holder.peroption2 = holder.pollRes.findViewById(R.id.percentage_option_2);

            holder.peroption3 = holder.pollRes.findViewById(R.id.percentage_option_3);

            holder.peroption4 = holder.pollRes.findViewById(R.id.percentage_option_4);

            holder.valueoption1 = holder.pollRes.findViewById(R.id.votecount_option_1);

            holder.valueoption2 = holder.pollRes.findViewById(R.id.votecount_option_2);

            holder.valueoption3 = holder.pollRes.findViewById(R.id.votecount_option_3);

            holder.valueoption4 = holder.pollRes.findViewById(R.id.votecount_option_4);


            holder.progress1 = holder.pollRes.findViewById(R.id.progress_bar_option1);
            holder.progress1.setProgressColor(Color.parseColor("#00796b"));
            holder.progress1.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

            holder.progress2 = holder.pollRes.findViewById(R.id.progress_bar_option2);
            holder.progress2.setProgressColor(Color.parseColor("#00796b"));
            holder.progress2.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

            holder.progress3 = holder.pollRes.findViewById(R.id.progress_bar_option3);
            holder.progress3.setProgressColor(Color.parseColor("#00796b"));
            holder.progress3.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

            holder.progress4 = holder.pollRes.findViewById(R.id.progress_bar_option4);
            holder.progress4.setProgressColor(Color.parseColor("#00796b"));
            holder.progress4.setProgressBackgroundColor(Color.parseColor("#D2D0D0"));

            listItemView.setTag(holder);
        } else



        holder = (ViewHolder) listItemView.getTag();

        holder.title.setText(PollCardItems.get(position).getQuestion());


        if (PollCardItems.get(position).getCount() == 2) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            holder.option1.setText(PollCardItems.get(position).getCtitle(0));

            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            holder.option2.setText(PollCardItems.get(position).getCtitle(1));

            holder.option3.setVisibility(View.GONE);
            holder.option4.setVisibility(View.GONE);
            holder.button3.setVisibility(View.GONE);
            holder.button4.setVisibility(View.GONE);
        } else if (PollCardItems.get(position).getCount() == 3) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            holder.option1.setText(PollCardItems.get(position).getCtitle(0));

            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            holder.option2.setText(PollCardItems.get(position).getCtitle(1));

            holder.button3.setText(PollCardItems.get(position).getCtitle(2));
            holder.option3.setText(PollCardItems.get(position).getCtitle(2));

            holder.option4.setVisibility(View.GONE);
            holder.button4.setVisibility(View.GONE);
        } else if (PollCardItems.get(position).getCount() == 4) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            holder.option1.setText(PollCardItems.get(position).getCtitle(0));

            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            holder.option2.setText(PollCardItems.get(position).getCtitle(1));

            holder.button3.setText(PollCardItems.get(position).getCtitle(2));
            holder.option3.setText(PollCardItems.get(position).getCtitle(2));

            holder.button4.setText(PollCardItems.get(position).getCtitle(3));
            holder.option4.setText(PollCardItems.get(position).getCtitle(3));

        }


        final ViewHolder finalHolder = holder;
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (finalHolder.button1.isChecked() || finalHolder.button2.isChecked() || finalHolder.button3.isChecked() || finalHolder.button4.isChecked()) {
                    finalHolder.submit.setVisibility(View.INVISIBLE);
                    finalHolder.title.setVisibility(View.VISIBLE);
                    finalHolder.pGroupLay.setVisibility(View.INVISIBLE);

                    editor.putBoolean("has_voted_" + PollCardItems.get(position).getVoteid(), true);
                    editor.apply();

                    int selectedId = finalHolder.rGroup.getCheckedRadioButtonId();

                    RadioButton clicked = finalHolder.RlContainer.findViewById(selectedId);

                    String name = clicked.getText().toString();

                    int choice = PollCardItems.get(position).searchTitle(name);

                    finalHolder.rlayout.setVisibility(View.VISIBLE);

                    APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
                    Call<PollCardItem> call = apiInterface.doVotePollListResources(PollCardItems.get(position).getId().toString(), String.valueOf(choice));
                    call.enqueue(new Callback<PollCardItem>() {
                        @Override
                        public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                            PollCardItem poll = response.body().getResults().get(0);

                            int total = poll.getTotalVotes();
                            finalHolder.title.setVisibility(View.VISIBLE);

                            if (PollCardItems.get(position).getCount() == 2) {
                                finalHolder.progress1.setMax(100);
                                finalHolder.progress1.setProgress(PollResult(poll.getCvotes(0), total));
                                finalHolder.progress2.setMax(100);
                                finalHolder.progress2.setProgress(PollResult(poll.getCvotes(1), total));
                                finalHolder.progress3.setVisibility(View.GONE);
                                finalHolder.progress4.setVisibility(View.GONE);

                                finalHolder.peroption1.setText(String.format("%.2f", (PollResult(poll.getCvotes(0), total))) + '%');
                                finalHolder.peroption2.setText(String.format("%.2f", (PollResult(poll.getCvotes(1), total))) + '%');
                                finalHolder.peroption3.setVisibility(View.GONE);
                                finalHolder.peroption4.setVisibility(View.GONE);

                                finalHolder.valueoption1.setText("( " + poll.getCvotes(0) + " )");
                                finalHolder.valueoption2.setText("( " + poll.getCvotes(1) + " )");
                                finalHolder.valueoption3.setVisibility(View.GONE);
                                finalHolder.valueoption4.setVisibility(View.GONE);
                            } else if (PollCardItems.get(position).getCount() == 3) {
                                finalHolder.progress1.setMax(100);
                                finalHolder.progress1.setProgress(PollResult(poll.getCvotes(0), total));
                                finalHolder.progress2.setMax(100);
                                finalHolder.progress2.setProgress(PollResult(poll.getCvotes(1), total));
                                finalHolder.progress3.setMax(100);
                                finalHolder.progress3.setProgress(PollResult(poll.getCvotes(2), total));
                                finalHolder.progress4.setVisibility(View.GONE);

                                finalHolder.peroption1.setText(String.format("%.2f", (PollResult(poll.getCvotes(0), total))) + '%');
                                finalHolder.peroption2.setText(String.format("%.2f", (PollResult(poll.getCvotes(1), total))) + '%');
                                finalHolder.peroption3.setText(String.format("%.2f", (PollResult(poll.getCvotes(2), total))) + '%');
                                finalHolder.peroption4.setVisibility(View.GONE);

                                finalHolder.valueoption1.setText("( " + poll.getCvotes(0) + " )");
                                finalHolder.valueoption2.setText("( " + poll.getCvotes(1) + " )");
                                finalHolder.valueoption3.setText("( " + poll.getCvotes(2) + " )");
                                finalHolder.valueoption4.setVisibility(View.GONE);
                            } else if (PollCardItems.get(position).getCount() == 4) {
                                finalHolder.progress1.setMax(100);
                                finalHolder.progress1.setProgress(PollResult(poll.getCvotes(0), total));
                                finalHolder.progress2.setMax(100);
                                finalHolder.progress2.setProgress(PollResult(poll.getCvotes(1), total));
                                finalHolder.progress3.setMax(100);
                                finalHolder.progress3.setProgress(PollResult(poll.getCvotes(2), total));
                                finalHolder.progress4.setMax(100);
                                finalHolder.progress4.setProgress(PollResult(poll.getCvotes(3), total));
                                finalHolder.peroption1.setText(String.format("%.2f", (PollResult(poll.getCvotes(0), total))) + '%');
                                finalHolder.peroption2.setText(String.format("%.2f", (PollResult(poll.getCvotes(1), total))) + '%');
                                finalHolder.peroption3.setText(String.format("%.2f", (PollResult(poll.getCvotes(2), total))) + '%');
                                finalHolder.peroption4.setText(String.format("%.2f", (PollResult(poll.getCvotes(3), total))) + '%');

                                finalHolder.valueoption1.setText("( " + poll.getCvotes(0) + " )");
                                finalHolder.valueoption2.setText("( " + poll.getCvotes(1) + " )");
                                finalHolder.valueoption3.setText("( " + poll.getCvotes(2) + " )");
                                finalHolder.valueoption4.setText("( " + poll.getCvotes(3) + " )");

                            }


                        }

                        @Override
                        public void onFailure(Call<PollCardItem> call, Throwable t) {
                            Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                        }
                    });
                } else {

                    Toast.makeText(getContext(), "Please select an option for Poll : " + (position + 1), Toast.LENGTH_SHORT).show();
                }
            }
        });


        SharedPreferences prefs = getContext().getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        boolean name = prefs.getBoolean("has_voted_" + PollCardItems.get(position).getVoteid(), false);
        if (name) {
            finalHolder.submit.setVisibility(View.INVISIBLE);

            finalHolder.pGroupLay.setVisibility(View.INVISIBLE);

            APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
            Call<PollCardItem> call = apiInterface.doGetPollsListResources();
            call.enqueue(new Callback<PollCardItem>() {
                @Override
                public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {

                    List<PollCardItem> poll = null;

                    if (response.body().cvotes.size() != -1) {
                        poll = response.body().getResults();
                    }
                    finalHolder.title.setVisibility(View.VISIBLE);
                    finalHolder.rlayout.setVisibility(View.VISIBLE);
                    int total = poll.get(position).getTotalVotes();

                    if (poll.size() != -1) {
                        if (PollCardItems.get(position).getCount() == 2) {
                            finalHolder.progress1.setMax(100);
                            finalHolder.progress1.setProgress(PollResult(poll.get(position).getCvotes(0), total));
                            finalHolder.progress2.setMax(100);
                            finalHolder.progress2.setProgress(PollResult(poll.get(position).getCvotes(1), total));
                            finalHolder.progress3.setVisibility(View.GONE);
                            finalHolder.progress4.setVisibility(View.GONE);

                            finalHolder.peroption1.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(0), total))) + '%');
                            finalHolder.peroption2.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(1), total))) + '%');
                            finalHolder.peroption3.setVisibility(View.GONE);
                            finalHolder.peroption4.setVisibility(View.GONE);

                            finalHolder.valueoption1.setText("( " + poll.get(position).getCvotes(0) + " )");
                            finalHolder.valueoption2.setText("( " + poll.get(position).getCvotes(1) + " )");
                            finalHolder.valueoption3.setVisibility(View.GONE);
                            finalHolder.valueoption4.setVisibility(View.GONE);
                        } else if (PollCardItems.get(position).getCount() == 3) {
                            finalHolder.progress1.setMax(100);
                            finalHolder.progress1.setProgress(PollResult(poll.get(position).getCvotes(0), total));
                            finalHolder.progress2.setMax(100);
                            finalHolder.progress2.setProgress(PollResult(poll.get(position).getCvotes(1), total));
                            finalHolder.progress3.setMax(100);
                            finalHolder.progress3.setProgress(PollResult(poll.get(position).getCvotes(2), total));
                            finalHolder.progress4.setVisibility(View.GONE);

                            finalHolder.peroption1.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(0), total))) + '%');
                            finalHolder.peroption2.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(1), total))) + '%');
                            finalHolder.peroption3.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(2), total))) + '%');
                            finalHolder.peroption4.setVisibility(View.GONE);

                            finalHolder.valueoption1.setText("( " + poll.get(position).getCvotes(0) + " )");
                            finalHolder.valueoption2.setText("( " + poll.get(position).getCvotes(1) + " )");
                            finalHolder.valueoption3.setText("( " + poll.get(position).getCvotes(2) + " )");
                            finalHolder.valueoption4.setVisibility(View.GONE);
                        } else if (PollCardItems.get(position).getCount() == 4) {
                            finalHolder.progress1.setMax(100);
                            finalHolder.progress1.setProgress(PollResult(poll.get(position).getCvotes(0), total));
                            finalHolder.progress2.setMax(100);
                            finalHolder.progress2.setProgress(PollResult(poll.get(position).getCvotes(1), total));
                            finalHolder.progress3.setMax(100);
                            finalHolder.progress3.setProgress(PollResult(poll.get(position).getCvotes(2), total));
                            finalHolder.progress4.setMax(100);
                            finalHolder.progress4.setProgress(PollResult(poll.get(position).getCvotes(3), total));
                            finalHolder.peroption1.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(0), total))) + '%');
                            finalHolder.peroption2.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(1), total))) + '%');
                            finalHolder.peroption3.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(2), total))) + '%');
                            finalHolder.peroption4.setText(String.format("%.2f", (PollResult(poll.get(position).getCvotes(3), total))) + '%');

                            finalHolder.valueoption1.setText("( " + poll.get(position).getCvotes(0) + " )");
                            finalHolder.valueoption2.setText("( " + poll.get(position).getCvotes(1) + " )");
                            finalHolder.valueoption3.setText("( " + poll.get(position).getCvotes(2) + " )");
                            finalHolder.valueoption4.setText("( " + poll.get(position).getCvotes(3) + " )");
                        }
                    }
                }

                @Override
                public void onFailure(Call<PollCardItem> call, Throwable t) {
                    Toast.makeText(getContext(), R.string.no_internet_connection, Toast.LENGTH_SHORT).show();
                }
            });

        }


        return listItemView;
    }

    private class ViewHolder {
        RadioGroup rGroup;
        TextView title;
        RadioButton button1;
        RadioButton button2;
        RadioButton button3;
        RadioButton button4;
        Button submit;
        CardView cardView;
        RelativeLayout RlContainer;
        View pollRes;
        RelativeLayout rlayout;
        RelativeLayout pGroupLay;
        TextView option1;
        TextView option2;
        TextView option3;
        TextView option4;
        TextView peroption1;
        TextView peroption2;
        TextView peroption3;
        TextView peroption4;
        TextView valueoption1;
        TextView valueoption2;
        TextView valueoption3;
        TextView valueoption4;
        RoundCornerProgressBar progress1;
        RoundCornerProgressBar progress2;
        RoundCornerProgressBar progress3;
        RoundCornerProgressBar progress4;

    }

    private float PollResult(int PollValue, int total) {
        return (((float) PollValue / (float) total) * 100);
    }

    @Override
    public int getCount() {
        return PollCardItems.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

}



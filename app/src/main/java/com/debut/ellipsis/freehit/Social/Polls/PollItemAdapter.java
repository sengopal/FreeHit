package com.debut.ellipsis.freehit.Social.Polls;

import android.content.Context;
import android.graphics.Color;
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


public class PollItemAdapter extends RecyclerView.Adapter<com.debut.ellipsis.freehit.Social.Polls.PollItemAdapter.PollsViewHolder> {

    private List<PollCardItem> PollCardItems;
    private int rowLayout;
    private Context context;


    public static class PollsViewHolder extends RecyclerView.ViewHolder {
        RelativeLayout pollsLayout;
        RadioGroup rGroup;
        TextView title;
        RadioButton button1;
        RadioButton button2;
        RadioButton button3;
        RadioButton button4;
        Button submit;
        RelativeLayout rlcontainer;


        public PollsViewHolder(View v) {
            super(v);
            pollsLayout = (RelativeLayout) v.findViewById(R.id.parent_layout);
            rGroup = (RadioGroup) v.findViewById(R.id.poll_group);
            title = (TextView) v.findViewById(R.id.poll_title);
            button1 = (RadioButton) v.findViewById(R.id.option_1);
            button2 = (RadioButton) v.findViewById(R.id.option_2);
            button3 = (RadioButton) v.findViewById(R.id.option_3);
            button4 = (RadioButton) v.findViewById(R.id.option_4);
            submit = (Button) v.findViewById(R.id.poll_submit);

            rlcontainer = (RelativeLayout) v.findViewById(R.id.parent_layout);
        }
    }

    public PollItemAdapter(List<PollCardItem> news, int rowLayout, Context context) {
        this.PollCardItems = news;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public com.debut.ellipsis.freehit.Social.Polls.PollItemAdapter.PollsViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                                      int viewType) {
        View view = null;
        if (viewType == 0) {
            view = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        }
        return new com.debut.ellipsis.freehit.Social.Polls.PollItemAdapter.PollsViewHolder(view);

    }


    @Override
    public void onBindViewHolder(final com.debut.ellipsis.freehit.Social.Polls.PollItemAdapter.PollsViewHolder holder, final int position) {
        holder.title.setText(PollCardItems.get(position).getQuestion());
        final View pollRes = holder.rlcontainer.findViewById(R.id.pollItem_result);
        final RelativeLayout rlayout = (RelativeLayout) pollRes.findViewById(R.id.pollItem_result);
        final RelativeLayout pGroupLay = (RelativeLayout) holder.rlcontainer.findViewById(R.id.poll_group_layout);

        TextView option1 = (TextView) pollRes.findViewById(R.id.option_1);

        TextView option2 = (TextView) pollRes.findViewById(R.id.option_2);

        TextView option3 = (TextView) pollRes.findViewById(R.id.option_3);

        TextView option4 = (TextView) pollRes.findViewById(R.id.option_4);


        final TextView peroption1 = (TextView) pollRes.findViewById(R.id.percentage_option_1);

        final TextView peroption2 = (TextView) pollRes.findViewById(R.id.percentage_option_2);

        final TextView peroption3 = (TextView) pollRes.findViewById(R.id.percentage_option_3);

        final TextView peroption4 = (TextView) pollRes.findViewById(R.id.percentage_option_4);

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

        if (PollCardItems.get(position).getCtitle(0) != null) {
            holder.button1.setText(PollCardItems.get(position).getCtitle(0));
            option1.setText(PollCardItems.get(position).getCtitle(0));
        } else {
            holder.button1.setVisibility(View.INVISIBLE);
        }
        if (PollCardItems.get(position).getCtitle(1) != null) {
            holder.button2.setText(PollCardItems.get(position).getCtitle(1));
            option2.setText(PollCardItems.get(position).getCtitle(1));
        } else {
            holder.button2.setVisibility(View.INVISIBLE);
        }
        if (PollCardItems.get(position).getCtitle(2) != null) {
            holder.button3.setText(PollCardItems.get(position).getCtitle(2));
            option3.setText(PollCardItems.get(position).getCtitle(2));
        } else {
            holder.button3.setVisibility(View.INVISIBLE);
        }
        if (PollCardItems.get(position).getCtitle(3) != null) {
            holder.button4.setText(PollCardItems.get(position).getCtitle(3));

            option4.setText(PollCardItems.get(position).getCtitle(3));
        } else {
            holder.button4.setVisibility(View.INVISIBLE);
        }

// View.OnClickListener mClickListener;
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holder.rlcontainer.setVisibility(View.INVISIBLE);
                holder.submit.setVisibility(View.INVISIBLE);
                holder.title.setVisibility(View.INVISIBLE);
                pGroupLay.setVisibility(View.INVISIBLE);

                int selectedId = holder.rGroup.getCheckedRadioButtonId();
                RadioButton clicked = (RadioButton) holder.rlcontainer.findViewById(selectedId);
                String name = clicked.getText().toString();
                int choice = PollCardItems.get(position).searchTitle(name);
                rlayout.setVisibility(View.VISIBLE);
                Toast.makeText(context, String.valueOf(choice), Toast.LENGTH_SHORT).show();
                APIInterface apiInterface = ApiClient.getClient().create(APIInterface.class);
                Call<PollCardItem> call = apiInterface.doVotePollListResources(PollCardItems.get(position).getId().toString(), String.valueOf(choice));
                call.enqueue(new Callback<PollCardItem>() {
                    @Override
                    public void onResponse(Call<PollCardItem> call, Response<PollCardItem> response) {
                        PollCardItem poll = response.body().getResults().get(0);

                        int sum = 0;
                        for (int i = 0; i < poll.getCount(); i++) {
                            sum += poll.getCvotes(i);
                        }
                        float total = sum;

                        System.out.println(total);

                        progress1.setMax(100);
                        progress1.setProgress((poll.getCvotes(0) / total) * 100);
                        progress2.setMax(100);
                        progress2.setProgress((poll.getCvotes(1) / total) * 100);
                        progress3.setMax(100);
                        progress3.setProgress((poll.getCvotes(2) / total) * 100);
                        progress4.setMax(100);
                        progress4.setProgress((poll.getCvotes(3) / total) * 100);
                        peroption1.setText(String.valueOf((poll.getCvotes(0) / total) * 100) + '%');
                        peroption2.setText(String.valueOf((poll.getCvotes(1) / total) * 100) + '%');
                        peroption3.setText(String.valueOf((poll.getCvotes(2) / total) * 100) + '%');
                        peroption4.setText(String.valueOf((poll.getCvotes(3) / total) * 100) + '%');
                    }

                    @Override
                    public void onFailure(Call<PollCardItem> call, Throwable t) {
                        Toast.makeText(context, "FAILURE " + t, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return PollCardItems.size();
    }
}


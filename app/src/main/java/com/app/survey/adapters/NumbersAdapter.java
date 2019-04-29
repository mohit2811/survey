package com.app.survey.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.survey.R;
import com.app.survey.datamodels.NumbersData;
import com.app.survey.holders.NumbersHolder;

import java.util.ArrayList;

/**
 * Created by ghumman on 9/11/2017.
 */

public class NumbersAdapter extends RecyclerView.Adapter<NumbersHolder> {

    private ArrayList<NumbersData> numbers ;

    private Context c ;

    public static int curr_selected = -1 ;
    public NumbersAdapter(ArrayList<NumbersData>  numbers)
    {
        this.numbers = numbers ;
    }

    @Override
    public NumbersHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        c = parent.getContext();
        return new NumbersHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.numbers_cell , parent , false));

    }

    @Override
    public void onBindViewHolder(final NumbersHolder holder, final int position) {

        holder.number.setText(String.valueOf(numbers.get(position).getNumber()));

        if(numbers.get(position).getSelected() == true)
        {
            holder.number.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.circle4));
            holder.number.setTextColor(Color.WHITE);
        }
        else {
            holder.number.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.stroke_circle));

            holder.number.setTextColor(c.getResources().getColor(R.color.colorPrimary));
        }

        holder.number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(curr_selected >= 0)
                {
                    numbers.get(curr_selected).setSelected(false);

                }
               if(numbers.get(position).getSelected() == true)
               {
                   holder.number.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.circle4));
                   holder.number.setTextColor(c.getResources().getColor(R.color.colorPrimary));

                   numbers.get(position).setSelected(false);
               }
               else {
                   holder.number.setBackgroundDrawable(c.getResources().getDrawable(R.drawable.stroke_circle));
                   holder.number.setTextColor(Color.WHITE);

                   numbers.get(position).setSelected(true);
               }

               curr_selected = position;

                NumbersAdapter.this.notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return numbers.size();
    }
}

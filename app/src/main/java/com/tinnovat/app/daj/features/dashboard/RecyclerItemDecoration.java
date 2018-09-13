package com.tinnovat.app.daj.features.dashboard;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

import com.tinnovat.app.daj.R;

/**
 * Item decorator for recycler view. Adds margin to the outermost children of the list to induce an
 * over scroll effect with drag.
 */

class RecyclerItemDecoration extends RecyclerView.ItemDecoration {
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        // Global Variable
        boolean isTablet;
        boolean isSmall;

//Get Value from values bool.xml file
        isTablet = view.getResources().getBoolean(R.bool.isTablet);
        isSmall = view.getResources().getBoolean(R.bool.small);

//Now check condition
        if(isTablet){
            //Device is tablet
                outRect.top = (int) DpToPx(parent.getContext(), 50);
                outRect.bottom = (int) DpToPx(parent.getContext(), 50);//50

                if (parent.getChildAdapterPosition(view) == 0) {
                    outRect.top = (int) DpToPx(parent.getContext(), 50);
                    outRect.bottom = (int) DpToPx(parent.getContext(), 50);
                }
                if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                    outRect.top = (int) DpToPx(parent.getContext(), 50);
                    outRect.bottom = (int) DpToPx(parent.getContext(), 50);
                }

        }else{

            outRect.top = (int) DpToPx(parent.getContext(), 25);//30
            outRect.bottom = (int) DpToPx(parent.getContext(), 25);//30

            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.top = (int) DpToPx(parent.getContext(), 25);
                outRect.bottom = (int) DpToPx(parent.getContext(), 25);
            }
            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.top = (int) DpToPx(parent.getContext(), 25);
                outRect.bottom = (int) DpToPx(parent.getContext(), 25);
            }
        }


    }

    /**
     * Function to convert a value given in dp to pixels (px).
     *
     * @param context Current context, used to access resources.
     * @param dp      The value (in dp) to be converted.
     * @return        The value in pixels.
     */

    private float DpToPx(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}

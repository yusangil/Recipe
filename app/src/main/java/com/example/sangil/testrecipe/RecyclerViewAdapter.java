package com.example.sangil.testrecipe;

/**
 * Created by sangil on 2015-12-11.
 */
import android.app.AlertDialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.database.Cursor;
        import android.database.sqlite.SQLiteDatabase;
        import android.os.AsyncTask;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.CompoundButton;
        import android.widget.Toast;

        import org.json.JSONObject;

        import java.io.DataOutput;
        import java.io.DataOutputStream;
        import java.io.OutputStream;
        import java.net.URL;
        import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;

/**
 * Created by LGPC on 2015-12-04.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<ListLowViewHolder> {

    private static final String COLD = "냉장";
    private static final String ICE = "냉동";
    private static final String WARM = "실온";


    private Context context;
    private List<Ingredient> items;
    int itemLayout;

    public RecyclerViewAdapter(Context context, List<Ingredient> items, int itemLayout) {
        this.context = context;
        this.items = items;
        this.itemLayout = itemLayout;
    }

    @Override
    public ListLowViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recycle, null);
        return new ListLowViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return this.items.size();
    }

    @Override
    public void onBindViewHolder(final ListLowViewHolder listLowViewHolder, int position) {

        final Ingredient item = items.get(position);
        String statusName = "상태";

        switch (item.ingredient_status){
            case Ingredient.COLD:
                statusName = COLD;
                break;
            case Ingredient.ICE:
                statusName = ICE;
                break;
            case Ingredient.WARM:
                statusName = WARM;
                break;
        }

        listLowViewHolder.showStatus.setText(statusName);
        listLowViewHolder.showName.setText(item.name);
        listLowViewHolder.showCount.setText(item.count+"개");
        listLowViewHolder.showGram.setText( item.gram+"g");
        Log.d("shelf Life","year"+item.shelfLife.get(Calendar.YEAR)+" month"+ item.shelfLife.get(Calendar.MONTH)+" day" + item.shelfLife.get(Calendar.DAY_OF_MONTH));

        listLowViewHolder.showShelfLife.setText(item.shelfLifeToString());
    }
}
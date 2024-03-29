package it.macgood.mathanapp.ui.guidebook;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import it.macgood.mathanapp.databinding.ItemGuidebookBinding;
import it.macgood.mathanapp.ui.MainActivity;
import it.macgood.mathanapp.R;
import it.macgood.mathanapp.ui.guidebook.supportingmaterials.MaterialsURL;

public class GuidebookAdapter extends RecyclerView.Adapter<GuidebookAdapter.ViewHolder>{

    private Context mContext;
    private List<Guide> mGuides;

    public GuidebookAdapter() {
    }

    public GuidebookAdapter(List<Guide> mGuides) {
        this.mGuides = mGuides;
    }

    public GuidebookAdapter(Context mContext, List<Guide> mGuides) {
        this.mContext = mContext;
        this.mGuides = mGuides;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_guidebook, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(mGuides.get(position).getTitle());
        holder.description.setText(mGuides.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return mGuides.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        CardView guidebook;
        TextView title;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ItemGuidebookBinding binding = ItemGuidebookBinding.bind(itemView);

            guidebook = binding.guidebook;
            title = binding.guideTitle;
            description = binding.guideDescription;


            MainActivity activity = (MainActivity) itemView.getContext();
            NavController navController = Navigation.findNavController(activity, R.id.app_placeholder);

            guidebook.setOnClickListener(view -> {
                switch (title.getText().toString()) {
                    case "Энциклопедия": navController.navigate(R.id.get_encyclopedia); break;
                    case "Desmos": navController.navigate(R.id.get_desmos_from_guidebook); break;
                    case "Тесты": navController.navigate(R.id.get_tests_from_guidebook); break;
                    case "Сопроводительные материалы": navController.navigate(R.id.get_supportingMaterials_from_guidebook); break;
                    case "Конвертер изображений": Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(MaterialsURL.CONVERTER));
                        itemView.getContext().startActivity(browserIntent); break;
                    case "Примечания": navController.navigate(R.id.get_remark_from_guidebook); break;
                }
            });

        }
    }
}

package android.josephhowerton.travelingsalesman.ui.auth.interest

import android.josephhowerton.travelingsalesman.R
import android.josephhowerton.travelingsalesman.data.foursquare.FoursquareCategory
import android.josephhowerton.travelingsalesman.databinding.RowItemInterestBinding
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class PickInterestAdapter(private val interests: List<FoursquareCategory>,
                          private val listener: CategoryClickListener):
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = DataBindingUtil.inflate<RowItemInterestBinding>(
            LayoutInflater.from(parent.context),
            R.layout.row_item_interest,
            parent,
            false)
        return FoursquareCategoryViewHolder(binding, listener)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val fsHolder = holder as FoursquareCategoryViewHolder
        fsHolder.bindCategory(interests[position])
    }

    override fun getItemCount(): Int {
        return interests.size
    }

    class FoursquareCategoryViewHolder(
            private val binding: RowItemInterestBinding,
            private val listener: CategoryClickListener
    ):
        RecyclerView.ViewHolder(binding.root){

        fun bindCategory(category: FoursquareCategory){
            binding.category = category
            binding.cardView.setOnClickListener {
                listener.onCategoryClick(category)
            }
        }
    }

    interface CategoryClickListener{
        fun onCategoryClick(category: FoursquareCategory)
    }
}
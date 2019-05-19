package com.orpuwupetup.numberslight.ui.numbers.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.data.model.number.Number
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject

class NumbersAdapter(
    private val context: Context, private val numbers: MutableList<Number>,
    private val clickedNumberPublishedSubject: PublishSubject<Pair<Int, String>>,
    private val picasso: Picasso
) : RecyclerView.Adapter<NumbersAdapter.NumberViewHolder>() {

    val clickedNumberEvent = clickedNumberPublishedSubject

    companion object {
        const val NO_POSITION_SELECTED = -1
    }

    private var selectedItemPosition: Int = NO_POSITION_SELECTED

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NumberViewHolder =
        NumberViewHolder(LayoutInflater.from(context).inflate(R.layout.item_numbers_list, parent, false))

    override fun getItemCount(): Int = numbers.size

    override fun onBindViewHolder(holder: NumberViewHolder, position: Int) {
        val currentNumber = numbers[position]

        with(holder) {
            tvNumberName.text = currentNumber.name
            loadNumberPhoto(ivNumberImage, currentNumber.imageUrl)
            llRoot.isSelected = selectedItemPosition == position
            with(llRoot) {
                isSelected = selectedItemPosition == position
                setOnClickListener {
                    // notify listener about clicked item position and name
                    clickedNumberPublishedSubject.onNext(position to currentNumber.name)

                    if (selectedItemPosition != NO_POSITION_SELECTED)
                        // notify former selected position to make it loose selection (if any was selected before)
                        notifyItemChanged(selectedItemPosition)

                    selectedItemPosition = position

                    // notify newly selected position to make itself selected
                    notifyItemChanged(selectedItemPosition)
                }
            }
        }
    }

    private fun loadNumberPhoto(imageView: ImageView, imageUrl: String) {
        picasso.load(imageUrl).into(imageView)
    }

    fun setSelectedItem(position: Int) {
        selectedItemPosition = position
        notifyDataSetChanged()
    }

    fun replaceData(newNumbers: List<Number>) {
        numbers.clear()
        numbers.addAll(newNumbers)
        notifyDataSetChanged()
    }

    class NumberViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvNumberName: TextView = view.findViewById(R.id.text_number_list_item_name)
        val llRoot: LinearLayout = view.findViewById(R.id.root_number_list_item)
        val ivNumberImage: ImageView = view.findViewById(R.id.image_number_list_item_image)
    }
}
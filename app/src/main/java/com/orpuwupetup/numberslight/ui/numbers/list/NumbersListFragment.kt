package com.orpuwupetup.numberslight.ui.numbers.list

import android.os.Bundle
import android.os.Handler
import androidx.recyclerview.widget.RecyclerView
import com.orpuwupetup.numberslight.R
import com.orpuwupetup.numberslight.data.model.number.Number
import com.orpuwupetup.numberslight.ui.AbstractFragment
import com.orpuwupetup.numberslight.ui.numbers.list.adapter.NumbersAdapter
import com.orpuwupetup.numberslight.utils.getScrollPosition
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.fragment_numbers_list.*
import javax.inject.Inject

class NumbersListFragment: AbstractFragment(), NumbersListFragmentContract.View {

    @Inject
    override lateinit var presenter: NumbersListFragmentContract.Presenter

    @Inject
    lateinit var adapter: NumbersAdapter

    companion object {
        const val SELECTED_ITEM_KEY = "selectedItem"
        const val LIST_SCROLL_KEY = "listScroll"
    }

    private var clickedNumberDisposable: Disposable? = null
    private var numberClickedListener: NumberClickedListener? = null

    override fun getFragmentLayout(): Int = R.layout.fragment_numbers_list

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initViews()

        presenter.takeView(this, readFromBundle(savedInstanceState))
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        writeToBundle(outState, presenter.getState())
    }

    private fun readFromBundle(savedInstanceState: Bundle?): NumbersListFragmentContract.State =
        NumbersListFragmentState(savedInstanceState?.getInt(LIST_SCROLL_KEY), savedInstanceState?.getInt(SELECTED_ITEM_KEY))

    private fun writeToBundle(outState: Bundle, state: NumbersListFragmentContract.State) {
        with(outState) {
            putInt(LIST_SCROLL_KEY, state.getScroll() ?: 0)
            putInt(SELECTED_ITEM_KEY, state.getSelectedItemPosition() ?: NumbersAdapter.NO_POSITION_SELECTED)
        }
    }

    override fun onResume() {
        super.onResume()

        clickedNumberDisposable = clickedNumberDisposable ?: adapter.clickedNumberEvent.subscribe{ clickedItem ->
            presenter.onItemClicked(clickedItem.first, clickedItem.second)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
        clickedNumberDisposable?.dispose()
        clickedNumberDisposable = null
    }

    override fun showNumbersList(numbers: List<Number>) {
        adapter.replaceData(numbers)
    }

    private fun initViews() {
        list_numbers.adapter = this.adapter
        list_numbers.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)
                    presenter.onScrollChanged(recyclerView.getScrollPosition())
                }
            })
    }

    override fun setScrollPosition(scroll: Int) {
        // ugly fix for known issue with RecyclerView scrolling problems
        Handler().postDelayed(
            {
                list_numbers?.scrollToPosition(scroll)
            }, 0
        )
    }

    override fun setSelectedItem(itemPosition: Int) {
        adapter.setSelectedItem(itemPosition)
    }

    fun setOnNumberClickedListener(listener: NumberClickedListener?) {
        this.numberClickedListener = listener
    }

    override fun notifyListenersAboutItemClicked(clickedItemName: String) {
        numberClickedListener?.onNumberClicked(clickedItemName)
    }

    interface NumberClickedListener {
        fun onNumberClicked(clickedNumberName: String)
    }
}
package com.orpuwupetup.numberslight.utils

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.getScrollPosition(): Int =
    (this.layoutManager as LinearLayoutManager).findFirstCompletelyVisibleItemPosition()